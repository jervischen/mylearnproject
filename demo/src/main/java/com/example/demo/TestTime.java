package com.example.demo;

import com.example.demo.util.DateUtil;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
        System.out.println(yyyyMMddStr + d1time);
    }

    /**
     * 两个日期间隔多少天
     */
    @Test
    public void testDaysBetween() {
        DateTime oldTime = new DateTime(2017, 11, 30, 0, 0, 0);
        DateTime nowTime = new DateTime();
        int days = Days.daysBetween(oldTime, nowTime).getDays();

        for (int i = 0; i <= days; i++) {
            DateTime startDate = oldTime.plusDays(i);
            System.out.println(startDate);
        }
    }

    @Test
    public void testDate() throws ParseException {
        SimpleDateFormat simFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Date date = simFormat.parse("2018-03-23 22:45:56");
        System.out.println(date.getTime() - System.currentTimeMillis());

        simFormat = new SimpleDateFormat("yyyy_MM_dd");
        System.out.println(simFormat);
    }

    @Test
    public void testfutureDay() throws ParseException {
        SimpleDateFormat simFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simFormat.parse("2018-03-23 00:00:00");
        System.out.println(date);
        System.out.println(new Date(date.getTime()));
    }

    @Test
    public void getRankPkNamePostfix() {
        int week = 2;
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(7);
        calendar.setTime(new Date());
        //第几周
        int iSweek = calendar.get(Calendar.WEEK_OF_YEAR) + week;
        //去上一年的周
        if (iSweek < 0) {
            Calendar c = Calendar.getInstance();
            c.set(calendar.get(Calendar.YEAR) - 1, Calendar.DECEMBER, 31, 23, 59, 59);
            Calendar c1 = Calendar.getInstance();
            c1.setFirstDayOfWeek(Calendar.MONDAY);
            c1.setMinimalDaysInFirstWeek(7);
            c1.setTime(c.getTime());

            int pWeek = c1.get(Calendar.WEEK_OF_YEAR) + iSweek;
            System.out.println(calendar.get(Calendar.YEAR) - 1 + "_" + pWeek);
        } else {
            System.out.println(calendar.get(Calendar.YEAR) + "_" + iSweek);
        }
    }

    @Test
    public void yuejie() {
        long exp = 1234567666896321444L;
        int i = (int) exp;
        System.out.println(i);
        System.out.println(i < 0);
    }

    @Test
    public void currentWeek() {
        String FORMATE_DATE = "yyyy-MM-dd 23:59:59";
        LocalDate d = LocalDate.now();

        // 上个月(可以是之前的任意月)的最后一天
        LocalDate lastDayOfPreviousMonth = d.minusMonths(1).dayOfMonth().withMaximumValue();


        LocalDate.Property e = d.minusWeeks(0).dayOfWeek();
        System.out.println("上周的周一：" + e.withMinimumValue().toString(FORMATE_DATE, Locale.CHINESE));
        System.out.println("上周的周日：" + e.withMaximumValue().toString(FORMATE_DATE, Locale.CHINESE));
        System.out.println(DateUtil.formatStrToNormalDate(e.withMaximumValue().toString(FORMATE_DATE, Locale.CHINESE)));
    }

}
