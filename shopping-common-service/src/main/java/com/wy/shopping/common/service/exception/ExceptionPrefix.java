package com.wy.shopping.common.service.exception;

/**
 * @author wy
 * @description
 * @date 2019-05-18
 */
public interface ExceptionPrefix {
    /**
     * 通用异常码前缀
     */
    String ComExpPrefix = "10";

    /**
     * User模块异常码前缀
     */
    String UserExpPrefix = "20";

    /**
     * Product模块异常码前缀
     */
    String ProdExpPrefix = "30";

    /**
     * Order模块异常码前缀
     */
    String OrderExpPrefix = "40";

    /**
     * Analysis模块异常码前缀
     */
    String AnlsExpPrefix = "50";
}
