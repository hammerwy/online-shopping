package com.wy.shopping.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author wy
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ShoppingConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShoppingConfigApplication.class, args);
    }
}
