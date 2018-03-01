package com.example.demo;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created in 2018-01-19 14:13.
 *
 * @author chenxiao
 */
public class TestTime {

    private static final DateTimeFormatter YYYYMMDD = DateTimeFormat.forPattern("yyyyMMdd");

    public static int getTime(int gap, Date date) {
        Long minutes = (long) date.getTime() / (60 * 1000L);
        int time = (int) ((minutes - minutes % gap) / gap);
        System.out.println(time);
        return time;
    }

    @Test
    public void test() {
        DateTime d1 = new DateTime(2018, 1, 19, 14, 0, 0);
        DateTime d2 = new DateTime(2018, 1, 19, 16, 0, 0);

        int d1time = getTime(60, d1.toDate());
        int d2time = getTime(60, d2.toDate());

        String yyyyMMddStr = YYYYMMDD.print(d1.toDate().getTime());
        System.out.println(yyyyMMddStr +d1time);
    }

    /**
     * 两个日期间隔多少天
     */
    @Test
    public void testDaysBetween(){
        DateTime oldTime = new DateTime(2017, 11, 30, 0, 0, 0);
        DateTime nowTime = new DateTime();
        int days = Days.daysBetween(oldTime, nowTime).getDays();

        for (int i=0;i <=days;i++){
            DateTime startDate = oldTime.plusDays(i);
            System.out.println(startDate);
        }
    }
}
