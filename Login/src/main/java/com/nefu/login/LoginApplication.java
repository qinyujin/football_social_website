package com.nefu.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author :覃玉锦
 * @create :2021-04-09 14:09:00
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
public class LoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }
}
