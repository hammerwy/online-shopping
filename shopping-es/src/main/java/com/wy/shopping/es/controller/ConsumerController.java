package com.wy.shopping.es.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author wy
 * @date 2019/10/10
 * @description
 */
@RestController
@RequestMapping(value = "/consume")
public class ConsumerController {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/getConfig")
    public String echo() {
        return restTemplate.getForObject("http://nacos-config/config/localCache", String.class);
    }
}
