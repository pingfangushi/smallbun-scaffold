/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 ‭‭‭‭‭‭‭‭‭‭‭‭[smallbun] www.smallbun.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.smallbun.framework.toolkit;

import org.springframework.util.StringUtils;

import java.lang.management.ManagementFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 日期工具类
 *
 * @author SanLi
 * Created by 2689170096@qq.com on 2018/10/25 20:27
 */
public class DateUtil {

	/**
	 * 默认的时间格式化类型
	 */
	public final static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	/**
	 * 时间格式化类型
	 */
	public final static String DATE_PATTERN_DETAIL = "yyyy-MM-dd hh mm ss";


	/**
	 * 方法说明：字符类型装成指定格式的时间格式
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date str2Date(String date, String pattern) {
		Date d = null;
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		try {
			d = new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
		}
		return d;
	}

	/**
	 * 获取当前Date型日期
	 *
	 * @return Date() 当前日期
	 */
	public static Date getNowDate() {
		return new Date();
	}

	/**
	 * 方法说明：字符类型装成指定格式的时间格式
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date strToDate(String date, String pattern) {
		Date d = null;
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		try {
			d = new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException ignored) {
		}
		return d;
	}

	/**
	 * 方法说明：字符类型装成默认的时间格式
	 *
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date) {
		return str2Date(date, DEFAULT_DATE_PATTERN);
	}

	/**
	 * 方法说明：字符类型装成默认的时间格式
	 *
	 * @param date
	 * @return
	 */
	public static Date strToDate(String date) {
		return strToDate(date, DEFAULT_DATE_PATTERN);
	}

	/**
	 * 方法说明：字符类型转成timestamp的时间格式
	 *
	 * @param date
	 * @return
	 */
	public static Date str2DateDetail(String date) {
		return str2Date(date, DATE_PATTERN_DETAIL);
	}

	/**
	 * 时间转成字符串
	 *
	 * @param date
	 * @return
	 */
	public static String date2Str(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat newstr = new SimpleDateFormat(pattern);
		return newstr.format(date);
	}

	/**
	 * 时间转换成字符串-默认格式"yyyy-MM-dd"
	 *
	 * @param date
	 * @return
	 */
	public static String date2Str(Date date) {
		return date2Str(date, DEFAULT_DATE_PATTERN);
	}

	/**
	 * 方法说明：某个时间点添加几天后的时间
	 *
	 * @param date 某个时间
	 * @param days 需要添加的时间
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	/**
	 * 格式化时间
	 *
	 * @return
	 */
	public static String getNowtimeStr() {
		Calendar cal = Calendar.getInstance();
		return date2Str(cal.getTime(), "yyyyMMddHHmmssSSS");
	}

	/**
	 * 方法说明：给日期加上一天
	 *
	 * @param date
	 * @return
	 */
	public static Date addDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}

	/**
	 * 方法说明：某个时间点添加几月后的时间
	 *
	 * @param date   某个时间
	 * @param months 需要添加月数
	 * @return
	 */
	public static Date addMonths(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}

	/**
	 * 增加年份
	 *
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addYears(Date date, int years) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);
		return cal.getTime();
	}

	/**
	 * 取得当前日期
	 *
	 * @return
	 */
	public static Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * 取当前日期字符串"yyyy-MM-dd"
	 *
	 * @return
	 */
	public static String getCurrentDateStr() {
		SimpleDateFormat newstr = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		return newstr.format(getCurrentDate());
	}

	/**
	 * 取当前日期字符串"yyyy-MM-dd"
	 *
	 * @return
	 */
	public static String getCurrentDateStr(String pattern) {
		SimpleDateFormat newstr = new SimpleDateFormat(pattern);
		return newstr.format(getCurrentDate());
	}

	/**
	 * 获取当前时间往前一个月的开始日期
	 *
	 * @return
	 */
	public static String getOneMonthStartDate() {
		SimpleDateFormat newstr = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		Calendar calendar = Calendar.getInstance();
		// 得到前一个月
		calendar.add(Calendar.MONTH, -1);
		return newstr.format(calendar.getTime()).toString();
	}

	/**
	 * 获取当前时间往前一个月的结束日期
	 *
	 * @return
	 */
	public static String getOneMonthEndDate() {
		SimpleDateFormat newstr = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		return newstr.format(new Date()).toString();
	}

	/**
	 * 比较两个时间大小，date1<=date2返回true，反之返回false
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareDate(Date date1, Date date2) {
		long day1 = date1.getTime();
		long day2 = date2.getTime();
		int result = 0;
		if (day1 > day2) {
			result = 1;
		} else if (day1 < day2) {
			result = -1;
		}
		return result;
	}

	public static Map<String, Integer> getDateFields(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("year", year);
		map.put("month", month);
		map.put("day", day);
		return map;
	}

	/**
	 * 方法说明：将指定时间重置为当天凌晨时间
	 *
	 * @param date
	 * @return
	 */
	public static Date moveBeginOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 1);
		return c.getTime();
	}

	/**
	 * 获取当前时间一年的日期
	 *
	 * @return
	 */
	public static Date getOneYearStartDate() {
		Calendar calendar = Calendar.getInstance();
		// 得到前一个月
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 1);
		return calendar.getTime();
	}

	/**
	 * 获取当月初时间
	 *
	 * @return
	 */
	public static Date getCurrentMonthBegin() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 获取指定年份开始时间
	 *
	 * @return
	 * @throws ParseException
	 */
	public static Date getYearBegin(String yearStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(sdf.parse(yearStr.toString()));
		return calendar.getTime();
	}

	public static int getYearFromDate(Date date) {
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		return cld.get(Calendar.YEAR);
	}

	/**
	 * 方法说明：根据年月获取初始日期
	 *
	 * @param yearMonth
	 * @return
	 */
	public static Date getDateFromYearMonth(String yearMonth) {
		DateFormat ym = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = ym.parse(yearMonth);
		} catch (ParseException e) {
		}
		return date;
	}

	/**
	 * 方法说明：根据年月日获取日期
	 *
	 * @param yearMonth
	 * @return
	 */
	public static Date getDateFromYearMonthDay(String yearMonth) {
		DateFormat ym = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = ym.parse(yearMonth);
		} catch (ParseException e) {
		}
		return date;
	}

	/**
	 * 获取这个月的年份
	 *
	 * @return
	 */
	public static int getThisMonthOfYear() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}

	/**
	 * 获取毫秒的数据
	 *
	 * @return
	 */
	public static String getMillisecondStr() {
		String result = date2Str(getCurrentDate(), "yyMMddHHmmssSSS");
		return result;
	}

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long betweenDays = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(betweenDays));
	}

	/**
	 * 计算两个时间差
	 */
	public static String getDatePoor(Date endDate, Date nowDate) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		// long sec = diff % nd % nh % nm / ns;
		return day + "天" + hour + "小时" + min + "分钟";
	}

	/**
	 * 获取服务器启动时间
	 */
	public static Date getServerStartDate() {
		long time = ManagementFactory.getRuntimeMXBean().getStartTime();
		return new Date(time);
	}


}
