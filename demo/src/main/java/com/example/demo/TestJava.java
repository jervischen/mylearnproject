package com.example.demo;

import com.example.demo.bean.MyData;
import com.example.demo.util.DateUtil;
import com.example.demo.util.Student;
import com.example.demo.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import fm.lizhi.sso.util.AntPathMatcher;
import fm.lizhi.sso.util.PathMatcher;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.IllegalCharsetNameException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created in 2018-01-08 14:39.
 *
 * @author chenxiao
 */
public class TestJava {
    private static Logger logger = LoggerFactory.getLogger(TestJava.class);

    /*    public static void main(String[] args) throws Exception {
     *//*        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        str = br.readLine();
        System.out.println(str);*//*

        String str = "abc";
        tranStr(str);
        System.out.println(str);
    }*/

    public static void tranStr(String str) {
        str = "123";
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
        System.out.println(DigestUtils.md5Hex("lz!@#fam2018"));
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
    public void testDoWhile() {
        do {
            System.out.println(111);
        } while (false);
        System.out.println(222);
    }

    @Test
    public void testStringSplit() {
        String s = "lizhi_red_envelope";
        System.out.println(s.substring(0, 14));

        System.out.println(s.replace("ddd", "aaaaa"));
    }


    @Test
    public void testXunHuan() {
        int times = 15 / 10;
        int mod = 15 % 10 > 0 ? 1 : 0;
        System.out.println("times=" + times);
        System.out.println("mod=" + mod);
        boolean settleResult = true;
        for (int i = 0; i < times + mod; i++) {
            if (!settleResult) {
                break;
            }
            List<String> list = new ArrayList<>();
            list.add("1");
            list.add("2");
            list.add("3");
            list.add("4");
            for (String s : list) {
                System.out.println(s);
                if (s.equals("5")) {
                    settleResult = false;
                    break;
                }
            }
        }
        System.out.println(settleResult);
    }

    @Test
    public void test() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println(cal.getTime());

        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.MONTH, 0);
        cal1.set(Calendar.DAY_OF_MONTH, 1);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        System.out.println(cal1.getTime());

    }

    @Test
    public void testMap() {
        Set<Long> set = new HashSet<>();
        set.add(11222L);
        set.add(12368L);
        Map<String, Set<Long>> map = new HashMap<>();
        map.put("ab", set);
        System.out.println(map);

        System.out.println(set.contains(12368L));

        System.out.println("0123".substring(1, 2));
    }

    @Test
    public void testDate1() {
        Map<String, Date> map = new HashMap<>();
        Date d = DateUtil.formatStrToDate("2018-06-06", "yyyy-MM-dd");
        map.put("a", d);

        Date a = map.get("a");
        System.out.println(a);
        map.put("a", DateUtil.formatStrToDate("2017-06-06", "yyyy-MM-dd"));
        System.out.println(a);

        MyData my = new MyData();
        my.setDate(d);
        System.out.println(my.getDate());

        my.setDate(DateUtil.formatStrToDate("2017-06-06", "yyyy-MM-dd"));
        System.out.println(my.getDate());
    }

    @Test
    public void testDate2() {
        Set<String> result = new HashSet<String>();
        Set<String> set1 = new HashSet<String>() {
            {
                add("王者荣耀");
                add("英雄联盟");
                add("穿越火线");
                add("地下城与勇士");
            }
        };

        Set<String> set2 = new HashSet<String>() {
            {
               /* add("王者荣耀");
                add("地下城与勇士");*/
                add("魔兽世界");
            }
        };

        result.clear();
        result.addAll(set1);
        boolean b = result.retainAll(set2);
        System.out.println(b);
        System.out.println("交集：" + result);


        System.out.println(System.currentTimeMillis() - 1800 * 1000);

        String phone = "18146824623";
        int weizhi = phone.indexOf("-");
        System.out.println(weizhi);
        System.out.println(phone.substring(weizhi + 1));

        for (int i = 0; i < 100; i++) {
     /*       int x = (int) (Math.random() * 200) * 1000;
            System.out.println(x);*/
            System.out.println(new Random().nextInt(200));
        }
        System.out.println(System.currentTimeMillis() + 60 * 1000);


    }

    @Test
    public void zz() {
        boolean isMatch = Pattern.matches("/recommend/recommend/889", "/recommend/recommend/*");
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);

        PathMatcher pathMatcher = new AntPathMatcher();
        System.out.println(pathMatcher.match("/live/comment_style/*", "/live/comment_style/user_list_page"));
    }

    @Test
    public void t() {
        System.out.println(Calendar.getInstance().get(Calendar.MINUTE));

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        System.out.println(year);
        String noStr = String.format("%05d", 555);
        System.out.println(noStr);

        Float a = new Float(100);
        System.out.println(a);

    }

    @Test
    public void substring() throws UnsupportedEncodingException {
        String renewNo = "LZ-CL-201800148";
        System.out.println(renewNo.indexOf("-", 6));
        // renewNo = renewNo.substring(0, );

        UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString().length());

        String s = "咀嚼肌";
        System.out.println(s.getBytes("GBK").length);

        String a = "";
        List<String> strings = Arrays.asList(a.split(","));
        System.out.println(strings);


        Iterable<String> split = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split(a);
        System.out.println(split);
        for (String s1 : split) {
            System.out.println(s1);
        }

        String join = Joiner.on(",").skipNulls().join(split);
        System.out.println(join);
    }

    @Test
    public void te() {
        String str = "abcdef";
        Date date = DateUtil.formatStrToDate("2018-12-13", DateUtil.yyyy_MM_dd);

        re(str, date);
        System.out.println(str);
        System.out.println(date);
    }

    public void re(String str, Date date) {
        str = "123";
        date = DateUtil.formatStrToDate("2019-12-13", DateUtil.yyyy_MM_dd);
    }
    @Test
    public void testClone() {
        TestJava t = new TestJava();
        String str = "abcdef";
        Date date = DateUtil.formatStrToDate("2018-12-13", DateUtil.yyyy_MM_dd);

        t.re(str, date);
        System.out.println(str);
        System.out.println(date);

        Float f = 0.15f;

        System.out.println(1 - f);


        int amount = new BigDecimal(100.219).multiply(new BigDecimal(10)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
        System.out.println(amount);

        Float nu = 100f;
        System.out.println(nu.equals(100f));

        System.out.println("2019010417".substring(0, 6));

        long[] widgetIds = new long[20];
        for (long widgetId : widgetIds) {
            System.out.print(widgetId);
        }
    }

    @Test
    public void testArrayList(){
        List list = Lists.newArrayList();
        list.add(2);
        list.add(1);
        list.add(3);
        System.out.println(list.toString());

        boolean equals = Objects.equals("PRODUCT", "product");

        boolean pro = "PRODUCTpro".contains("pro");

        System.out.println(equals);
        System.out.println(pro);
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        class Student implements Cloneable{
            private String name;

            @Override
            public Object clone() throws CloneNotSupportedException {
                return super.clone();
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        Student st = new Student();
        st.setName("CC");
        Student clone = (Student)st.clone();
        clone.setName("GG");

        System.out.println("st="+st.getName());
        System.out.println("clone="+clone.getName());

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(123);
            }
        });

    }
}
