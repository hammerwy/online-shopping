package com.wy.shopping.common.service.enumeration.order;

import com.wy.shopping.common.service.enumeration.BaseEnum;
import lombok.Getter;

/**
 * @author wy
 * @description
 * @date 2019-05-18
 */
@Getter
public enum OrderStateEnum implements BaseEnum {

    /**
     * 初始状态
     */
    INIT(0, "订单初始态"),
    BUYER_UNPAID(1, "买家未付款"),
    BUYER_PAID(2, "买家已付款/买家待发货"),
    BUYER_RECEIVING(3, "买家待收货"),
    BUYER_SIGNED_UNCONFIRMED(4, "买家已签收 & 买家未确认收货"),
    FINISHED(5, "已完成(已签收 & 已确认收货)"),
    BUYER_APPLYRETURN(6, "买家申请退货中"),
    SELLER_ACCEPTRETURN(7, "卖家同意退货/待买家发货"),
    SELLER_REJECTRETURN_APPLICABLE(8, "卖家拒绝退货 & 买家可申诉"),
    BUYERER_APPEALING(9, "买家申诉中"),
    SELLER_APPEALING(10, "卖家申诉中"),
    FINISHED_APPEAL(11, "申诉已结束"),
    SELLER_RECEIVING(12, "卖家待收货"),
    SELLER_SIGNED_UNCONFIRMED(13, "卖家已签收 & 卖家未确认收货"),
    REFUNDING(14, "退款中"),
    REFUND_SUCCESS(15, "退款成功"),
    REFUND_FAIL(16, "退款失败"),
    FINISHED_RETURN(17, "退货已完成"),
    CANCEL(18, "订单已取消"),
    ;

    private int code;
    private String message;

    OrderStateEnum(int code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
