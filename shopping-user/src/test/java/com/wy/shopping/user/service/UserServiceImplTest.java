package com.wy.shopping.user.service;

import com.wy.shopping.common.service.annotation.EnableSqlPrint;
import com.wy.shopping.common.service.entity.user.UserEntity;
import com.wy.shopping.common.service.enumeration.user.UserTypeEnum;
import com.wy.shopping.common.service.facade.user.UserService;
import com.wy.shopping.common.service.req.BatchReq;
import com.wy.shopping.common.service.req.user.LoginReq;
import com.wy.shopping.common.service.req.user.RegisterReq;
import com.wy.shopping.common.service.req.user.UserQueryReq;
import com.wy.shopping.common.service.req.user.UserStateReq;
import org.apache.dubbo.config.annotation.Reference;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wy
 * @description
 * @date 2019-05-21
 */
//@EnableSqlPrint
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Reference(version = "1.0.0")
    private UserService userService;

    @Test
    public void login() {
        UserEntity userEntity = userService.login(LoginReq.builder().username("wy").password("123456").build());
        System.out.println(userEntity);
    }

    @Test
    public void register() {
        RegisterReq registerReq = RegisterReq.builder()
                .username("wy")
                .phone("18711112222")
                .password("123456")
                .mail("asdf@qq.com")
                .userType(UserTypeEnum.PERSON.getCode())
                .build();
        UserEntity userEntity = userService.register(registerReq);
        System.out.println(userEntity);
    }

    @Test
    public void findUsers() {
        List<UserEntity> users = userService.findUsers(UserQueryReq.builder().username("wy").orderByRegisterTime(1).build());
        System.out.println(users);
    }

    @Test
    public void batchUpdateUserState() {
        UserStateReq userStateReq = UserStateReq.builder().userId("USERbca370e923a642cd90c31d9ada90caad").userState(1).build();
        ArrayList<UserStateReq> userStateReqs = Lists.newArrayList(userStateReq);
        BatchReq<UserStateReq> batchReq = new BatchReq<>();
        batchReq.setReqList(userStateReqs);
        userService.batchUpdateUserState(batchReq);
    }
}