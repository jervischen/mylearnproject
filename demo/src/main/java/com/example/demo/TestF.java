package com.example.demo;

import com.example.demo.bean.Content;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

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

        map.put(new Content(11,"aa"),"aa");

        System.out.println(map.get(new Content(11,"aa")));

        System.out.println(new Content(22,"bb").getText());

        System.out.println(getCurrentTime());
    }
}
