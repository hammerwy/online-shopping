package com.wy.shopping.common.service.req.user;


import com.wy.shopping.common.service.req.AbstractReq;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wy
 * @description 用户登录处理请求
 * @date 2019-05-10
 */
@Getter
@Setter
@ToString
@Builder
public class AdminCreateReq extends AbstractReq {

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
     * 角色ID
     */
    private String roleId;

}
