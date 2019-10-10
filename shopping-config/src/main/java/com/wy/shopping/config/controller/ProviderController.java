package com.wy.shopping.config.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wy
 * @date 2019/10/10
 */
@RestController
@RequestMapping(value = "/provide")
public class ProviderController {
    @GetMapping(value = "/echo")
    public String echo(String echoStr) {
        return "prefix" + echoStr;
    }
}
