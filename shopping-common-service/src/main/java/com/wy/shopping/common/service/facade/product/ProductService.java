package com.wy.shopping.common.service.facade.product;

import com.wy.shopping.common.service.entity.product.BrandEntity;
import com.wy.shopping.common.service.entity.product.CategoryEntity;
import com.wy.shopping.common.service.entity.product.ProductEntity;
import com.wy.shopping.common.service.req.product.BrandReq;
import com.wy.shopping.common.service.req.product.CategoryReq;
import com.wy.shopping.common.service.req.product.ProductReq;
import com.wy.shopping.common.service.resp.Result;

import java.util.List;

/**
 * @author wy
 * @date 2019-07-02
 * @description
 */
public interface ProductService {
    /**
     * 新增产品
     * @param productReq
     * @return
     */
    Result createProduct(ProductReq productReq);

    /**
     * 增量更新产品
     * @param productReq
     * @return
     */
    Result updateProduct(ProductReq productReq);

    /**
     * 多条件查询所有产品（支持分页）
     * @param prodQueryReq
     * @return
     */
    Result<List<ProductEntity>> findProducts(ProductReq prodQueryReq);

    /**
     * 新增类别
     * @param categoryEntity
     * @return
     */
    Result createCategoty(CategoryEntity categoryEntity);

    /**
     * 增量更新类别
     * @param categoryEntity
     * @return
     */
    Result modifyCategory(CategoryEntity categoryEntity);

    /**
     * 删除类别
     * @param categoryId
     * @return
     */
    Result deleteCategory(String categoryId);

    /**
     * 新增品牌
     * @param brandInsertReq
     * @return
     */
    Result createBrand(BrandReq brandInsertReq);

    /**
     * 增量更新品牌
     * @param brandInsertReq
     * @return
     */
    Result modifyBrand(BrandReq brandInsertReq);

    /**
     * 多条件查询所有类别（支持分页）
     * @param categoryQueryReq
     * @return
     */
    Result<List<CategoryEntity>> findCategorys(CategoryReq categoryQueryReq);

    /**
     * 多条件查询所有品牌（支持分页）
     * @param brandReq
     * @return
     */
    Result<List<BrandEntity>> findBrands(BrandReq brandReq);
}
