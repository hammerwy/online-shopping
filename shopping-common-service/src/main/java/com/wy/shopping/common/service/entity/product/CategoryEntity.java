package com.wy.shopping.common.service.entity.product;

import lombok.*;

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
public class CategoryEntity {

    /**
     * 主键
     */
    private String id;

    /**
     * 分类名称
     */
    private String category;

    /**
     * 父分类id (一级分类的parentId为空)
     */
    private String parentId;

    /**
     * 排序（权值越高，排名越前）
     */
    private int sort;
}
