package com.nefu.login.controller;

import com.nefu.login.component.IVerifyCodeGen;
import com.nefu.login.component.RedisUtil;
import com.nefu.login.component.VerifyCode;
import com.nefu.login.component.impl.SimpleCharVerifyCodeGenImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author :覃玉锦
 * @create :2020-09-11 10:19:00
 */
@RestController
@RequestMapping("/api/")
@Slf4j
@CrossOrigin
public class VerifyController {
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("verifyCode")
    public void verifyCode(HttpServletResponse response, HttpServletRequest request) {
        log.info("请求验证码");
        String timestamp = request.getParameter("timestamp");
        log.info("获取时间戳："+timestamp);
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        log.info("获取iVerifyCodeGen:{}",iVerifyCodeGen);
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            log.info("设置长宽成功");
            String code = verifyCode.getCode();
            log.info("即将存入redis");
            //把验证码放入到redis中,并且给验证码设置一个时效，默认5分钟，如果在时效时间则该验证码可以用
            redisUtil.sSetAndTime("VerifyCodeList", 300, code+timestamp);
            log.info("存入成功");
            log.info(code);
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            log.info("生成验证码出现异常:", e);
        }
    }
}