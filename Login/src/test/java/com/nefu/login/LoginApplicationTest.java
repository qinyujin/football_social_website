package com.nefu.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nefu.login.component.CommonResult;
import com.nefu.login.entity.User;
import com.nefu.login.feign.ConsumerFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author :覃玉锦
 * @create :2021-04-09 14:21:00
 */
@SpringBootTest
class LoginApplicationTest {
    @Autowired
    private ConsumerFeignClient consumerFeignClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test1(){
        CommonResult user = consumerFeignClient.getUserByNum("001");
        User u = (User) user.getData();
        System.out.println(u);

        /*CommonResult res = consumerFeignClient.getRidsByUid(3);
        List<Integer> rids = (List<Integer>) res.getData();
        System.out.println(rids);*/
    }

    @Test
    void test2(){
        System.out.println(System.currentTimeMillis());
    }
}