package com.rlrg.utillities.json;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.rowset.CachedRowSet;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.rlrg.utillities.annotation.JsonExport;

public class JsonExporter {
	
	public static String encodeObjectToJson(final Object value, final Class clazz){

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
			return JSONValue.toJSONString(jsonValue);
			
		} catch(NullPointerException npe){
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}
	}
	
	public static <T> T decodeJsonToObject(final String jsonStr, final Class<T> clazz){
		
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
	
	private static Object castValue(Class type, String value){
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
