package com.example.demo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.IllegalCharsetNameException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created in 2018-01-08 14:39.
 *
 * @author chenxiao
 */
public class TestJava {
    private static Logger logger = LoggerFactory.getLogger(TestJava.class);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        str = br.readLine();
        System.out.println(str);
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

        Double d = 2d;
        double d1 = 2;

        System.out.println(d);
        System.out.println(d1);
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
    public void testStirng() {
        String msg = String.format("抱歉，您的红包口令「%s」因不符合荔枝APP《信息存储空间服务协议》已被删除，如红包内仍有未领取的金币将在24小时内退回。"
                , "555");
        System.out.println(msg);
    }

    @Test
    public void testAssert() {

    }

    @Test
    public void TestSystemIn() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        str = br.readLine();
        System.out.println(str);
    }

    @Test
    public void testBivision() {
        System.out.println(0 / 3);
        System.out.println(0 % 3);
    }

    @Test
    public void testInteger() {
        Integer i = new Integer(129);
        int j = 129;
        System.out.println(i == j);
        System.out.println(i.equals(j));
    }


    @Test
    public void testRedMd5() {
        String password = "5566";
        String result = Hashing.md5().newHasher().putString(password, Charsets.UTF_8).hash().toString()
                .substring(8, 24);
        System.out.println(result);
    }

    @Test
    public void testList() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        System.out.println(list.toString());
        List<String> strings = Arrays.asList(list.toString());

        List<String> listA = new ArrayList<>();
        System.out.println(listA.get(0));
    }

    @Test
    public void testListSort() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(1);
        /**等级倒序排序**/
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        System.out.println(list.toString());
    }

    @Test
    public void testFor() {
        for (int i = 0; i < 10; i += 2) {
            System.out.println(i);
            System.out.println(i + 1);
        }
    }

    @Test
    public void testDate() {

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sd.parse("2018-04-10 11:00");
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testYu() {
        int RANK_PK = 0x04;
        System.out.println(RANK_PK);
        System.out.println(1 & RANK_PK);
        System.out.println(2 & RANK_PK);
        System.out.println(3 & RANK_PK);
        System.out.println(4 & RANK_PK);
        System.out.println(4 == RANK_PK);

    }

    @Test
    public void testSwitch() {
        int i = 0;
        switch (i) {
            case 0:

            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            default:
                System.out.println("-1");
        }
    }


    @Test
    public void testDoWhile(){
        do {
            System.out.println(111);
        } while (false);
        System.out.println(222);
    }

    @Test
    public void testStringSplit(){
        String s = "lizhi_red_envelope";
        System.out.println(s.substring(0,14));
    }
}
