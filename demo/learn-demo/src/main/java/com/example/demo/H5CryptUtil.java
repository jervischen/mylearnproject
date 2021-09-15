package com.example.demo;

import com.example.demo.util.CryptUtil;

/**
 * Created in 2018-05-25 16:26.
 *
 * @author chenxiao
 */
public class H5CryptUtil {

    private final static byte[] E_KEY = new byte[] { 'l', 'z', 'h', '5', 's', 'e', 'c', 'u', 'r', 'i', 't', 'y' };

    /**
     * 加密
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static String encrypt(String value) {
        return CryptUtil.encrypt(value, E_KEY);
    }

    /**
     * 解密
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static String decrypt(String value) {
        return CryptUtil.decrypt(value, "socialH5manager".getBytes());
    }

    public static void main(String[] args) {
        System.out.println(decrypt("ff79bbe903d6438fc7bce6e9f9aad40162d4eacf18134bd94f7f63ef6b8c6f1d396afb4c62b7c4c3"));
    }
}
