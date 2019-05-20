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
@Setter
@Getter
@ToString
@Builder
public class MenuEntity {
    /** 主键 */
    private String id;

    /** 菜单名称 */
    private String menuName;

    /** 菜单对应页面的URL */
    private String url;

    /** 父菜单的ID */
    private String parentId;
}
