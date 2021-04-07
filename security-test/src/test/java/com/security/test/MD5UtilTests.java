package com.security.test;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;

/**
 * @author dick <18668485565@163.com>
 * @version V1.0.0
 * @description
 * @date created on 2020/5/12
 */
public class MD5UtilTests {

    private final static char[] HEX_DIGITS =  {'0', '1', '2', '3', '4','5','6','7','8','9','A','B','C','D','E','F'};

    public static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        String str = "tianbang"+"tianbang123";
        System.out.println(MD5(str));
    }
}
