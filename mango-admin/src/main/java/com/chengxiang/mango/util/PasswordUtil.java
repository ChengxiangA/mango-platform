package com.chengxiang.mango.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 密码工具类
 * 暴露使用，而PasswordEncoder不暴露使用
 */
public class PasswordUtil {

    /**
     * 用来判断用户密码是否正确
     * @param salt 盐值
     * @param rawPass 原码
     * @param encPass 密文
     * @return
     */
    public static boolean matches(String salt,String rawPass,String encPass) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return encPass.equals(new PasswordEncoder(salt).encode(rawPass));
    }

    /**
     * 加密
     * @param salt
     * @param rawPass
     * @return
     */
    public static String encode(String salt,String rawPass) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return new PasswordEncoder(salt).encode(rawPass);
    }

    /**
     * 得到随机盐值，需要写入到数据库中
     * @return
     */
    public static String getRandomSalt() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0,20);
    }

}
