package com.wy.shopping.common.service.entity.order;

import com.wy.shopping.common.service.enumeration.order.OrderStateEnum;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

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
public class OrderStateTimeEntity {

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 订单状态
     */
    private OrderStateEnum orderStateEnum;

    /**
     * 时间
     */
    private Timestamp time;
}
