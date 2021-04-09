package com.nefu.mvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author :覃玉锦
 * @create :2021-04-09 10:42:00
 */
@SpringBootApplication
@MapperScan(value = "com.nefu.mvc.mapper")
public class MVCApplication {
    public static void main(String[] args) {
        SpringApplication.run(MVCApplication.class,args);
    }
}
