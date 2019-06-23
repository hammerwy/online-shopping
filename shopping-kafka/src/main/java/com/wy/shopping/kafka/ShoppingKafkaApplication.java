package com.wy.shopping.kafka;

import com.wy.shopping.kafka.Service.Sender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class ShoppingKafkaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingKafkaApplication.class, args);
    }

    @Resource
    private Sender sender;

    @Override
    public void run(String... args) throws Exception {
        sender.sendMsg(1);
        sender.sendMsg(2);
        sender.sendMsg(3);
        sender.sendMsg(4);
        sender.sendMsg(5);
    }
}
