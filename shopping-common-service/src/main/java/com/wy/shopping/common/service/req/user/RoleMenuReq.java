package com.wy.shopping.common.service.req.user;

import com.wy.shopping.common.service.req.AbstractReq;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author wy
 * @description
 * @date 2019-05-18
 */
@Getter
@Setter
@ToString
@Builder
public class RoleMenuReq extends AbstractReq {
    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 菜单ID列表
     */
    private List<String> menuIdList;
}
