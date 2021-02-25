package com.example.demo;

import com.example.demo.bean.Content;
import com.example.demo.bean.Menu;
import com.example.demo.util.DateUtil;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created in 2019-03-22 19:24.
 *
 * @author chenxiao
 */
public class TestF {
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static void main(String[] args) {
        Map<Content, String> map = new HashMap();


        System.out.println(getCurrentTime());
    }

    @Test
    public void testList() {

        ArrayList<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.get(1));
        list.remove(list.get(1));

        System.out.println(list.get(1));

        Menu menu = new Menu();
        List<String> list1 = menu.getList();
        System.out.println(list1);
        list1.remove("a");
        System.out.println(menu.getList());
        System.out.println(list1);
    }

    @Test
    public void a() {
        long userId = 5077819358388954668L;
        String hexString = Long.toHexString(5077819358388954668L);
        System.out.println(hexString);
        System.out.println(Long.valueOf(hexString, 16));
    }

    @Test
    public void b() {
        long time = System.currentTimeMillis();
        System.out.println(time);

        System.out.println(Long.MAX_VALUE);
        Long a = 2L << 41;
        System.out.println(a);
    }

    @Test
    public void cc() {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);

        List<Integer> b = new ArrayList<>();
        a.add(3);
        a.add(4);

        ArrayList<List<Integer>> lists = Lists.newArrayList(a, b);
        System.out.println(lists);


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int currMinute = calendar.get(Calendar.MINUTE);

        int difMinute = 10 - currMinute % 10;
        int endMinute = new DateTime().minuteOfDay().get() + difMinute;
        long timeDown;

        timeDown = new DateTime().withMillisOfDay(endMinute * 60 * 1000).getMillis() - System.currentTimeMillis();

        long max = Math.max(timeDown, 0L);
        System.out.println(max/1000/60);
        System.out.println(max/1000%60);
    }
}
