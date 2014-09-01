package com.rlrg.test.code;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String test = "{\"Login\":\"5\",\"Login55\":\"10\"}";
//		JSONObject arr = (JSONObject) JSONValue.parse(test);
//		System.out.println(arr.get("Lodgin"));
//		System.out.println(arr.containsKey("Loddgin"));
		
		String s1 = "Hello World!";
		String s2 = new String("Hello World!");
		String s3 = s1;
		
		System.out.println(s1 == s2);
		System.out.println(s1 == s3);
		System.out.println(s3 == s2);
		
		Integer a = 1;
		Integer b = 2;
		swap(a, b);
		System.out.println(a);
		
		String st1 = "abc";
		String st2 = "xyz";
		swap(st1, st2);
		System.out.println(st1);
	}
	

	static void swap(String s1, String s2) { String temp = s1; s1 = s2; s2 = temp;}
	
	static void swap(Integer a, Integer b) { Integer temp = a; a = b; b = temp;}

}
