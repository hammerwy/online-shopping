package com.wy.shopping.common.service.utils;

import java.util.UUID;

/**
 * @author wy
 * @description
 * @date 2019-05-20
 */
public class KeyGenerator {
    /**
     * uuid
     *
     * @return 去掉"-"的uuid
     */
    public static String generateKey() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(generateKey().length());
    }
}
