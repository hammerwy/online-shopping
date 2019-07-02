package com.wy.shopping.common.service.req.product;

import lombok.*;

/**
 * @author wy
 * @date 2019-07-02
 * @description
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryReq {
    /** 类别id */
    private String id;

    /** 类别名称（模糊匹配） */
    private String categoryName;

    /** 父分类id (一级分类的parentId为空) */
    private String parentId;
}
