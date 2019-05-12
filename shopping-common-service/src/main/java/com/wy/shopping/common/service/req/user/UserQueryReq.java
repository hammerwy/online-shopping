package com.wy.shopping.common.service.req.user;

import com.wy.shopping.common.service.enumeration.OrderEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wy
 * @description
 * @date 2019-05-10
 */
@Getter
@Setter
@Builder
public class UserQueryReq {
    /**
     * 主键
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 注册时间(起始)
     */
    private String registerTimeStart;
    /**
     * 注册时间(结束)
     */
    private String registerTimeEnd;

    /**
     * 用户类型 {@link com.wy.shopping.common.service.enumeration.user.UserTypeEnum}
     */
    private Integer userType;

    /**
     * 账号状态 {@link com.wy.shopping.common.service.enumeration.user.UserStateEnum }
     */
    private Integer userState;

    /**
     * 用户角色ID
     */
    private String roleId;

    /**
     * 根据注册时间排序 {@link OrderEnum}
     */
    private Integer orderByRegisterTime;
}
