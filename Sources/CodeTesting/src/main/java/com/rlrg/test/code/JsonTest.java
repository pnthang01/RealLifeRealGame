package com.rlrg.test.code;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.rlrg.dataserver.task.dto.CategoryDTO;
import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.dataserver.task.entity.enums.TaskStatus;
import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.ConvertException;
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

	public static void encodeCategoryObject() throws ConvertException {
		CategoryDTO c = new CategoryDTO();
		c.setCode("Category Code");
		c.setDescription("Description");
		c.setName("Name");
		c.setPosition(1);
		//
		String temp = jsonExporter.encodeObjectToJson(c);
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
	//	encodeCategoryObject();
//		encodeCategoryObjects();
//		System.out.println("\n=============\n");
//		testDecode();
//		
//		testDecodeObjects();
		//
//		long start = System.currentTimeMillis();
//		testEncodeTasks();
//		System.out.println("It requires " + (System.currentTimeMillis() - start) + " miliseconds to complete");
//		System.out.println("\n=============\n");
//		testDecodeTasks();
		//testDecodeTask();
		testEncodeBlankRestObject();
	}
	
	public static void testEncodeBlankRestObject(){
		RestObject test = RestObject.successBlank();
		String result = jsonExporter.encodeBlankRestObject(test);
		System.out.println(result);
	}
	
	public static void testDecodeTask() throws IOException, ParseException, ConvertException{
		FileReader fr = new FileReader("E:\\test.json");
		JSONParser jp = new JSONParser();
		JSONObject jsonObject = (JSONObject) jp.parse(fr);
		
		//System.out.println(jsonObject.toJSONString());
		RestObject test = jsonExporter.decodeJsonToRestObject(jsonObject.toJSONString());
		CategoryDTO task = jsonExporter.decodeJsonToObject(test.getData().toString(), CategoryDTO.class);
		System.out.println(task);
	}
	
	public static void testDecodeTasks() throws IOException, ParseException, ConvertException{
		FileReader fr = new FileReader("E:\\tests.json");
		JSONParser jp = new JSONParser();
		JSONObject jsonObject = (JSONObject) jp.parse(fr);
		
		//System.out.println(jsonObject.toJSONString());
		TaskDTO task = jsonExporter.decodeJsonToObject(jsonObject.toJSONString(), TaskDTO.class);
		System.out.println(task);
	}
	
	public static void testEncodeTask() throws ConvertException{
		TaskDTO task = new TaskDTO();
		//
		CategoryDTO c = new CategoryDTO();
		c.setCode("Category Code");
		c.setDescription("Description");
		c.setName("Name");
		c.setPosition(1);
		//
		task.setCategory(c);
		task.setCompleteTime(new Date());
		task.setDescription("Description");
		task.setDifficultyLevel(1);
		task.setId(3l);
		task.setName("Task Name");
		task.setPoint(12);
		task.setStartTime(new Date());
		task.setStatus(TaskStatus.COMPLETED);
		//
		String temp = jsonExporter.encodeObjectToJson(task);
		System.out.println(temp);
	}
	
	public static void testEncodeTasks() throws ConvertException{
		TaskDTO task = new TaskDTO();
		//
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		for(int i=0; i<3;i++){
			CategoryDTO c = new CategoryDTO();
			c.setCode("Category Code " + i);
			c.setDescription("Description" + i);
			c.setName("Name" + i );
			c.setPosition(i + 1);
			list.add(c);
		}
		//task.setCategories(list);
		
		//
		//task.setCategory(c);
		task.setCompleteTime(new Date());
		task.setDescription("Description");
		task.setDifficultyLevel(1);
		task.setId(3l);
		task.setName("Task Name");
		task.setPoint(12);
		task.setStartTime(new Date());
		task.setStatus(TaskStatus.CANCELLED);
		//
		String temp = jsonExporter.encodeObjectToJson(task);
		System.out.println(temp);
	}
	
	public static void testDecode() throws ConvertException{
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
	
	public static void encodeCategoryObjects() throws ConvertException {
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
		final List test = new ArrayList<CategoryDTO>();
		test.add(c1);
		test.add(c2);
		test.add(c3);
		
		String temp = jsonExporter.encodeObjectsToJson(test);
		System.out.println(temp);
	}

}
