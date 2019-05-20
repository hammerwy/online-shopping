package com.wy.shopping.common.service.entity.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wy
 * @description
 * @date 2019-05-18
 */
@Setter
@Getter
@ToString
@Builder
public class LocationEntity {
    /**
     * 主键
     */
    private String id;

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

    /**
     * 用户id
     */
    private String userId;
}
