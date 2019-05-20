package com.wy.shopping.common.service.entity.order;

import com.wy.shopping.common.service.entity.product.ProductEntity;
import lombok.*;

import java.io.Serializable;

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
public class ProductOrderEntity {

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 产品详情
     */
    private ProductEntity productEntity;

    /**
     * 产品数量
     */
    private int count;
}
