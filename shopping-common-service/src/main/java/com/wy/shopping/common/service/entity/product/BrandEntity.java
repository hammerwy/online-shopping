package com.wy.shopping.common.service.entity.product;

import com.wy.shopping.common.service.entity.user.UserEntity;
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
public class BrandEntity{

    /** 主键 */
    private String id;

    /** 产品品牌名称 */
    private String brand;

    /** 品牌logo url*/
    private String brandLogoUrl;

    /** 品牌所属企业 */
    private UserEntity companyEntity;

    /** 品牌排序（权值越高，排名越前） */
    private int sort;
}
