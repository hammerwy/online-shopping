package com.wy.shopping.common.service.entity.order;

import com.wy.shopping.common.service.entity.user.LocationEntity;
import com.wy.shopping.common.service.entity.user.UserEntity;
import com.wy.shopping.common.service.enumeration.order.OrderStateEnum;
import com.wy.shopping.common.service.enumeration.order.PayModeEnum;
import lombok.*;

import java.io.Serializable;
import java.util.List;

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
public class OrdersEntity {

    /**
     * 订单编号(主键)
     */
    private String id;

    /**
     * 买家ID
     */
    private UserEntity buyer;

    /**
     * 卖家详情
     */
    private UserEntity company;

    /**
     * 产品-订单的关系列表(订单中某一产品的数量)
     */
    private List<ProductOrderEntity> productOrderList;

    /**
     * 订单状态
     */
    private OrderStateEnum orderStateEnum;

    /**
     * 订单到达各个状态的时间
     */
    private List<OrderStateTimeEntity> orderStateTimeList;

    /**
     * 支付方式
     */
    private PayModeEnum payModeEnum;

    /**
     * 订单总金额
     */
    private String totalPrice;

    /**
     * 发票信息
     */
    private ReceiptEntity receiptEntity;

    /**
     * 收货地址
     */
    private LocationEntity locationEntity;

    /**
     * 买家备注信息
     */
    private String remark;

    /**
     * 物流单号
     */
    private String expressNo;
}
