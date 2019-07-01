package com.wy.shopping.common.service.req.product;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wy
 */
@Getter
@Setter
public class BrandReq {
    /**
     * 主键
     */
    private String id;
    /**
     * 产品品牌名称
     */
    private String brand;
    /**
     * 品牌logo url
     */
    private String brandLogoUrl;
    /**
     * 品牌所属企业
     */
    private String companyEntityId;
    /**
     * 品牌排序（权值越高，排名越前）
     */
    private int sort;
}
