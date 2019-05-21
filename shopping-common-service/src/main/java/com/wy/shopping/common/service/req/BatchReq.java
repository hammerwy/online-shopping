package com.wy.shopping.common.service.req;

import lombok.*;

import java.util.List;

/**
 * @author wy
 * @description
 * @date 2019-05-20
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BatchReq<T> extends AbstractReq {
    private List<T> reqList;
}
