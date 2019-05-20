package com.wy.shopping.common.service.enumeration.order;

import com.wy.shopping.common.service.enumeration.BaseEnum;
import lombok.Getter;

/**
 * @author wy
 * @description
 * @date 2019-05-18
 */
@Getter
public enum PayModeEnum implements BaseEnum {

    /**
     * pay method
     */
    ALIPAY(1, "支付宝"),
    WECHAT(2, "微信支付"),
    UNIONPAY(3, "银联支付"),
    ;

    private int code;
    private String message;

    PayModeEnum(int code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
