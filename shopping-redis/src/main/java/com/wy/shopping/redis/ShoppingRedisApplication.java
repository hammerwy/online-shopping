package com.wy.shopping.redis;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class ShoppingRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingRedisApplication.class, args);
    }

}
