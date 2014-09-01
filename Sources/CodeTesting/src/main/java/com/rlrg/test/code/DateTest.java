package com.rlrg.test.code;

import java.util.Calendar;

public class DateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 7);
		System.out.println(c.getTime());
		
		String date1 = "01/09/2014";
		String date2 = "09/01/2014";
		String date3 = "2014/09/01";
		
	}

}
