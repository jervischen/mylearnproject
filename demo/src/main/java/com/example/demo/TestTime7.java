package com.example.demo;

import com.example.demo.util.DateUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Chen Xiao
 * @since 2020-02-17 11:42
 */
public class TestTime7 {


    @Test
    public void time() throws ParseException {
        TimeZone tz = TimeZone.getTimeZone("GMT+0:00");
        TimeZone.setDefault(tz);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(Long.valueOf("1581928050456")));


        Date parse = df.parse("2019-02-02 00:00:00");
        int monthDiff = DateUtil.getMonthDiff(new Date(), parse);
        System.out.println(monthDiff);
    }
}
