package com.nefu.login.feign;

import com.nefu.login.component.CommonResult;
import com.nefu.login.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author :覃玉锦
 * @create :2021-04-09 14:18:00
 */
@FeignClient(value = "MVC-server")
public interface ConsumerFeignClient {
    @GetMapping("/api/user/usernum")
    CommonResult getUserByNum(@RequestParam("num") String num);

    @GetMapping("/api/user/rids/{uid}")
    CommonResult getRidsByUid(@PathVariable("uid") int uid);

    @PostMapping("/api/user/user")
    CommonResult saveUser(@RequestBody User user);
}
