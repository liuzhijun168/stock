package com.lzj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 时间工具类
 */
public class DateUtil {

	public static String DAY_FORMAT_STRING = "yyyy-MM-dd";
	public static String DAY_HOUR_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";
	public static final int SECONDS_IN_DAY = 60 * 60 * 24;
	public static final long MILLIS_IN_DAY = 1000L * SECONDS_IN_DAY;

	/**
	 * 两个时间之间的时间差。
	 * 
	 * @param timeA
	 * @param timeB
	 * @return 返回 “分钟：秒”形式
	 */
	public static long dateMinDifference(long timeA, long timeB) {
		long l = timeA - timeB;
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		// long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		return min;
	}

	// 计算当前时间的0点0分0秒
	public static Date getDateBegin(Date date) throws ParseException {
		if (date == null)
			return null;

		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

		return fmt.parse(sdf.format(date));
	}

	/**
	 * 两个时间之间的时间差。
	 * 
	 * @param timeA
	 * @param timeB
	 * @return 返回 “分钟：秒”形式
	 */
	public static long dateSecDifference(long timeA, long timeB) {
		long l = timeA - timeB;
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		return s;
	}

	/**
	 * 两个时间之间的时间差。
	 * 
	 * @param timeA
	 * @param timeB
	 * @return 返回 小时
	 */

	public static long dateHourDifference(long timeA, long timeB) {
		long l = timeA - timeB;
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		// long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		// long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		return hour;
	}

	public static long dateDayDifference(long a, long b) {
		long between = (a - b) / 1000;// 除以1000是为了转换成秒
		long day = between / (24 * 3600);
		return day;

	}

	/**
	 * 按时间形式格式化指定的毫秒数
	 * 
	 * @param time
	 * @return
	 */
	public static Date getDate(long time) {
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(time);
		return ca.getTime();
	}

	public static Date getDate() {
		return new Date();
	}

	public static long getCurrDateTimeInMillis() {
		Calendar ca = Calendar.getInstance();
		return ca.getTimeInMillis();
	}

	/**
	 * 
	 * @return 返回当前时间的UNIX形式
	 */
	public static long getTimeStamp() {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		return ca.getTimeInMillis() / 1000;
	}

	/**
	 * UNIX 时间转换为日期
	 * 
	 * @param timestampString
	 * @return
	 */
	public static Date TimeStamp2Date(long timestampString) {
		Long timestamp = timestampString * 1000;
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(timestamp);
		return ca.getTime();
	}

	/**
	 * 比较两个时间是否为同一天
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean compareToDay(Date a, Date b) {
		Calendar ca = Calendar.getInstance();
		Calendar cb = Calendar.getInstance();
		ca.setTime(a);
		cb.setTime(b);
		int aYear = ca.get(Calendar.YEAR);
		int aDay = ca.get(Calendar.DAY_OF_YEAR);
		int bYear = cb.get(Calendar.YEAR);
		int bDay = cb.get(Calendar.DAY_OF_YEAR);
		if (aYear == bYear && aDay == bDay) {
			return true;
		}
		return false;
	}

	public static boolean isSameDayOfMillis(final long ms1, final long ms2) {
		final long interval = ms1 - ms2;
		return interval < MILLIS_IN_DAY && interval > -1L * MILLIS_IN_DAY && toDay(ms1) == toDay(ms2);
	}

	private static long toDay(long millis) {
		return (millis + TimeZone.getDefault().getOffset(millis)) / MILLIS_IN_DAY;
	}

	public static int sub(Date a, Date b) {
		Calendar ca = Calendar.getInstance();
		Calendar cb = Calendar.getInstance();
		ca.setTime(a);
		cb.setTime(b);
		int aYear = ca.get(Calendar.YEAR);
		int aDay = ca.get(Calendar.DAY_OF_YEAR);
		int bYear = cb.get(Calendar.YEAR);
		int bDay = cb.get(Calendar.DAY_OF_YEAR);
		return aDay - bDay + (aYear - bYear) * 365;
	}

	public static Date add(Date date, int amount, int field) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(field, amount);
		return ca.getTime();
	}

	public static long getTimeMillis(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.getTimeInMillis();
	}

	public static long subMillisSecond(Date a, Date b) {
		Calendar ca = Calendar.getInstance();
		Calendar cb = Calendar.getInstance();
		ca.setTime(a);
		cb.setTime(b);
		return ca.getTimeInMillis() - cb.getTimeInMillis();
	}

	public static long subMin(Date a, Date b) {
		Calendar ca = Calendar.getInstance();
		Calendar cb = Calendar.getInstance();
		ca.setTime(a);
		cb.setTime(b);
		return (ca.getTimeInMillis() - cb.getTimeInMillis()) / 1000 / 60;
	}

	/**
	 * 指定时间和当前时间差（）
	 * 
	 * @param time
	 * @return
	 */
	public static long subCurrSec(long time) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		long t = ca.getTimeInMillis() - time;
		t = t < 0 ? time - ca.getTimeInMillis() : t;
		return t / 1000;
	}

	/**
	 * 指定时间和当前时间差（指定时间在当前时间之后）
	 * 
	 * @param time
	 * @return 秒
	 */
	public static long subCurrSec_A(long time) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		long t = time - ca.getTimeInMillis();
		return t / 1000;
	}

	public static String formatDate() {
		return formatDate(new Date(), DAY_HOUR_FORMAT_STRING);
	}

	public static String formatDate(Date date) {
		return formatDate(date, DAY_HOUR_FORMAT_STRING);
	}

	public static String formatDate(String format) {
		return formatDate(new Date(), format);
	}

	public static String formatDate(Date date, String format) {
		SimpleDateFormat dateformat1 = new SimpleDateFormat(format);
		return dateformat1.format(date);
	}

	public static Date str2Date(String date) throws ParseException {

		return str2Date(date, DAY_HOUR_FORMAT_STRING);

	}

	public static Date str2Date(String date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			return df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取当天零点零分零秒
	 */
	public static Date getTodayStart() {
		Calendar calendar = Calendar.getInstance();
		// 如果没有这种设定的话回去系统的当期的时间
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * @return 今天指定时间
	 */
	@SuppressWarnings("deprecation")
	public static Date getTodayByHMS(String str) {
		Date d = new Date();
		String[] times = str.split("[:]");
		int ch = Integer.parseInt(times[0]);
		int cm = Integer.parseInt(times[1]);
		int cs = Integer.parseInt(times[2]);
		d.setHours(ch);
		d.setMinutes(cm);
		d.setSeconds(cs);
		return d;

	}

	/**
	 * 给指定时间加上几天
	 * 
	 * @param date
	 *            指定时间
	 * @param day
	 *            要加上的天数
	 * @return
	 */
	public static Date addDay(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}

	public static Date change(Date date, int type, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(type, day);
		return calendar.getTime();
	}

	public static Date getTomorrow() {
		Date d = new Date();
		Calendar time = Calendar.getInstance();
		time.setTime(d);
		GregorianCalendar ca = new GregorianCalendar();
		ca.set(time.get(Calendar.YEAR), time.get(Calendar.MONTH), time.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		ca.add(Calendar.DAY_OF_YEAR, 1);
		return ca.getTime();
	}

	public static void main(String[] args) {
		Date date = getTodayStart();
		System.out.println(formatDate(date));
		date = addDay(date, -1);
		System.out.println(formatDate(date));

	}

}
