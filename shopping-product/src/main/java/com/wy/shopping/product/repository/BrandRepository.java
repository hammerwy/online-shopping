package com.wy.shopping.product.repository;

import com.wy.shopping.common.service.entity.product.BrandEntity;
import com.wy.shopping.common.service.req.product.BrandReq;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wy
 * @date 2019-07-01
 * @description
 */
@Repository
public interface BrandRepository {
    /**
     * 新增品牌
     *
     * @param brandInsertReq
     * @return
     */
    int createBrand(BrandReq brandInsertReq);

    /**
     * 增量更新品牌
     *
     * @param brandInsertReq
     */
    int updateBrand(BrandReq brandInsertReq);


    /**
     * 查询所有品牌（支持分页）
     *
     * @param brandQueryReq
     * @return
     */
    List<BrandEntity> findBrands(BrandReq brandQueryReq);
}
