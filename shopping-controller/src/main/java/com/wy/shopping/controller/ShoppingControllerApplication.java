package com.wy.shopping.controller;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wy
 */
@EnableDubbo
@SpringBootApplication
public class ShoppingControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingControllerApplication.class, args);
    }

}
