package com.nefu.gateway.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nefu.gateway.component.MyToken;
import com.nefu.gateway.component.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 18:27:00
 */
@Component
@Slf4j
public class LoginGlobalFilter implements GlobalFilter, Ordered {
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${privateKey}")
    private String privateKey;

    @Value("${publicKey}")
    private String publicKey;

    private static List<String> white =initWhite();

    public static ArrayList<String> initWhite(){
        ArrayList<String> whiteList = new ArrayList<>();
        whiteList.add("/api/verifyCode");
        whiteList.add("/api/login");
        return whiteList;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("进入网关登录拦截器");
        RequestPath path = exchange.getRequest().getPath();
        log.info("请求路径:"+path.toString());

        log.info("请求是否在白名单中:"+white.contains(path.toString()));
        //给登录的api放行
        if(white.contains(path.toString()))
            return chain.filter(exchange);

        //其他的api请求，如果header没有认证信息，不给放行
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        List<String> authorization = headers.get("authorization");
        if(authorization==null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"未登录!");
        }
        String token = authorization.get(0);

        //校验token是否合法
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(publicKey)).build();
        try {
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"token校验失败!");
        }

        DecodedJWT jwt = JWT.decode(token);
        String auth = jwt.getAudience().get(0);
        String decryToken = null;
        try {
            decryToken = RSAUtil.decrypt(auth, privateKey);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "数据被篡改！");
        }

        //获取uid、rids
        MyToken myToken = null;
        try {
            myToken = objectMapper.readValue(decryToken, MyToken.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"数据被篡改！");
        }

        //将uid、rids、workId存入header中，提供给其他微服务
        ServerHttpRequest header = request.mutate()
                .header("uid", String.valueOf(myToken.getUid()))
                .header("rids", myToken.getRids().toString())
                .build();
        log.info("在过滤器中修改请求头："+header.getHeaders().toString());
        ServerWebExchange exchange1 = exchange.mutate().request(header).build();

        return chain.filter(exchange1);
    }

    @Override
    public int getOrder() {
        return -999999;
    }
}
