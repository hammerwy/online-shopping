package com.wy.shopping.common.service.entity.user;

import com.wy.shopping.common.service.enumeration.user.UserStateEnum;
import com.wy.shopping.common.service.enumeration.user.UserTypeEnum;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author wy
 * @description
 * @date 2019-05-10
 */
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
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
     * 营业执照照片
     */
    private String licencePic;

    /**
     * 注册时间
     */
    private LocalDateTime registerTime;

    /**
     * 用户类型 {@link com.wy.shopping.common.service.enumeration.user.UserTypeEnum}
     */
    private UserTypeEnum userTypeEnum;

    /**
     * 账号状态 {@link com.wy.shopping.common.service.enumeration.user.UserStateEnum }
     */
    private UserStateEnum userStateEnum;

    /**
     * 用户的角色
     */
    private RoleEntity roleEntity;
}
