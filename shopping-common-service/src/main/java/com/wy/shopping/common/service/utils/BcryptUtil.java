package com.wy.shopping.common.service.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author wy
 * @description
 * @date 2019-05-21
 */
public class BcryptUtil {
    /**
     * 密码加密保存到数据库
     *
     * @param password 密码
     * @return 加密后的密码字符串
     */
    public static String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * 解密比较密码是否相同
     *
     * @param password 传过来的密码
     * @param hashwp   从数据库中取出的加密后的密码
     * @return 相同与否
     */
    public static boolean checkpw(String password, String hashwp) {
        return BCrypt.checkpw(password, hashwp);
    }

    public static void main(String[] args) {
        System.out.println(encrypt("1234556").length());
    }
}
