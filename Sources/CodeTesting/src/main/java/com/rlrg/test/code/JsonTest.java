package com.rlrg.test.code;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.rlrg.dataserver.category.dto.CategoryDTO;
import com.rlrg.dataserver.category.entity.Category;
import com.rlrg.utillities.json.JsonExporter;

public class JsonTest {

	public static void encodeAJSONObject() {
		JSONObject obj = new JSONObject();
		obj.put("name", "foo");
		obj.put("num", new Integer(100));
		obj.put("balance", new Double(1000.21));
		obj.put("is_vip", new Boolean(true));
		obj.put("nickname", null);
		System.out.print(obj);
	}

	public static void encodeAJSONObjectStreaming() throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("name", "foo");
		obj.put("num", new Integer(100));
		obj.put("balance", new Double(1000.21));
		obj.put("is_vip", new Boolean(true));
		obj.put("nickname", null);
		StringWriter out = new StringWriter();
		obj.writeJSONString(out);
		String jsonText = out.toString();
		System.out.print(jsonText);
	}

	public static void encodeAJSOBObjectWithOrder() throws IOException {
		Map obj = new LinkedHashMap();
		obj.put("name", "foo");
		obj.put("num", new Integer(100));
		obj.put("balance", new Double(1000.21));
		obj.put("is_vip", new Boolean(true));
		obj.put("nickname", null);
		StringWriter out = new StringWriter();
		JSONValue.writeJSONString(obj, out);
		String jsonText = out.toString();
		System.out.print(jsonText);
	}

	public static void encodeCategoryObject() {
		CategoryDTO c = new CategoryDTO();
		c.setCode("Category Code");
		c.setDescription("Description");
		c.setName("Name");
		c.setPosition(1);
		//
		String temp = JsonExporter.encodeObjectToJson(c, CategoryDTO.class);
		System.out.println(temp);
	}

	public static void decodeExample() {
		System.out.println("=======decode=======");

		String s = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
		Object obj = JSONValue.parse(s);
		JSONArray array = (JSONArray) obj;
		System.out.println("======the 2nd element of array======");
		System.out.println(array.get(1));
		System.out.println();

		JSONObject obj2 = (JSONObject) array.get(1);
		System.out.println("======field \"1\"==========");
		System.out.println(obj2.get("1"));

		s = "{}";
		obj = JSONValue.parse(s);
		System.out.println(obj);

		s = "[5,]";
		obj = JSONValue.parse(s);
		System.out.println(obj);

		s = "[5,,2]";
		obj = JSONValue.parse(s);
		System.out.println(obj);
	}

	public static void main(String[] args) throws Exception {
//		decodeExample();
//		encodeCategoryObject();
		testDecode();
	}
	
	public static void testDecode(){
		String json = "{\"Code\":\"Category Code\",\"Name\":\"Name\",\"Description\":\"Description\",\"Position\":1}";
		CategoryDTO c = JsonExporter.decodeJsonToObject(json, CategoryDTO.class);
		System.out.println(c);
	}

}
