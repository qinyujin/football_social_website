package com.nefu.login.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nefu.login.component.*;
import com.nefu.login.entity.User;
import com.nefu.login.feign.ConsumerFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author :覃玉锦
 * @create :2021-04-08 15:50:00
 * 先校验验证码是否正确，再按照账号、密码的顺序进行校验，校验成功的话，把uid、rids封装到token返回给前端，前端之后通过
 * 在header里携带来表示登陆状态
 */
@RestController
@RequestMapping("/api/")
@Slf4j
public class LoginController {
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${privateKey}")
    private String privateKey;

    @Value("${publicKey}")
    private String publicKey;

    private final String VERIFYCODE = "VerifyCodeList";

    //通过微服务来进行调用
    @Autowired
    private ConsumerFeignClient consumerFeignClient;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private TransferJson transferJson;

    @PostMapping("login")
    public CommonResult login(@RequestBody User user, @RequestParam("verifyCode") String code) {
        //检测验证码、账号和密码来进行登陆
        boolean passVerify = false;
        Set<Object> codeList = redisUtil.sGet(VERIFYCODE);
        Iterator<Object> iterator = codeList.iterator();
        while (iterator.hasNext()) {
            String vCode = (String) iterator.next();
            String c = vCode.substring(0, vCode.length() - 13);
            String time = vCode.substring(4);
            Long t = Long.valueOf(time);
            if (code.equalsIgnoreCase(c) && (System.currentTimeMillis() - t) / 1000 < 300) {
                passVerify = true;
            }
        }
        if (!passVerify) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "验证码错误或者过期，请重试！");
        }
        User u = (User) transferJson.transferToClz(consumerFeignClient.getUserByNum(user.getNum()).getData().toString(), User.class);
        if (u == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "用户不存在，请重试");
        try {
            String realPwd = RSAUtil.decrypt(u.getPassword(), privateKey);
            //通过把用户的token封装到jwt中来实现登陆状态的认证
            List<Integer> rids = (List<Integer>) transferJson.transferToClz(consumerFeignClient.getRidsByUid(u.getId()).getData().toString()
                    , List.class);
            MyToken t = new MyToken(u.getId(), rids);
            //把自定义的token封装为json格式
            String json = objectMapper.writeValueAsString(t);
            //对json格式再进行加密，防止被拦截之后以明文形式存在
            String secreteJson = RSAUtil.encrypt(json, publicKey);
            String token = JWT.create()
                    //签发时间
                    .withIssuedAt(new Date())
                    //设置token两小时过期
//                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 120))

                    //测试jwt刷新，过期时间设置为1分钟
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 120))
                    //传输的内容
                    .withAudience(secreteJson)
                    .sign(Algorithm.HMAC256(publicKey));
            if (realPwd.equals(user.getPassword())) return new CommonResult(200, "登陆成功，返回token", token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CommonResult(401, "密码错误");
    }

    /**
     * token的续期，返回一个新时间的token，只做了更新过期时间处理
     * @param token
     * @return
     */
    @GetMapping("renew")
    public CommonResult renewToken(@RequestHeader(value = "authorization") String token) {
        log.info("刷新token中获取authorization：" + token);
        //获取到了旧token之后,取出payload，设置新token返回
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(publicKey)).build();
        try {
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "token校验失败!");
        }
        DecodedJWT jwt = JWT.decode(token);
        String auth = jwt.getAudience().get(0);
        String res = JWT.create()
                //签发时间
                .withIssuedAt(new Date())
                //续期token，用1分钟来测试
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 120))
                //保持主要内容一致
                .withAudience(auth)
                .sign(Algorithm.HMAC256(publicKey));
        return new CommonResult(200,"token续期成功",res);
    }
}
