package com.rlrg.test.code;

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
	}

}
