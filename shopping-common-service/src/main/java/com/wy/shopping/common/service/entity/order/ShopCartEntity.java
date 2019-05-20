package com.wy.shopping.common.service.entity.order;

import com.wy.shopping.common.service.entity.product.ProductEntity;
import lombok.*;

import java.sql.Timestamp;

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
public class ShopCartEntity {

    /**
     * 主键
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 产品详情（在数据库中以product_id表示）
     */
    private ProductEntity productEntity;

    /**
     * 数量
     */
    private int count;

    /**
     * 添加时间
     */
    private Timestamp time;
}
