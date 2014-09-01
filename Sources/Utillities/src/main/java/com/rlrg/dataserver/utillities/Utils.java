package com.rlrg.dataserver.utillities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

	public static Date convertDateBaseOnI18N(String date, String i18n)
			throws ParseException {
		DateFormat df = null;
		if ("vi".equals(i18n)) {
			df = new SimpleDateFormat("dd/MM/yyyy");
		}
		return df.parse(date);
	}

	public static boolean regexCheck(String input, String patent) {
		return input.matches(patent);
	}

	public static long truncateMiliSecondDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		//
		return calendar.getTime().getTime();
	}

	public static String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss.SSS");// dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	public static Date truncateDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.AM_PM, Calendar.AM);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		//
		return calendar.getTime();
	}

}
