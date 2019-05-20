package com.wy.shopping.common.service.enumeration.order;

import com.wy.shopping.common.service.enumeration.BaseEnum;
import lombok.Getter;

/**
 * @author wy
 * @description
 * @date 2019-05-18
 */
@Getter
public enum ReceiptTypeEnum implements BaseEnum {
    /**
     * 发票类型
     */
    COMMON(1, "普通发票"),
    ONLINE(2, "电子发票"),
    ADDTAX(3, "增值税发票"),
    ;

    private int code;
    private String message;

    ReceiptTypeEnum(int code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
