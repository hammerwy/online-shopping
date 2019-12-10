package com.wy.shopping.common.service.entity.product;

import com.wy.shopping.common.service.entity.user.UserEntity;
import com.wy.shopping.common.service.enumeration.product.ProdStateEnum;
import lombok.*;

import java.util.List;

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
public class ProductEntity {

    /**
     * 产品ID（主键）
     */
    private String id;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 市场价（保留两位小数，使用字符串类型存储，计算时将其转为数值型）
     */
    private String marketPrice;

    /**
     * 本店价
     */
    private String shopPrice;

    /**
     * 库存
     */
    private int stock;

    /**
     * 销量
     */
    private int sales;

    /**
     * 产品重量
     */
    private String weight;

    /**
     * 产品所属一级分类
     */
    private CategoryEntity topCateEntity;

    /**
     * 产品所属二级分类
     */
    private CategoryEntity subCategEntity;

    /**
     * 产品所属品牌
     */
    private BrandEntity brandEntity;

    /**
     * 是否上架 {@link com.wy.shopping.common.service.enumeration.product.ProdStateEnum}
     */
    private ProdStateEnum prodStateEnum;

    /**
     * 产品图片
     */
    private List<ProdImageEntity> prodImageEntityList;

    /**
     * 产品详情
     */
    private String content;

    /**
     * 产品所属企业信息
     */
    private UserEntity companyEntity;
}
