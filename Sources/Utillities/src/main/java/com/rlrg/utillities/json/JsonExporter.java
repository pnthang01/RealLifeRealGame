package com.rlrg.utillities.json;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.rlrg.utillities.annotation.JsonDTO;
import com.rlrg.utillities.annotation.JsonExport;

public class JsonExporter {
	
	/**
	 * Put an object to Map for encoding to json
	 * @param value
	 * @param clazz
	 * @return
	 */
	private Map<String,Object> putObjectToJSONMap(final Object value, final Class clazz){
		final Map<String, Object> jsonValue = new LinkedHashMap<String, Object>();
		final Field[] fields = clazz.getDeclaredFields();
		//
		try {
			for(Field field : fields){
				JsonExport jsonAnno = field.getAnnotation(JsonExport.class);
				if(null != jsonAnno){
					field.setAccessible(true);
					jsonValue.put(jsonAnno.name().toString(), field.get(value));
				}
			}
			return jsonValue;
		} catch(NullPointerException npe){
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}
	}
	
	/**
	 * Encode an object to a json string
	 * @param value
	 * @param clazz
	 * @return
	 */
	public String encodeObjectToJson(final Object value, final Class clazz){
		final JSONObject jObj = new JSONObject();
		//
		JsonDTO dtoAnno = (JsonDTO) clazz.getAnnotation(JsonDTO.class);
		final Map<String, Object> jsonValue = putObjectToJSONMap(value, clazz);
		jObj.put(dtoAnno.singularName(), jsonValue);
		//
		return jObj.toJSONString();					
	}
	
	/**
	 * Encode a list of object to a json string
	 * @param values
	 * @param clazz
	 * @return
	 */
	public String encodeObjectsToJson(final List<Object> values, final Class clazz){
		final JSONObject jObj = new JSONObject();
		final JsonDTO dtoAnno = (JsonDTO) clazz.getAnnotation(JsonDTO.class);
		if(null == dtoAnno){
			return null;
		}
		final List<Map<String, Object>> listValue = new LinkedList<Map<String, Object>>();
		for(Object value : values){
			final Map<String, Object> jsonValue = putObjectToJSONMap(value, clazz);
			listValue.add(jsonValue);
		}
		jObj.put(dtoAnno.pluralName(), listValue);
		return jObj.toJSONString();
	}
	
	/**
	 * From a json String, decode it to an object
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	private <T> T getObjectFromJSONString(final String jsonStr, final Class<T> clazz){
		
		final JSONObject jObj = (JSONObject)JSONValue.parse(jsonStr);
		final Field[] fields = clazz.getDeclaredFields();
		try {
			final T returnedObj = clazz.newInstance();
			for(Field field : fields){
				JsonExport jsonAnno = field.getAnnotation(JsonExport.class);
				if(null != jsonAnno){
					field.setAccessible(true);
					String valueTmp = jObj.get(jsonAnno.name()).toString();
					field.set(returnedObj, castValue(field.getType(), valueTmp));
				}
			}
			return returnedObj;
			
		} catch(NullPointerException npe){
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		} catch (InstantiationException e) {
			return null;
		}
	}
	
	/**
	 * Decode a json String to a list of object
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public <T> List<T>  decodeJsonToObjects(final String jsonStr, final Class<T> clazz){
		final JSONObject jObj = (JSONObject)JSONValue.parse(jsonStr);
		final JsonDTO dtoAnno = clazz.getAnnotation(JsonDTO.class);
		//
		final String dtoJsonName = dtoAnno.pluralName();
		if(null != jObj.get(dtoJsonName)){
			final List<T> valueList = new ArrayList<T>();
			final JSONArray array = (JSONArray) jObj.get(dtoJsonName);
			for(Object obj : array){
				valueList.add(getObjectFromJSONString(obj.toString(), clazz));
			}
			//
			return valueList;
		} else {
			return null;
		}
	}
	
	/**
	 * Decode a json string to an object
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public <T> T decodeJsonToObject(final String jsonStr, final Class<T> clazz){
		
		final JSONObject jObj = (JSONObject)JSONValue.parse(jsonStr);
		final JsonDTO dtoAnno = clazz.getAnnotation(JsonDTO.class);
		//
		final String dtoJsonName = dtoAnno.singularName();
		if(null != jObj.get(dtoJsonName)){
			return getObjectFromJSONString(jObj.get(dtoJsonName).toString(), clazz);
		} else {
			return null;
		}
	}
	
	/**
	 * Cast value from json to proper type
	 * @param type
	 * @param value
	 * @return
	 */
	private Object castValue(Class type, String value){
		if(type.getName().equals("java.lang.Integer")){
			return Integer.valueOf(value);
		} else if (type.getName().equals("java.lang.Long")){
			return Long.valueOf(value);
		} else if (type.getName().equals("java.lang.Double")){
			return Double.valueOf(value);
		} else {
			return value;
		}
	}
}
