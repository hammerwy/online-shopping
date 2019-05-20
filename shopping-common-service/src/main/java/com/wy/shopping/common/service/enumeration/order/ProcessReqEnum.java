package com.wy.shopping.common.service.enumeration.order;

import com.wy.shopping.common.service.enumeration.BaseEnum;
import lombok.Getter;

/**
 * @author wy
 * @description
 * @date 2019-05-18
 */
@Getter
public enum ProcessReqEnum implements BaseEnum {

    /**
     * 订单受理
     */
    PlaceOrder(1, "PlaceOrderProcessor"),
    Pay(2, "PayProcessor"),
    CancelOrder(3, "CancelOrderProcessor"),
    ConfirmDelivery(4, "ConfirmDeliveryProcessor"),
    ConfirmReceive(5, "ConfirmReceiveProcessor");

    private int code;
    private String message;

    ProcessReqEnum(int code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
