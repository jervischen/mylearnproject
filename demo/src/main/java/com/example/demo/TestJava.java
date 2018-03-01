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
    public void testS() {
        System.out.println(StringFilter("12345678932145687一二三四"));

    }

    public String StringFilter(String medalName) {
        if (Strings.isNullOrEmpty(medalName)) {
            return null;
        }

        //勋章名字长度最长为3
        String newMedalName = "";
        String regEx = "^[a-z0-9A-Z\\u4e00-\\u9fa5]+$";
        Pattern p = Pattern.compile(regEx);
        for (char ch : medalName.toCharArray()) {
            Matcher m = p.matcher(String.valueOf(ch));
            if (m.matches()) {
                newMedalName += String.valueOf(ch);
            }

            if (newMedalName.getBytes().length >= 200) {
                break;
            }
        }

        /**
         * 没有汉字，则六个字符
         * 有一个汉字则加3个数字或字母3+3=6
         * 有两个个汉字则加3个数字或字母3+3+3=9
         * utf-8编码汉字为3个字节
         */
        int chineseCharCount = getChineseCharCount(newMedalName);
        if (chineseCharCount == 0) {
            newMedalName = newMedalName.substring(0, newMedalName.length() > 6 ? 6 : newMedalName.length());
        } else if (chineseCharCount == 1) {
            while (newMedalName.getBytes().length > 6) {
                newMedalName = newMedalName.substring(0, newMedalName.length() - 1);
            }
        } else {
            while (newMedalName.getBytes().length > 9) {
                System.out.println(newMedalName.length());
                newMedalName = newMedalName.substring(0, newMedalName.length() - 1);
            }
            System.out.println(newMedalName.length());
        }

        return newMedalName;
    }

    private static int getChineseCharCount(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char tmp = str.charAt(i);
            if ((tmp >= 'A' && tmp <= 'Z') || (tmp >= 'a' && tmp <= 'z')) {
                continue;
            }
            if ((tmp >= '0') && (tmp <= '9')) {
                continue;
            }
            count++;
        }
        return count;
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

    /**
     * 正则表达式，去除所有空格
     */
    @Test
    public void testPatternBlank() {
        // 要验证的字符串
        String str = "biubiu biu";
        // 邮箱验证规则
        String regEx = "\\s+";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);

        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        System.out.println(rs);

        System.out.println(str.replaceAll(regEx,""));
        String s = str.trim().replaceAll("\\s+", "");
        System.out.println(s);
    }

    /**
     * 正则表达式，空格
     */
    @Test
    public void testPatternBlank2() {
        // 要验证的字符串
        String str = " ";
        // 邮箱验证规则
        String regEx = "[\\s]+";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);

        Matcher matcher = pattern.matcher(str);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        System.out.println(rs);

        System.out.println("   ".length());
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
