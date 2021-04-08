package com.nefu.football_social_website;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.nefu.football_social_website.mapper")
public class FootballSocialWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballSocialWebsiteApplication.class, args);
    }

}
