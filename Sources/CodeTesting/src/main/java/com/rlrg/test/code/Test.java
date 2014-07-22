package com.rlrg.test.code;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String test = "{\"Login\":\"5\",\"Login55\":\"10\"}";
		JSONObject arr = (JSONObject) JSONValue.parse(test);
		System.out.println(arr.get("Lodgin"));
		System.out.println(arr.containsKey("Loddgin"));
	}

}
