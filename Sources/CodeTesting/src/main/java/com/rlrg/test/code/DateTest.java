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
	}

}
