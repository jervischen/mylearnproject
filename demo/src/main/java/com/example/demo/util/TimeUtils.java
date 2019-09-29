package com.example.demo.util;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeUtils {
	
	public static final long ONE_SECOND_IN_MILLS = 1000L;
	public static final long ONE_MINUTE_IN_MILLS = ONE_SECOND_IN_MILLS * 60;
	public static final long ONE_HOUR_IN_MILLS = ONE_MINUTE_IN_MILLS * 60;
	public static final long ONE_DAY_IN_MILLS = ONE_HOUR_IN_MILLS * 24;

	public static Date parseTime(String timeStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(timeStr);
		} catch (ParseException e) {
			LizhiLogger.error("parse time error", e);
			return null;
		}
	}
	
	public static Date parseTime(String timeStr, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(timeStr);
		} catch (ParseException e) {
			LizhiLogger.error("parse time error", e);
			return null;
		}
	}

	public static String formatTime(Date date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static boolean nowIsBetween(String startTimeStr, String endTimeStr) {
		Date startTime = TimeUtils.parseTime(startTimeStr);
		Date endTime = TimeUtils.parseTime(endTimeStr);
		Date now = new Date();
		return startTime.before(now) && endTime.after(now);
	}
	
	public static boolean nowIsBetween(Date startTime, Date endTime) {
		Date now = new Date();
		return startTime.before(now) && endTime.after(now);
	}

	public static boolean nowIsBetween(Date now, Date startTime, Date endTime) {
		return startTime.before(now) && endTime.after(now);
	}

	public static boolean nowIsBetWeenEqual(Date now, Date startTime, Date endTime){
		return (startTime.before(now) && endTime.after(now)) || (now.equals(startTime) || now.equals(endTime));
	}

	public static boolean nowIsBetween(long startTime, long endTime){
		// millis
		long now = System.currentTimeMillis();
		return (startTime <= now) && (now <= endTime);
	}

	public static String getThisDay() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(c.getTime());
	}

	public static String getThisDay(long time){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(time));
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(c.getTime());
	}

	public static String getPreDay(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH,-1);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(c.getTime());
	}

	public static String getPreDay(long time){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(time));
		c.add(Calendar.DAY_OF_MONTH,-1);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(c.getTime());
	}

	public static String getPreDay(int num){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_MONTH,-num);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(c.getTime());
	}

	public static String getThisHour(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
		return df.format(c.getTime());
	}

	public static String getThisHour(long time){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(time));
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
		return df.format(c.getTime());
	}

	public static String getNextHour(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR,1);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
		return df.format(c.getTime());
	}

	public static String getPreHour(long time){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(time));
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
		c.add(Calendar.HOUR,-1);
		return df.format(c.getTime());
	}

	public static String getPreHour(){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
		c.add(Calendar.HOUR,-1);
		return df.format(c.getTime());
	}

	public static List<String> getDiffDayList(Date beginDate, Date endDate) {
		int diff = DateUtil.getDayDiff(endDate, beginDate);
		if (diff == 0) {
			return Collections.emptyList();
		}
		List<String> dayList = new ArrayList<>();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		for (int i = diff; i > 0; i--) {
			Date beforeDate = DateUtil.getDayBefore(endDate, i);
			dayList.add(df.format(beforeDate));
		}
		return dayList;
	}
	
	/**
	 * @return 格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowString() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(c.getTime());
	}

	/**
	 * 获取 x minute后的时间 millions 毫秒
	 * @param minute
	 * @return
	 */
	public static long getNextTimeByMinute(int minute){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, minute);
		return c.getTime().getTime();
	}

	/**
	 * 获取 x second前的 millions 毫秒
	 * @param milliions
	 * @param second
	 * @return
	 */
	public static long getPreTimeBySeconds(long milliions, int second){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(milliions);
		c.add(Calendar.SECOND, -second);
		return c.getTime().getTime();
	}

	public static long getTheEndOfTheDay(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis() - System.currentTimeMillis();
	}

	public static long getTheEndOfTheDay(long time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(time));
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	public static long getTheEndOfTheMinute(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}
    public static long getTheEndOfTheMinute(long time){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
	
	public static long getTheStartOfTheDay(long time){
		Calendar cal = Calendar.getInstance();//获取当前日期
		cal.setTime(new Date(time));
		//cal.add(Calendar.MONTH, 0);
		//cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime().getTime();
	}

	public static long getTheStartOfTheDay(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	public static boolean isBetween(long starTime, long endTime, long nowTime){
		return nowTime >= starTime && nowTime <= endTime;
	}

	public static boolean isBetweenNotEquelEnd(long starTime, long endTime, long nowTime){
		return nowTime >= starTime && nowTime < endTime;
	}

	public static long getStartTimeOfNextDay(long time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(time));
		cal.add(Calendar.DAY_OF_MONTH,1);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime().getTime();
	}


	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 获取下个月初
	 */
	public static Date getNextBeginOfMonth(boolean isMock, int settleMockInteval) {
		if (isMock) {
			return getNextMockTime(settleMockInteval);
		} else {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			return cal.getTime();
		}
	}

	/**
	 * 获取下个mock的时间
	 */
	public static Date getNextMockTime(int settleMockInteval) {
		Calendar cal = Calendar.getInstance();
		int tmpMinutes = cal.get(Calendar.MINUTE);
		cal.set(Calendar.MINUTE, 0);
		cal.add(Calendar.MINUTE, tmpMinutes / settleMockInteval * settleMockInteval + settleMockInteval);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取上个月初
	 */
	public static Date getLastBeginOfMonth(boolean isMock, int settleMockInteval) {
		if (isMock) {
			return getLastMockTime(settleMockInteval);
		} else {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			return cal.getTime();
		}
	}

	/**
	 * 获取上个mock时间
	 */
	public static Date getLastMockTime(int settleMockInteval) {
		Calendar cal = Calendar.getInstance();
		int tmpMinutes = cal.get(Calendar.MINUTE);
		cal.set(Calendar.MINUTE, 0);
		cal.add(Calendar.MINUTE, tmpMinutes / settleMockInteval * settleMockInteval - settleMockInteval);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取本个mock时间
	 */
	public static Date getThisMockTime(int settleMockInteval) {
		Calendar cal = Calendar.getInstance();
		int tmpMinutes = cal.get(Calendar.MINUTE);
		cal.set(Calendar.MINUTE, 0);
		cal.add(Calendar.MINUTE, tmpMinutes / settleMockInteval * settleMockInteval);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取本月初
	 */
	public static Date getThisBeginOfMonth(boolean isMock, int settleMockInteval) {
		if (isMock) {
			return getThisMockTime(settleMockInteval);
		} else {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			return cal.getTime();
		}
	}

	public static String getMonthStr(Date date, boolean isMock) {
		if (isMock) {
			return getMinuteStr(date);
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			return sdf.format(date);
		}
	}

	public static String getMinuteStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(date);
	}

	@Test
	public void testBeginOfMonth(){
		Date lastBeginOfMonth = TimeUtils.getLastBeginOfMonth(false,
				30);

		Date thisBeginOfMonth = TimeUtils.getThisBeginOfMonth(false, 30);

		System.out.println(DateUtil.formatDateNormal(lastBeginOfMonth));
		System.out.println(DateUtil.formatDateNormal(thisBeginOfMonth));
	}

	public static void main(String[] args) {
		Date lastBeginOfMonth = TimeUtils.getLastBeginOfMonth(false,
				30);
		String settleMonth = TimeUtils.getMonthStr(lastBeginOfMonth, true);

		System.out.println(lastBeginOfMonth);
		System.out.println(settleMonth);

		Date lastBeginOfMonth1 = TimeUtils.getLastBeginOfMonth(false,
				30);
		String settleMonth1 = TimeUtils.getMonthStr(lastBeginOfMonth1, false);

		System.out.println(lastBeginOfMonth1);
		System.out.println(settleMonth1);

		//2019-01-04 17:15
		String[] params = settleMonth.split(" ");
		settleMonth = params[0].replace("-", "");
		settleMonth = settleMonth.substring(0,6);
		int settleMonthInt = Integer.valueOf(settleMonth);
		System.out.println(settleMonthInt);

		String s = CharMatcher.anyOf("- ").removeFrom("2019-01-04 17:15");
		System.out.println(s);
		Iterable<String> split = Splitter.on(":").trimResults().omitEmptyStrings().split(s );
		System.out.println(split);
		System.out.println(split.iterator().next());

		float a = 0.0f;
		System.out.println(100 -a);
    }


}
