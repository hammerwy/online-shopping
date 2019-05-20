package com.wy.shopping.common.service.enumeration.product;

import com.wy.shopping.common.service.enumeration.BaseEnum;
import lombok.Getter;

/**
 * @author 大闲人柴毛毛
 * @date 2017/10/31 下午3:42
 * @description 产品状态的枚举类
 */
@Getter
public enum ProdStateEnum implements BaseEnum {

    /**
     * 上架
     */
    OPEN(1, "上架"),
    /**
     * 下架
     */
    CLOSE(2, "下架");

    private int code;
    private String message;

    ProdStateEnum(int code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
