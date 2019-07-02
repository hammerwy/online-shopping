package com.wy.shopping.product.repository;

import com.wy.shopping.common.service.entity.product.CategoryEntity;
import com.wy.shopping.common.service.req.product.CategoryReq;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wy
 * @date 2019-07-02
 * @description
 */
@Repository
public interface CategoryRepository {
    /**
     * 新增类别
     * @param categoryEntity
     * @return
     */
    Integer createCategoty(CategoryEntity categoryEntity);

    /**
     * 修改类别,增量更新
     * @param categoryEntity
     * @return
     */
    Integer updateCategory(CategoryEntity categoryEntity);

    /**
     * 删除类别
     * @param categoryId
     * @return
     */
    Integer deleteCategory(String categoryId);

    /**
     * 查询所有类别（分页）
     * @param categoryQueryReq
     */
    List<CategoryEntity> findCategorys(CategoryReq categoryQueryReq);
}
