package com.wy.shopping.product.service;

import com.wy.shopping.common.service.entity.product.BrandEntity;
import com.wy.shopping.common.service.entity.product.CategoryEntity;
import com.wy.shopping.common.service.entity.product.ProductEntity;
import com.wy.shopping.common.service.facade.product.ProductService;
import com.wy.shopping.common.service.req.product.BrandReq;
import com.wy.shopping.common.service.req.product.CategoryReq;
import com.wy.shopping.common.service.req.product.ProductReq;
import com.wy.shopping.common.service.resp.Result;
import com.wy.shopping.product.repository.CategoryRepository;
import com.wy.shopping.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wy
 * @date 2019-07-02
 * @description
 */
@Service
@org.apache.dubbo.config.annotation.Service()
public class ProductServiceImpl implements ProductService {

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private ProductRepository productRepository;

    @Override
    public Result createProduct(ProductReq productReq) {
        return null;
    }

    @Override
    public Result updateProduct(ProductReq productReq) {
        return null;
    }

    @Override
    public Result<List<ProductEntity>> findProducts(ProductReq prodQueryReq) {
        return null;
    }

    @Override
    public Result createCategoty(CategoryEntity categoryEntity) {
        return null;
    }

    @Override
    public Result modifyCategory(CategoryEntity categoryEntity) {
        return null;
    }

    @Override
    public Result deleteCategory(String categoryId) {
        return null;
    }

    @Override
    public Result createBrand(BrandReq brandInsertReq) {
        return null;
    }

    @Override
    public Result modifyBrand(BrandReq brandInsertReq) {
        return null;
    }

    @Override
    public Result<List<CategoryEntity>> findCategorys(CategoryReq categoryQueryReq) {
        return null;
    }

    @Override
    public Result<List<BrandEntity>> findBrands(BrandReq brandReq) {
        return null;
    }
}
