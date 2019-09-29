package com.example.demo;

import com.example.demo.util.DateUtil;
import com.example.demo.util.TimeUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
        int week = 0;
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
        System.out.println("本周的周日：" + e.withMaximumValue().toString(FORMATE_DATE, Locale.CHINESE));
        System.out.println(DateUtil.formatStrToNormalDate(e.withMaximumValue().toString(FORMATE_DATE, Locale.CHINESE)));


        LocalDate localDate = LocalDate.now();
        LocalDate.Property property = localDate.minusWeeks(0).dayOfWeek();
        Date date = DateUtil.formatStrToNormalDate(property.withMaximumValue().toString(FORMATE_DATE, Locale.CHINESE));
        System.out.println(date);
    }

    @Test
    public void currentHour() {
        System.out.println(DateUtil.formatDateToString(new Date(), "yyyyMMddHH"));
    }

    @Test
    public void addDays() {
        DateTime dateTime = new DateTime(new Date());
        DateTime dateTime1 = dateTime.plusDays(3);
        System.out.println(dateTime);
        System.out.println(dateTime1);
        System.out.println(dateTime1.toDate());

        String[] split = DateUtil.formatCurrentTime("yyyy-MM-dd-HH-mm-ss").split("-");
        for (String a : split) {
//             System.out.println(a);
        }

        Random random = new Random();
        System.out.println(random.nextInt(60));
    }

    @Test
    public void addAfterDays() {
        Date date = DateUtil.getDayAfter(new Date(), 1);
        System.out.println(date);

        Date endTime = DateUtil.getDayBefore(new Date(), 1);

        Calendar c = Calendar.getInstance();//获得一个日历的实例
        c.setTime(new Date());//设置日历时间
//         c.add(Calendar.DATE, -1);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 1);
        //   c.add(Calendar.MONTH, 6);
        endTime = c.getTime();
        System.out.println(endTime);


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        calendar.add(Calendar.MONTH, 6);
        System.out.println(calendar.getTime());

        String a = "1233";
        a = a.replace("3", null);
    }


    @Test
    public void sqlDate() {
        java.sql.Timestamp date = new java.sql.Timestamp(System.currentTimeMillis());
        System.out.println(date);
        System.out.println(new Date());

        System.out.println(getTheEndOfTheMinute());
        System.out.println(System.currentTimeMillis());
        System.out.println(getTheStartOfTheDay(getTheEndOfTheMinute()));

        System.out.println(Calendar.getInstance().get(Calendar.MINUTE));
    }


    public static long getTheEndOfTheMinute() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }


    public static long getTheStartOfTheDay(long time) {
        Calendar cal = Calendar.getInstance();//获取当前日期
        cal.setTime(new Date(time));
        //cal.add(Calendar.MONTH, 0);
        //cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println(cal.getTime());
        return cal.getTime().getTime();
    }

    @Test
    public void isFile() {
        String path = "D:\\荔枝18989\\64.jpg";
        File file = new File(path);
        // System.out.println(file.isFile());
        Long a = 1000L;
        Long b = 1000L;
        int c = 1000;
        long d = 1000;
        System.out.println(a.equals(b));
        System.out.println(a == c);
        System.out.println(a.equals(c));
        System.out.println(a.equals(d));
        System.out.println(a == d);
    }


    @Test
    public void str() {
        String img = "http://njver.lizhi.fm/nj_verify/2018/06/07/2673919801982243883.jpg";
        System.out.println(img.contains("http://njver.lizhi.fm/nj_verify/"));
        System.out.println(img.replace("http://njver.lizhi.fm/nj_verify/", ""));

        Map<String, Object> data = new HashMap<>();
        data.put("a", null);
        data.put("b", null);
        data.put("c", null);

        System.out.println(data.get("a"));
        System.out.println(data.get("b"));
        System.out.println(data.get("c"));


        System.out.println(Name.NJ.name());
        System.out.println(Name.NJ.toString());
        System.out.println(Name.NJ.ordinal());
        System.out.println(TestEnum.MIDD.ordinal());
    }

    public enum Name {
        NJ, FAMILY, USER;
    }

    @Test
    public void testThisDay() {
        System.out.println(TimeUtils.getPreDay());
        // 当前时间是否有淘汰赛或者晋级赛
        long now = System.currentTimeMillis();
        long time = TimeUtils.getTheEndOfTheMinute(now);
        System.out.println("now=" + now);
        System.out.println("time=" + time);
        String thisDay = TimeUtils.getThisDay(time);
        //当天凌晨0点
        long dayStartTime = TimeUtils.getTheStartOfTheDay(time);
        System.out.println("dayStartTime=" + dayStartTime);

        //是否0点晋级
        boolean isNewDay = dayStartTime == time ? true : false;
        System.out.println(isNewDay);

        String preDay = TimeUtils.getPreDay(DateUtil.getYesterday().getTime());
        System.out.println(preDay);


        System.out.println(TimeUtils.getPreDay(2));

        System.out.println(DateUtil.formatDateToString(new Date(), "yyyyMMddHH"));
        System.out.println(TimeUtils.getThisDay());

    }

    @Test
    public void testT() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testWY() {
        int num = 10;
        System.out.println(num << 2);
        System.out.println(num << 2);
        System.out.println(1 << 7);
        System.out.println(Integer.toBinaryString(2 << 7));
        String s = Integer.toBinaryString(2 << 30);
        System.out.println(s);

        long id = (((2 << 30 | timeFlag()) << 10 | 347) << 12 | 150) << 9 | 58;
        System.out.println(id);
    }

    private static long timeFlag() {
        return (System.currentTimeMillis() - baseTime().getTime()) / 1000L;
    }

    private static Date baseTime() {
        Calendar c = Calendar.getInstance();
        // Calendar中月从0开始，所以1月是0
        c.set(2013, 0, 1, 0, 0, 0);
        return c.getTime();
    }

    @Test
    public void testMonth() {
        Date lastBeginOfMonth = TimeUtils.getLastBeginOfMonth(false, 30);
        Date thisBeginOfMonth = TimeUtils.getThisBeginOfMonth(false, 30);
        Date nextBeginOfMonth = TimeUtils.getNextBeginOfMonth(false, 30);

        System.out.println(DateUtil.formatDateNormal(lastBeginOfMonth));
        System.out.println(DateUtil.formatDateNormal(thisBeginOfMonth));
        System.out.println(DateUtil.formatDateNormal(nextBeginOfMonth));

        Calendar cal = Calendar.getInstance();
        int tmpMinutes = cal.get(Calendar.MINUTE);
        System.out.println(tmpMinutes);
    }

    @Test
    public void testNull() {
        String msg = null;
        System.out.println(msg + "123");
    }

    @Test
    public void testTimeUnit() {
        long time = System.currentTimeMillis();
        long l = TimeUnit.MILLISECONDS.toSeconds(time);
        System.out.println(l);
        System.out.println(time);
    }

    @Test
    public void testGMT() {
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+3")); // 设置时区为GMT  +8为北京时间东八区
        String str = sdf.format(cd.getTime());
        String str1 = sdf.format(new Date());
        System.out.println(str);
        System.out.println(str1);
    }

    @Test
    public void testGMT1() {
        Calendar cd = Calendar.getInstance(TimeZone.getTimeZone("GMT+3"));

        System.out.println(cd.getTime());
        System.out.println(new Date());
    }



    /**
     * 获得东八区时间
     *
     * @return
     */
    @Test
    public void getChinaTime() {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+3");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(timeZone);
        System.out.println(simpleDateFormat.format(new Date()));
    }


    public static void main(String[] args) {
        while (true) {
            if (TimeUtils.getMinute(new Date()) != 0) {
                continue;
            }
            System.out.println(new Date());
            // 当前时间是否有淘汰赛或者晋级赛
            long now = System.currentTimeMillis();
            long time = TimeUtils.getTheEndOfTheMinute(now);
            System.out.println("now=" + now);
            System.out.println("time=" + time);
            String thisDay = TimeUtils.getThisDay(time);
            //当天凌晨0点
            long dayStartTime = TimeUtils.getTheStartOfTheDay(time);
            System.out.println("dayStartTime=" + dayStartTime);

            //是否0点晋级
            boolean isNewDay = dayStartTime == time ? true : false;
            System.out.println(isNewDay);
            break;
        }
    }
}
