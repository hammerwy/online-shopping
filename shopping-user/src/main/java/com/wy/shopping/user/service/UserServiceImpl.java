package com.wy.shopping.user.service;

import com.wy.shopping.common.service.entity.UserEntity;
import com.wy.shopping.common.service.facade.user.UserService;
import com.wy.shopping.common.service.req.user.LoginReq;
import com.wy.shopping.user.service.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangyong
 * @description
 * @date 2019-05-09
 */
@org.apache.dubbo.config.annotation.Service(version = "1.0.0")
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Override
    public UserEntity login(LoginReq loginReq) {
        return null;
    }

    @Override
    public String test() {
        return "hello world!";
    }
}
