package com.rlrg.test.code;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.rlrg.dataserver.task.entity.enums.TaskStatus;

public class TestBasic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TaskStatus test = TaskStatus.COMPLETED;
		System.out.println(test);
		TaskStatus temp = TaskStatus.valueOf("COMPLETED");
		System.out.println(temp);
		Class clazz = test.getClass();
		System.out.println(clazz.getEnumConstants());
		clazz.getEnumConstants();
		
		HashMap<String, String> testMap = new HashMap<String, String>();
		Set<Entry<String, String>> testMap2 = testMap.entrySet();
		//
		//testMap2.
		Set<String> keySet = testMap.keySet();
		Iterator<String> iter = keySet.iterator();
		while(iter.hasNext()){
			String key = iter.next();
		}
		//
		
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime().getTime());
		calendar.set(Calendar.MILLISECOND, 0);
		System.out.println(calendar.getTime().getTime());
	}

}
