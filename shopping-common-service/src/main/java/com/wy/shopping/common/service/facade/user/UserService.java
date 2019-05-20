package com.wy.shopping.common.service.facade.user;

import com.wy.shopping.common.service.entity.user.UserEntity;
import com.wy.shopping.common.service.req.user.LoginReq;

/**
 * @author wy
 * @description
 * @date 2019-05-10
 */
public interface UserService {
    UserEntity login(LoginReq loginReq);
    String test();
}
