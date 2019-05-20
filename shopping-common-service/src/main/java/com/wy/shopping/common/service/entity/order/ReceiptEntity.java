package com.wy.shopping.common.service.entity.order;

import com.wy.shopping.common.service.enumeration.order.ReceiptTypeEnum;
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
public class ReceiptEntity {

    /**
     * 主键
     */
    private String id;

    /**
     * 发票类型
     */
    private ReceiptTypeEnum receiptTypeEnum;

    /**
     * 发票抬头
     */
    private String title;

    /**
     * 纳税人识别号
     */
    private String taxpayerNo;

    /**
     * 发票内容
     */
    private String content;
}
