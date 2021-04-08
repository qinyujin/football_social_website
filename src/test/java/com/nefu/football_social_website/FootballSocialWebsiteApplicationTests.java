package com.nefu.football_social_website;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.football_social_website.entity.User;
import com.nefu.football_social_website.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FootballSocialWebsiteApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void test1(){
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        for (User user : users) {
            System.out.println(user);
        }
    }

}
