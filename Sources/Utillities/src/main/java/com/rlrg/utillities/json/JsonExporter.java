package com.rlrg.utillities.json;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.rlrg.utillities.annotation.JsonDTO;
import com.rlrg.utillities.annotation.JsonExport;
import com.rlrg.utillities.annotation.JsonObject;
import com.rlrg.utillities.domain.RestObject;

public class JsonExporter {

	/**
	 * Put an object to Map for encoding to json, it also encodes a list of
	 * parameted object
	 * 
	 * @param value
	 * @param clazz
	 * @return
	 */
	private Map<String, Object> putObjectToJSONMap(final Object value,final Class clazz) {
		final Map<String, Object> jsonValue = new LinkedHashMap<String, Object>();
		final Field[] fields = clazz.getDeclaredFields();
		//
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				// if this field is primitive type, parse this value to string
				// and add it to json
				JsonExport jsonAnno = field.getAnnotation(JsonExport.class);
				if (null != jsonAnno && null != field.get(value)) {
					jsonValue.put(jsonAnno.name().toString(),
							parseValueToString(field.get(value)));
				}
				// if this field is object type, it will check if it is an
				// object or a list of object
				JsonObject jsonObj = field.getAnnotation(JsonObject.class);
				if (null != jsonObj) {
					final Object fieldValue = field.get(value);
					final Class objClass = fieldValue.getClass();
					// This field is a list, so it would be parse each object
					// from the list to json
					if(objClass.getName().equals("java.util.List") || objClass.getName().equals("java.util.ArrayList")) {
						List list = (List) objClass.cast(fieldValue);
						if(!list.isEmpty()){
							JSONArray array = new JSONArray();
							for (Object obj : list) {
								Map<String, Object> objTempValue = putObjectToJSONMap(obj, obj.getClass());
								array.add(objTempValue);		
							}
							//
							JsonDTO objAnno = list.get(0).getClass().getAnnotation(JsonDTO.class);
							jsonValue.put(objAnno.pluralName(), array);
						}
					} else {
						// This field is an object, it will put this field to
						// recursive method.
						JsonDTO objAnno = (JsonDTO) objClass.getAnnotation(JsonDTO.class);
						if (null != objAnno) {
							Map<String, Object> objTempValue = putObjectToJSONMap(
									field.get(value), objClass);
							jsonValue.put(objAnno.singularName(), objTempValue);
						}
					}
				}
			}
			return jsonValue;
		} catch (NullPointerException npe) {
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		}
	}

	/**
	 * Encode an object to a json string
	 * 
	 * @param value
	 * @param clazz
	 * @return
	 */
	public <T> String encodeObjectToJson(final T value) {
 		JsonDTO dtoAnno = (JsonDTO) value.getClass().getAnnotation(JsonDTO.class);
		if(null == dtoAnno){
			return null;
		}
		final RestObject restObject = RestObject.fromData(value);
		//
		final Map<String, Object> jsonValue = putObjectToJSONMap(restObject, RestObject.class);
		return JSONValue.toJSONString(jsonValue);
	}

	/**
	 * Encode a list of object to a json string
	 * 
	 * @param values
	 * @param clazz
	 * @return
	 */
	public <T> String encodeObjectsToJson(final List<T> values) {
		if(null == values){
			return null;
		}
		final JsonDTO dtoAnno = (JsonDTO) values.get(0).getClass().getAnnotation(JsonDTO.class);
		if (null == dtoAnno) {
			return null;
		}
		final RestObject restObject = RestObject.fromData(values);
		final Map<String, Object> jsonValue = putObjectToJSONMap(restObject, RestObject.class);
		//
		return JSONValue.toJSONString(jsonValue);
	}

	/**
	 * From a json String, decode it to an object
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	private <T> T getObjectFromJSONString(final String jsonStr,
			final Class<T> clazz) {

		final JSONObject jObj = (JSONObject) JSONValue.parse(jsonStr);
		final Field[] fields = clazz.getDeclaredFields();
		try {
			final T returnedObj = clazz.newInstance();
			for (Field field : fields) {
				field.setAccessible(true);
				//
				JsonExport jsonAnno = field.getAnnotation(JsonExport.class);
				if (null != jsonAnno) {
					String valueTmp = jObj.get(jsonAnno.name()).toString();
					field.set(returnedObj, castValue(field.getType(), valueTmp));
				}
				//
				JsonObject jsonObj = field.getAnnotation(JsonObject.class);
				if (null != jsonObj) {
					Class objClass = field.getType();
					if (objClass.getName().equals("java.util.List")) {
						List list = new ArrayList();
						Class genericClass = getGenericClassOfList(field);
						JsonDTO objAnno = (JsonDTO) genericClass
								.getAnnotation(JsonDTO.class);
						if (null != jObj.get(objAnno.pluralName())) {
							JSONArray array = (JSONArray) jObj.get(objAnno
									.pluralName());
							//
							for (Object obj : array) {
								list.add(getObjectFromJSONString(
										obj.toString(), genericClass));
							}
							//
							field.set(returnedObj, list);
						}
					} else {
						JsonDTO objAnno = (JsonDTO) objClass
								.getAnnotation(JsonDTO.class);
						if (null != jObj.get(objAnno.singularName())) {
							String valueTmp = jObj.get(objAnno.singularName())
									.toString();
							Object objTmp = getObjectFromJSONString(valueTmp,
									objClass);
							//
							field.set(returnedObj, objTmp);
						}
					}
				}
			}
			return returnedObj;

		} catch (NullPointerException npe) {
			npe.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Decode a json String to a list of object
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public <T> List<T> decodeJsonToObjects(final String jsonStr,
			final Class<T> clazz) {
		final JSONObject jObj = (JSONObject) JSONValue.parse(jsonStr);
		final JsonDTO dtoAnno = clazz.getAnnotation(JsonDTO.class);
		//
		final String dtoJsonName = dtoAnno.pluralName();
		if (null != jObj.get(dtoJsonName)) {
			final List<T> valueList = new ArrayList<T>();
			final JSONArray array = (JSONArray) jObj.get(dtoJsonName);
			for (Object obj : array) {
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
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public <T> T decodeJsonToObject(final String jsonStr, final Class<T> clazz) {

		final JSONObject jObj = (JSONObject) JSONValue.parse(jsonStr);
		final JsonDTO dtoAnno = clazz.getAnnotation(JsonDTO.class);
		//
		final String singularName = dtoAnno.singularName();
		if (null != jObj.get(singularName)) {
			return getObjectFromJSONString(jObj.get(singularName).toString(),
					clazz);
		}
		//
		//
		return null;
	}

	private Object parseValueToString(Object value) {
		if (value instanceof Date) {
			return ((Date) value).getTime();
		} else {
			return value.toString();
		}
	}

	/**
	 * Cast value from json to proper type
	 * 
	 * @param type
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	private Object castValue(Class type, String value) throws ParseException {
		if (type.getName().equals("java.lang.Integer")) {
			return Integer.valueOf(value);
		} else if (type.getName().equals("java.lang.Long")) {
			return Long.valueOf(value);
		} else if (type.getName().equals("java.lang.Double")) {
			return Double.valueOf(value);
		} else if (type.getName().equals("java.util.Date")) {
			return new Date(Long.valueOf(value));
		} else {
			return value;
		}
	}

	private Class getGenericClassOfList(Field field) {
		ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
		Class<?> genericClass = (Class<?>) stringListType.getActualTypeArguments()[0];
		return genericClass;
	}
}
