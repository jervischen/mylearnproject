package com.example.demo;

import com.google.common.base.Strings;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.nio.charset.IllegalCharsetNameException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created in 2018-01-08 14:39.
 *
 * @author chenxiao
 */
public class TestJava {
    private static Logger logger = LoggerFactory.getLogger(TestJava.class);

    public static void main(String[] args) {
        String localIp = System.getProperty("local.ip");
        System.out.println(localIp);

        float i = (float) 1000010 / 10000;
        System.out.println(i);
        int j = 100;
        float jj = j;
        System.out.println(jj);

        float wf = 100.1f;
        int w = 100;
        System.out.println(w);
        System.out.println((int) wf);
        System.out.println(w >= wf);
    }

    @Test
    public void testFloat() {
        int j = 100;
        float jj = j;
        System.out.println(j);
        System.out.println(j / 100);
        System.out.println(jj);
        System.out.println(jj / 100);
    }

    @Test
    public void testExcepiton() {
        DateTime dateTime = new DateTime("2018-01-27");
        System.out.println(dateTime.toDate().getTime());
    }


    @Test
    public void testLevel() {
        System.out.println((long) (1 + 98 * 0.05) * 150 * 99 * 99 * 99 + (450 * 99));
    }

    @Test
    public void testDouble() {
        float v = (float) 20 / 600;
        System.out.println(v);
    }

    @Test
    public void testMD() {
        System.out.println(DigestUtils.md5Hex("familymanager"));
    }

    @Test
    public void testSub() {
        System.out.println(-1 - 3);
    }

    @Test
    public void testTime() {
        System.out.println(System.currentTimeMillis() - 600 * 1000);
    }


    @Test
    public void testStirng(){
        String msg =String.format("抱歉，您的红包口令「%s」因不符合荔枝APP《信息存储空间服务协议》已被删除，如红包内仍有未领取的金币将在24小时内退回。"
                ,"555");
        System.out.println(msg);
    }

    @Test
    public void testAssert(){

    }

}
