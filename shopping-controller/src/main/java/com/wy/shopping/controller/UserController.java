package com.wy.shopping.controller;

import com.wy.shopping.common.service.facade.user.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wy
 * @description
 * @date 2019-05-10
 */
@RestController
@RequestMapping(value = "/controller")
public class UserController {
    @Reference(version = "1.0.0")
    private UserService userService;

    @GetMapping(value = "/test")
    @ResponseBody
    public String test() {
        return userService.test();
    }
}
