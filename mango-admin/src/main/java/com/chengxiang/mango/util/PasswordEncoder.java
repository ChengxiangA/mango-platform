package com.chengxiang.mango.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码加密
 */
public class PasswordEncoder {

    private final static String MD5 = "MD5";

    private final static String SHA = "SHA";

    // 加盐值
    private String salt;
    private String algorithm;

    public PasswordEncoder(String salt) {
        this.salt = salt;
    }

    public PasswordEncoder(String salt,String algorithm) {
        this.salt = salt;
        this.algorithm = algorithm;
    }

    /**
     * @param rowPass 明文密码
     * @return 密文
     */
    public String encode(String rowPass) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(MD5);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] digest = null;
        try {
            digest = md.digest(mergePasswordAndSalt(rowPass).getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return byteArrayToHexString(digest);
    }

    public boolean match(String rowPass,String encPass) {
        try {
            return PasswordUtil.matches(salt,rowPass,encPass);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * 合并盐值和密码
     * @param password
     * @return
     */
    public String mergePasswordAndSalt(String password) {
        if(password == null) {
            password = "";
        }
        if((salt) == null || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt + "}";
        }
    }

    /**
     * MD5字节转为16进制字符串
     * @param digest md5字节
     * @return 16进制字符串
     */
    public String byteArrayToHexString(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for(byte b: digest) {
            sb.append(byteToHexString(b));
        }
        return sb.toString();
    }

    public String byteToHexString(byte b) {
        return Integer.toHexString(b & 0xff);
    }

}
