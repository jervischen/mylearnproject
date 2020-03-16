package com.example.demo;

import com.example.demo.bean.Content;
import com.example.demo.bean.Menu;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created in 2019-03-22 19:24.
 *
 * @author chenxiao
 */
public class TestF {
    public static String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static void main(String[] args) {
        Map<Content,String> map = new HashMap();


        System.out.println(getCurrentTime());
    }

    @Test
    public void testList(){

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
    public void a(){
        long userId = 5077819358388954668L;
        String hexString = Long.toHexString(5077819358388954668L);
        System.out.println(hexString);
        System.out.println(Long.valueOf(hexString,16));
    }
}
