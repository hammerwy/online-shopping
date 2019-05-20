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
public class ProdImageEntity {

    /**
     * 主键
     */
    private String id;

    /**
     * 图片的URL
     */
    private String imageURL;

    /**
     * 图片所属产品的ID
     */
    private String productId;
}
