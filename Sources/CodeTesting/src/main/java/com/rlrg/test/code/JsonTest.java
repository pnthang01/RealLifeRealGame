package com.rlrg.test.code;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.rlrg.dataserver.task.dto.CategoryDTO;
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
		String temp = jsonExporter.encodeObjectToJson(c, CategoryDTO.class);
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
	
	private static JsonExporter jsonExporter = new JsonExporter();

	public static void main(String[] args) throws Exception {
//		decodeExample();
//		encodeCategoryObject();
		encodeCategoryObjects();
		System.out.println("\n=============\n");
//		testDecode();
		
		testDecodeObjects();
	}
	
	public static void testDecode(){
		String json = "{\"Category\":{\"Code\":\"Category Code\",\"Name\":\"Name\",\"Description\":\"Description\",\"Position\":1}}";
		CategoryDTO c = jsonExporter.decodeJsonToObject(json, CategoryDTO.class);
		System.out.println(c);
	}
	
	public static void testDecodeObjects(){
		String json = "{\"Categories\":[" +
				"{\"Code\":\"Category Code 1\",\"Name\":\"Name 1\",\"Description\":\"Description 1\",\"Position\":1}," +
				"{\"Code\":\"Category Code 2\",\"Name\":\"Name 2\",\"Description\":\"Description 2\",\"Position\":2}," +
				"{\"Code\":\"Category Code 3\",\"Name\":\"Name 3\",\"Description\":\"Description 3\",\"Position\":3}]}";
		
		List<CategoryDTO> cateList = jsonExporter.decodeJsonToObjects(json, CategoryDTO.class);
		for(CategoryDTO c : cateList){
			System.out.println(c);
		}
	}
	
	public static void encodeCategoryObjects() {
		CategoryDTO c1 = new CategoryDTO();
		c1.setCode("Category Code 1");
		c1.setDescription("Description 1");
		c1.setName("Name 1");
		c1.setPosition(1);
		//          
		CategoryDTO c2 = new CategoryDTO();
		c2.setCode("Category Code 2");
		c2.setDescription("Description 2");
		c2.setName("Name 2");
		c2.setPosition(2);
		//
		CategoryDTO c3 = new CategoryDTO();
		c3.setCode("Category Code 3");
		c3.setDescription("Description 3");
		c3.setName("Name 3");
		c3.setPosition(3);
		//
		final ArrayList test = new ArrayList<CategoryDTO>();
		test.add(c1);
		test.add(c2);
		test.add(c3);
		
		String temp = jsonExporter.encodeObjectsToJson(test, CategoryDTO.class);
		System.out.println(temp);
	}

}
