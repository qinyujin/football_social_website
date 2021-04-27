package com.nefu.login.controller;

import com.nefu.login.component.CommonResult;
import com.nefu.login.entity.User;
import com.nefu.login.feign.ConsumerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :覃玉锦
 * @create :2021-04-27 10:14:00
 * 用户注册
 */
@RestController
@RequestMapping("/api/")
public class RegisterController {

    @Autowired
    private ConsumerFeignClient consumerFeignClient;

    @PostMapping("register")
    public CommonResult register(@RequestBody User user){
        return consumerFeignClient.saveUser(user).getCode() == 200 ?
                new CommonResult(200,"注册成功")
                :new CommonResult(401,"该账号已存在，请换一个账号重试");
    }
}
