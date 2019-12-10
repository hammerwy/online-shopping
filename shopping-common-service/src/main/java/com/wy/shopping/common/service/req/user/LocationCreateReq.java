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
public class LocationCreateReq extends AbstractReq {
    /**
     * 详细地址
     */
    private String location;

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 收货人手机号
     */
    private String phone;

    /**
     * 邮编
     */
    private String postCode;
}
