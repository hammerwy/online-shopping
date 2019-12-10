package com.wy.shopping.common.service.entity.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wy
 * @description
 * @date 2019-05-10
 */
@Getter
@Setter
@ToString
@Builder
public class PermissionEntity {
    /**
     * 主键
     */
    private String id;

    /**
     * 权限名称
     */
    private String permission;

    /**
     * 权限描述
     */
    private String desc;
}
