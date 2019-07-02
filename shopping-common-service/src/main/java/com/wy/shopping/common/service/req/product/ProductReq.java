package com.wy.shopping.common.service.req.product;

import com.wy.shopping.common.service.enumeration.OrderEnum;
import com.wy.shopping.common.service.enumeration.product.ProdStateEnum;
import com.wy.shopping.common.service.req.AbstractReq;

/**
 * @author wy
 * @date 2019-07-02
 * @description
 */
public class ProductReq extends AbstractReq {
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
    private String topCateEntityID;

    /**
     * 产品所属二级分类
     */
    private String subCategEntityID;

    /**
     * 产品所属品牌
     */
    private String brandEntityID;

    /**
     * 是否上架 {@link ProdStateEnum}
     */
    private Integer prodState;

    /**
     * 产品详情
     */
    private String content;

    /**
     * 产品所属企业信息
     */
    private String companyEntityID;

    /**
     * 本店价格下限
     */
    private String shopPriceStart;
    /**
     * 本店价格上限
     */
    private String shopPriceEnd;

    /**
     * 一级类别id
     */
    private String topCategoryId;
    /**
     * 二级类别id
     */
    private String subCategoryId;

    /**
     * 品牌id
     */
    private String brandId;

    /**
     * 产品状态码 {@link ProdStateEnum}
     */
    private Integer prodStateCode;

    /**
     * 公司id
     */
    private String companyId;


    /**
     * 根据价格排序 {@link OrderEnum}
     */
    private Integer orderByPrice;

    /**
     * 根据销量排序 {@link OrderEnum}
     */
    private Integer orderBySales;
}
