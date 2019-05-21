package com.wy.shopping.common.service.req.user;

import com.wy.shopping.common.service.enumeration.user.UserStateEnum;
import com.wy.shopping.common.service.req.AbstractReq;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wy
 * @description
 * @date 2019-05-20
 */
@Getter
@Setter
@Builder
@ToString
public class UserStateReq extends AbstractReq {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户状态 {@link UserStateEnum}
     */
    private int userState;
}
