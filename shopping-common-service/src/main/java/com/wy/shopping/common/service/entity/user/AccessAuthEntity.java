package com.wy.shopping.common.service.entity.user;

import com.wy.shopping.common.service.enumeration.HttpMethodEnum;
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
public class AccessAuthEntity {

    /**
     * 请求 URL
     */
    private String url;

    /**
     * 接口方法名
     */
    private String methodName;

    /**
     * HTTP 请求方式
     */
    private HttpMethodEnum httpMethodEnum;

    /**
     * 当前接口是否需要登录
     */
    private boolean isLogin;

    /**
     * 当前接口的访问权限
     */
    private String permission;
}
