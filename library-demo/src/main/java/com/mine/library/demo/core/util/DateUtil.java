package com.mine.library.demo.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	public static Boolean getMaxMonth(String Month1, String Month2) {
		String[] month1 = Month1.split("-");
		String[] month2 = Month2.split("-");
		if (Integer.parseInt(month1[0]) > Integer.parseInt(month2[0])) {
			return Boolean.valueOf(true);
		}
		if (Integer.parseInt(month1[0]) == Integer.parseInt(month2[0])) {
			if (Integer.parseInt(month1[1]) > Integer.parseInt(month2[1])) {
				return Boolean.valueOf(true);
			}
			return Boolean.valueOf(false);
		}

		if (Integer.parseInt(month1[0]) < Integer.parseInt(month2[0])) {
			return Boolean.valueOf(false);
		}
		return Boolean.valueOf(true);
	}

	public static String[] nextMonth(String year, String month) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date date = dateFormat.parse(year + "-" + month + "-01");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(2, 1);
			int YEAR = calendar.get(1);
			int nextMonth = calendar.get(2) + 1;
			return new String[] { String.valueOf(YEAR),
					String.valueOf(nextMonth) };
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getLastWeekDate(String today) {
		String lastWeekDate = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = new GregorianCalendar();
		Date date = null;
		try {
			date = format.parse(today);
			calendar.setTime(date);
			calendar.add(5, -7);
			lastWeekDate = format.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return lastWeekDate;
	}

	public static int compare_date(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime())
				return 1;
			if (dt1.getTime() < dt2.getTime()) {
				return -1;
			}
			return 0;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static int compare_date(String DATE1, String DATE2, String format) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime())
				return 1;
			if (dt1.getTime() < dt2.getTime()) {
				return -1;
			}
			return 0;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	public static String getDateFull() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = formatter.format(currentTime);
		return date;
	}

	public static String getCurrentMonth() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String today = sdf.format(date);
		return today;
	}

	public static String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		return today;
	}

	public static String getCurrentYear() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String today = sdf.format(date);
		return today;
	}

	public static String getMonth() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		String today = sdf.format(date);
		return today;
	}

	public static String getDay() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		String today = sdf.format(date);
		return today;
	}

	public static String getLastMonth() {
		Calendar calendar = new GregorianCalendar();
		calendar.add(2, -1);
		int lastYEAR = calendar.get(1);
		int lastMonth = calendar.get(2) + 1;
		String tempLastMonth = "";
		if (lastMonth < 10)
			tempLastMonth = "0" + lastMonth;
		else {
			tempLastMonth = String.valueOf(lastMonth);
		}
		return lastYEAR + "-" + tempLastMonth;
	}

	public static String getYesterday() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = new GregorianCalendar();
		calendar.add(5, -1);
		String yesterday = format.format(calendar.getTime());
		return yesterday;
	}

	public static String getLastMonth(String thisMonth) {
		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		int lastYEAR = 0;
		int lastMonth = 0;
		String tempLastMonth = "";
		try {
			date = sdf.parse(thisMonth);
			calendar.setTime(date);
			calendar.add(2, -1);
			lastYEAR = calendar.get(1);
			lastMonth = calendar.get(2) + 1;
			if (lastMonth < 10)
				tempLastMonth = "0" + lastMonth;
			else
				tempLastMonth = String.valueOf(lastMonth);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return lastYEAR + "-" + tempLastMonth;
	}

	public static int getMonthTotalDays(String monthDate) {
		int totalDays = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Calendar calendar = new GregorianCalendar();
			Date date = null;
			date = sdf.parse(monthDate);
			calendar.setTime(date);
			totalDays = calendar.getActualMaximum(5);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return totalDays;
	}

	public static boolean isSameMonth(String date1, String date2)
			throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = simpleDateFormat.parse(date1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d1);
		date1 = calendar.get(1) + "-" + calendar.get(2);
		Date d2 = simpleDateFormat.parse(date2);
		Calendar calendar2 = Calendar.getInstance();
		calendar.setTime(d2);
		date2 = calendar2.get(1) + "-" + calendar2.get(2);
		boolean isSameMonth = date1.equals(date2);
		return isSameMonth;
	}

	public static String getNextMonth(String month) {
		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		int lastYEAR = 0;
		int lastMonth = 0;
		String tempLastMonth = "";
		try {
			date = sdf.parse(month);
			calendar.setTime(date);
			calendar.add(2, 1);
			lastYEAR = calendar.get(1);
			lastMonth = calendar.get(2) + 1;
			if (lastMonth < 10)
				tempLastMonth = "0" + lastMonth;
			else
				tempLastMonth = String.valueOf(lastMonth);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return lastYEAR + "-" + tempLastMonth;
	}
}
