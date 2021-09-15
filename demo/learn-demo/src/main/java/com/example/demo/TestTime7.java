package com.example.demo;

import com.example.demo.util.DateUtil;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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





    /**
     * 获取一个月中的第几天
     * @return
     */
    public static int getMonthDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取一个月中的第几天
     * @param date
     * @return
     */
    public static int getMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    // 获得上周一0点时间
    public static Date getLastTimesWeekZero(Long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesWeekZero(time));
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

    // 获得本周一0点时间
    public static Date getTimesWeekZero(Long time) {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(new Date(time));
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }
}
