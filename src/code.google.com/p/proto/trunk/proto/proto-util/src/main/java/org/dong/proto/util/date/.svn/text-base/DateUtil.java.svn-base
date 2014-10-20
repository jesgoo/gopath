package org.dong.proto.util.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.dong.proto.util.log.LogUtil;

/**
 *作者：dongjibo
 *创建时间：2012-5-8
 */
public class DateUtil {
	private static String defaultDatePattern = "yyyy-MM-dd HH:mm";

	public static String getDefaultDatePattern() {
		return defaultDatePattern;
	}

	/**
	 * 当前时间
	 * 
	 * @return
	 */
	public static String getNow() {
		Date now = new Date();
		return format(now);
	}

	/**
	 * Date转为字符串 格式：2011-01-11 17:30:30
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, getDefaultDatePattern());
	}

	
	public static String getYear() {
		Date now = new Date();
		String pat = "yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(pat);
		return sdf.format(now);
	}
	
	/**
	 * Date转为字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		String returnValue = "";

		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}

		return returnValue;
	}

	/**
	 * 字符串转为Date
	 * 默认的时间格式"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String strDate) {
		return parse(strDate, getDefaultDatePattern());
	}

	/**
	 * 字符串转为Date
	 * 指定时间格式
	 * 
	 * @param strDate
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			LogUtil.error("转换失败,时间字符串和时间格式不匹配，strDate=" + strDate + " pattern="
					+ pattern, e);
			return null;
		}
	}

	/**
	 * 加n月
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * 加n天
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date addDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, n);
		return cal.getTime();
	}

	/**
	 * 加n分
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date addMinute(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, n);
		return cal.getTime();
	}
	
	/**
	 * 加n时
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date addHour(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, n);
		return cal.getTime();
	}

	/**
	 * 加n秒
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date addSecond(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, n);
		return cal.getTime();
	}
	
	/**
	 * 获取当前时间ms
	 * @return
	 */
	public static long getCurrentTimes(){
		return System.currentTimeMillis() ;
	}
	
	/**
	 * 将Date转化为Timestamp
	 * @param date
	 * @return
	 */
	public static Timestamp dateToTimestamp(Date date){
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}
}



