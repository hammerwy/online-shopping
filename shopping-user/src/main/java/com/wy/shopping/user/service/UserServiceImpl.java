package com.wy.shopping.user.service;

import com.wy.shopping.common.service.entity.user.UserEntity;
import com.wy.shopping.common.service.exception.ExceptionCodeEnum;
import com.wy.shopping.common.service.facade.user.UserService;
import com.wy.shopping.common.service.req.user.LoginReq;
import com.wy.shopping.common.service.req.user.UserQueryReq;
import com.wy.shopping.common.service.utils.Assert;
import com.wy.shopping.user.service.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 用户登录
     *
     * @param loginReq
     * @return
     */
    @Override
    public UserEntity login(LoginReq loginReq) {
        //校验参数
        checkParam(loginReq);
        /*
        查询用户是否已经存在
         */
        UserQueryReq userQueryReq = buildUserQueryReq(loginReq);
        List<UserEntity> userEntityList = userRepository.findUsers(userQueryReq);
        Assert.startCheck().notEmpty(userEntityList, ExceptionCodeEnum.LOGIN_FAIL);
        return userEntityList.get(0);
    }

    /**
     * 查找用户
     *
     * @param userQueryReq 查找条件
     * @return 用户实体列表
     */
    public List<UserEntity> findUsers(UserQueryReq userQueryReq) {
        return userRepository.findUsers(userQueryReq);
    }

    private UserQueryReq buildUserQueryReq(LoginReq loginReq) {
        return UserQueryReq.builder()
                .password(loginReq.getPassword())
                .username(loginReq.getUsername())
                .mail(loginReq.getMail())
                .phone(loginReq.getPhone())
                .build();
    }

    /**
     * 注册信息参数校验
     *
     * @param loginReq 注册信息参数
     */
    private void checkParam(LoginReq loginReq) {
        Assert.startCheck(loginReq, ExceptionCodeEnum.PARAM_NULL)
                /*
                密码不能为空
                */
                .notEmpty(loginReq.getPassword(), ExceptionCodeEnum.PARAM_NULL)
                /*
                手机、mail、用户名、至少要有一个
                 */
                .anyNotEmpty(ExceptionCodeEnum.AUTH_NULL, loginReq.getUsername(), loginReq.getMail(), loginReq.getPhone());
    }

    @Override
    public String test() {
        return "hello world!";
    }
}
