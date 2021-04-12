package com.nefu.gateway.feign;

import com.nefu.gateway.component.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author :覃玉锦
 * @create :2021-04-12 11:07:00
 */
@FeignClient(value = "MVC-server")
public interface UserConsumerFeignClient {
    @GetMapping("/api/user/permissions")
    CommonResult getPermissions();

    @GetMapping("/api/user/role_permission")
    CommonResult getRolePermissions();
}

