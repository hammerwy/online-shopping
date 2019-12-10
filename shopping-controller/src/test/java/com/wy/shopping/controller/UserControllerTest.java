package com.wy.shopping.controller;

import com.wy.shopping.common.service.facade.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * @author wangyong
 * @date 2019/11/27
 * @description
 */
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Test
    void test1() {


        MockMvc build = standaloneSetup(UserController.class).build();
    }
}