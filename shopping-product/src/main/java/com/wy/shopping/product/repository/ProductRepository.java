package com.wy.shopping.product.repository;

import com.wy.shopping.common.service.entity.product.ProductEntity;
import com.wy.shopping.common.service.req.product.ProductReq;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wy
 * @date 2019-07-02
 * @description
 */
@Repository
public interface ProductRepository {
    /**
     * 新增产品
     * @param prodInsertReq
     * @return
     */
    int createProduct(ProductReq prodInsertReq);

    /**
     * 增量更新产品
     * @param prodUpdateReq
     */
    int updateProduct(ProductReq prodUpdateReq);

    /**
     * 多条件查询产品
     * @param prodQueryReq
     * @return
     */
    List<ProductEntity> findProducts(ProductReq prodQueryReq);

    /**
     * 查询制定类别是否被使用
     * @param categoryId
     * @return
     */
    int findUsedCategory(String categoryId);
}
