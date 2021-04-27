package com.nefu.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nefu.gateway.component.CommonResult;
import com.nefu.gateway.component.RedisUtil;
import com.nefu.gateway.component.TransferJson;
import com.nefu.gateway.feign.UserConsumerFeignClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author :覃玉锦
 * @create :2020-09-07 20:51:00
 * 权限拦截器，对于每一个权限都进行权限检查，如果用户的角色拥有该权限，则放行
 * 当前的问题：如何对于带参请求地址进行转换，将数字参数与数据库存的url对应，
 * 例如/api/user/user/del/115/role/2 和数据库字段url：
 * /api/user/user/del/{uid} 相互对应
 *
 * 解决方法（临时）：经过统计，大部分接口只带一个数字参数，使用正则表达式可以转换，将数字转换成为{id}，后端存url，有
 * 数字的地方统一使用{id}。如果涉及到多个数字参数，则可以考虑使用请求体或者?之后带参传输
 *
 * 权限校验防止超时的思路：1、减少远端访问数据库的次数
 * 2、做缓存，把数据先缓存到内存，减少数据的访问
 */
@Component
@Slf4j
@CrossOrigin
public class PermissionGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserConsumerFeignClient feignClient;

    @Autowired
    private TransferJson transferJson;

    private final String PERMISSION_KEY = "permissionList";

    private final String ROLE_PERMISSION_KEY = "rolePermissionList";

    //白名单
    private static List<String> white =initWhite();

    /**
     * 每人都拥有的权限
     * @return
     */
    public static ArrayList<String> initWhite(){
        ArrayList<String> whiteList = new ArrayList<>();
        whiteList.add("/api/verifyCode");
        whiteList.add("/api/login");
        whiteList.add("/api/renew");
        whiteList.add("/api/register");
        whiteList.add("/api/user/info");
        return whiteList;
    }

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("进入网关权限拦截器");
        RequestPath path = exchange.getRequest().getPath();

        log.info("全路径："+exchange.getRequest().getURI());

        log.info("判断是否含有中文："+path.toString().contains("%"));
        log.info("是否在权限白名单中:{}",white.contains(path.toString()));
        //给登录的api放行
        if(white.contains(path.toString()) || path.toString().contains("%")) {
            return chain.filter(exchange);
        }

        HttpMethod method = exchange.getRequest().getMethod();

        String regex = "-*\\d{1,}";
        String regexPath = path.toString().replaceAll(regex, "{id}");

        log.info("请求方法："+method.toString());
        log.info("请求url："+path);
        log.info("经过正则表达式修改过后的url："+regexPath);
        log.info("headers:"+exchange.getRequest().getHeaders().toString());

        //对于其他api：先拉取用户的角色，检查角色是否有对应权限，有则通过
        List<String> rids = exchange.getRequest().getHeaders().get("rids");
        log.info("rids:"+rids.get(0));
        //用户对应角色id集,由于远端访问数据库会产生延迟，所以最好减少数据库访问次数
        List<Integer> ridList = objectMapper.readValue(rids.get(0), List.class);

        //如果是管理员，那么拥有所有的权限
        if(ridList.contains(1))return chain.filter(exchange);

        //这里通过redis缓存来进行取值，redis数据结构选型和设计：
        //1、都选用list结构。2、role_permission的key是角色id，然后value是pids

        //这里对于权限的请求会先打到缓存层redis上，如果redis上没有，那么则会请求mysql，然后写入redis
        //权限校验
        //1、如果缓存层没有，那么优先写入缓存层
        //权限集的缓存
        List<Object> permissionList = redisUtil.lGet(PERMISSION_KEY, 0, -1);
        if(permissionList.size() == 0){
            //需要走mysql，然后写入到redis中
            CommonResult res1 = feignClient.getPermissions();
            permissionList = (List<Object>) transferJson.transferToClz(res1.getData().toString(), List.class);
            //把拿到的权限集合缓存进入redis，key设置为半天
            redisUtil.lSet(PERMISSION_KEY,permissionList,60*60*12,true);
        }

        //角色和权限对应关系集合的缓存
        for (Integer rid : ridList) {
            //每一个rid对应着一个list缓存
            List<Object> pids = redisUtil.lGet(ROLE_PERMISSION_KEY + ":" + rid, 0, -1);
            //如果缓存中没有，则需要添加入redis
            if(pids.size()==0){
                //返回的是map
                CommonResult res2 = feignClient.getRolePermissions();
                Map<Integer,List<Object>> mapping = (Map<Integer, List<Object>>) res2.getData();
                //拿到了每一个rid和pids的对应关系mapping
                for (Map.Entry<Integer, List<Object>> entry : mapping.entrySet()) {
                    //把每一个entry放入到redis
                    redisUtil.lSet(ROLE_PERMISSION_KEY+":"+entry.getKey(),entry.getValue(),
                            60*60*12,true);
                }
                //缓存结束。之后可以开始进行鉴权判断
                break;
            }
        }
        //此时已经拥有角色-权限的redis缓存，通过缓存来进行权限过滤
        for (Integer rid : ridList) {
            List<Object> pids = redisUtil.lGet(ROLE_PERMISSION_KEY + ":" + rid, 0, -1);
            for (Object p : permissionList) {
                Map<String,Integer> temp = (Map<String, Integer>) p;
                Integer id = temp.get("id");
                for (Object pid : pids) {
                    int pid1 = (int)pid;
                    if(pid1 == id){
                        //如果拥有的权限中，方法和url都校验通过则放行
                        if(method.toString().equals(temp.get("method")) &&
                                regexPath.equals(temp.get("url"))){
                            return chain.filter(exchange);
                        }
                    }
                }
            }
        }

        log.info("权限不足,未通过！");
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"无权限访问！");
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
