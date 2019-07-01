package com.wy.shopping.product.repository;

import java.util.List;

/**
 * @author wy
 * @date 2019-07-01
 * @description
 */
public class BrandRepository {
    /**
     * 新增品牌
     * @param brandInsertReq
     * @return
     */
    int createBrand(BrandInsertReq brandInsertReq);

    /**
     * 增量更新品牌
     * @param brandInsertReq
     */
    int updateBrand(BrandInsertReq brandInsertReq);


    /**
     * 查询所有品牌（支持分页）
     * @param brandQueryReq
     * @return
     */
    List<BrandEntity> findBrands(BrandQueryReq brandQueryReq);
}
