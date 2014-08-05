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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rlrg.utillities.annotation.JsonDTO;
import com.rlrg.utillities.annotation.JsonExport;
import com.rlrg.utillities.annotation.JsonObject;
import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.ConvertException;

public class JsonExporter {
	private static Logger LOG = LoggerFactory.getLogger(JsonExporter.class);
	
	/**
	 * Put an object to Map for encoding to json, it also encodes a list of
	 * parameted object
	 * 
	 * @param value
	 * @param clazz
	 * @return
	 * @throws IllegalAccessException 
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> putObjectToJSONMap(final Object value,final Class<?> clazz){
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
					final Object fieldValue = field.get(value);
					final Class<?> objClass = fieldValue.getClass();
					if(objClass.getName().equals("java.util.List") || objClass.getName().equals("java.util.ArrayList")
							|| objClass.getName().equals("java.util.Arrays$ArrayList")) {
						List<?> list = (List<?>) objClass.cast(fieldValue);
						if(!list.isEmpty()){
							JSONArray array = new JSONArray();
							for (Object obj : list) {
								array.add(obj);		
							}
							//
							jsonValue.put(jsonAnno.name().toString(), array);
						}
					} else {
						jsonValue.put(jsonAnno.name().toString(), parseValueToString(field.get(value)));
					}
				}
				// if this field is object type, it will check if it is an
				// object or a list of object
				JsonObject jsonObjAnno = field.getAnnotation(JsonObject.class);
				if (null != jsonObjAnno && null != field.get(value)) {
					final Object fieldValue = field.get(value);
					final Class<?> objClass = fieldValue.getClass();
					// This field is a list, so it would be parse each object
					// from the list to json
					if(objClass.getName().equals("java.util.List") || objClass.getName().equals("java.util.ArrayList")
							|| objClass.getName().equals("java.util.Arrays$ArrayList")) {
						List<?> list = (List<?>) objClass.cast(fieldValue);
						if(!list.isEmpty()){
							JSONArray array = new JSONArray();
							for (Object obj : list) {
								Map<String, Object> objTempValue = putObjectToJSONMap(obj, obj.getClass());
								array.add(objTempValue);		
							}
							//
							JsonDTO objAnno = list.get(0).getClass().getAnnotation(JsonDTO.class);
							if(null != jsonObjAnno && !jsonObjAnno.name().isEmpty()){
								Map<String, Object> jsonObj = new LinkedHashMap<String, Object>();
								jsonObj.put(objAnno.pluralName(), array);
								jsonValue.put(jsonObjAnno.name(), jsonObj);
							} else {
								jsonValue.put(objAnno.pluralName(), array);
							}
						}
					} else {
						// This field is an object, it will put this field to
						// recursive method.
						Map<String, Object> objTempValue = null;
						JsonDTO objAnno = (JsonDTO) objClass.getAnnotation(JsonDTO.class);
						if (null != objAnno) {
							objTempValue = putObjectToJSONMap(field.get(value), objClass);
							//jsonValue.put(objAnno.singularName(), objTempValue);
						}
						if(null != jsonObjAnno && !jsonObjAnno.name().isEmpty()){
							Map<String, Object> jsonObj = new LinkedHashMap<String, Object>();
							jsonObj.put(objAnno.singularName(), objTempValue);
							jsonValue.put(jsonObjAnno.name(), jsonObj);
						} else {
							jsonValue.put(objAnno.singularName(), objTempValue);
						}
					}
				}
			}
			return jsonValue;
		} catch (NullPointerException e) {
			LOG.error("Error while encode object to json for class: " + clazz.getName(), e);
			return null;
		} catch (IllegalArgumentException e) {
			LOG.error("Error while encode object to json for class: " + clazz.getName(), e);
			return null;
		} catch (IllegalAccessException e) {
			LOG.error("Error while encode object to json for class: " + clazz.getName(), e);
			return null;
		}
	}

	/**
	 * Encode a blank data #RestObject to json
	 * @param restObj
	 * @return
	 * @throws IllegalAccessException 
	 */
	public String encodeBlankRestObject(RestObject restObj){
		final Map<String, Object> jsonValue = putObjectToJSONMap(restObj, RestObject.class);
		return JSONValue.toJSONString(jsonValue);
	}
	
	/**
	 * Encode an object to a json string
	 * 
	 * @param value
	 * @param clazz
	 * @return
	 * @throws ConvertException 
	 * @throws IllegalAccessException 
	 */
	public <T> String encodeObjectToJson(final T value) throws ConvertException{
 		JsonDTO dtoAnno = (JsonDTO) value.getClass().getAnnotation(JsonDTO.class);
		if(null == dtoAnno){
			throw new ConvertException("This DTO doesn't containt JsonDTO annotation.");
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
	 * @throws ConvertException 
	 * @throws IllegalAccessException 
	 */
	public <T> String encodeObjectsToJson(final List<T> values) throws ConvertException {
		if(null == values || values.isEmpty() || null == values.get(0)){
			throw new ConvertException("The list of T values is null or empty.");
		}
		final JsonDTO dtoAnno = (JsonDTO) values.get(0).getClass().getAnnotation(JsonDTO.class);
		if (null == dtoAnno) {
			throw new ConvertException("This DTO doesn't containt JsonDTO annotation.");
		}
		final RestObject restObject = RestObject.fromData(values);
		final Map<String, Object> jsonValue = putObjectToJSONMap(restObject, RestObject.class);
		//
		return JSONValue.toJSONString(jsonValue);
	}
	
	/**
	 * Encode a list of object to a json string, and it's total count
	 * 
	 * @param values
	 * @param clazz
	 * @return
	 * @throws ConvertException 
	 * @throws IllegalAccessException 
	 */
	public <T> String encodeObjectsToJson(final List<T> values, Long total) throws ConvertException {
		if(null == values || values.isEmpty() || null == values.get(0)){
			throw new ConvertException("The list of T values is null or empty.");
		}
		final JsonDTO dtoAnno = (JsonDTO) values.get(0).getClass().getAnnotation(JsonDTO.class);
		if (null == dtoAnno) {
			throw new ConvertException("This DTO doesn't containt JsonDTO annotation.");
		}
		final RestObject restObject = RestObject.fromData(values);
		restObject.setTotal(total);
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
		//Parse json string to JSON Object to manage
		final JSONObject jObj = (JSONObject) JSONValue.parse(jsonStr);
		final Field[] fields = clazz.getDeclaredFields();
		try {
			final T returnedObj = clazz.newInstance();
			for (Field field : fields) {
				field.setAccessible(true);
				// If this field is primitive type, it will have JsonExport annotation
				// We will cast string to their type and set value
				JsonExport jsonAnno = field.getAnnotation(JsonExport.class);
				if (null != jsonAnno && null != jObj.get(jsonAnno.name())) {
					Class<?> objClass = field.getType();
					String valueTmp = jObj.get(jsonAnno.name()).toString();
					//
					if(objClass.getName().equals("java.util.List") || objClass.getName().equals("java.util.ArrayList")){
						Class<?> genericClass = getGenericClassOfList(field);
						JSONArray array = (JSONArray) jObj.get(jsonAnno.name());
						//
						field.set(returnedObj, array);
					} else {
						field.set(returnedObj, castValue(field.getType(), valueTmp));
					}
				}
				// Otherwise, this field is a object type.
				JsonObject jsonObj = field.getAnnotation(JsonObject.class);
				if (null != jsonObj) {
					Class<?> objClass = field.getType();
					//If this object type is a list, we will get generic class of this list.
					//After that, do a recursive loop of this method to decode json to this class and set value.
					if (objClass.getName().equals("java.util.List") || objClass.getName().equals("java.util.ArrayList")) {
						List list = new ArrayList();
						Class<?> genericClass = getGenericClassOfList(field);
						JsonDTO objAnno = (JsonDTO) genericClass.getAnnotation(JsonDTO.class);
						if (null != jObj.get(objAnno.pluralName())) {
							JSONArray array = (JSONArray) jObj.get(objAnno.pluralName());
							//
							for (Object obj : array) {
								list.add(getObjectFromJSONString(obj.toString(), genericClass));
							}
							//
							field.set(returnedObj, list);
						}
					} else if(objClass.getName().equals("java.lang.Object")){
						// If this class is just a Object class, no need to cast value. Instead set json string directly
						if(null != jObj.get(jsonObj.name())){
							field.set(returnedObj, jObj.get(jsonObj.name()).toString());
						}
					} else {
						//If this is just an object type, do a recursive method to decode json string to this class and set value.
						JsonDTO objAnno = (JsonDTO) objClass.getAnnotation(JsonDTO.class);
						if (null != objAnno && null != jObj.get(objAnno.singularName())) {
							String valueTmp = jObj.get(objAnno.singularName()).toString();
							Object objTmp = getObjectFromJSONString(valueTmp, objClass);
							//
							field.set(returnedObj, objTmp);
						}
					}
				}
			}
			return returnedObj;

		} catch (NullPointerException e) {
			LOG.error("Error while decode json to object for class: " + clazz.getName(), e);
			return null;
		} catch (IllegalArgumentException e) {
			LOG.error("Error while decode json to object for class: " + clazz.getName(), e);
			return null;
		} catch (IllegalAccessException e) {
			LOG.error("Error while decode json to object for class: " + clazz.getName(), e);
			return null;
		} catch (InstantiationException e) {
			LOG.error("Error while decode json to object for class: " + clazz.getName(), e);
			return null;
		} catch (ParseException e) {
			LOG.error("Error while decode json to object for class: " + clazz.getName(), e);
			return null;
		}
	}


	/**
	 * Decode json string to #RestObject
	 * @param jsonStr
	 * @return
	 */
	public RestObject decodeJsonToRestObject(String jsonStr){
		return getObjectFromJSONString(jsonStr, RestObject.class);
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
	 * @throws ConvertException 
	 */
	public <T> T decodeJsonToObject(final String jsonStr, final Class<T> clazz) throws ConvertException {
		if(null == jsonStr || jsonStr.isEmpty()){
			throw new ConvertException("Json string to be parsed is null or empty.");
		}
		if(null == clazz){
			throw new ConvertException("Class is needed to convert Json string to object.");
		}
		//
		final JSONObject jObj = (JSONObject) JSONValue.parse(jsonStr);
		final JsonDTO dtoAnno = clazz.getAnnotation(JsonDTO.class);
		//
		final String singularName = dtoAnno.singularName();
		if (null != jObj.get(singularName)) {
			return getObjectFromJSONString(jObj.get(singularName).toString(), clazz);
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
	private Object castValue(Class<?> type, String value) throws ParseException {
		if (type.getName().equals("java.lang.Integer") || int.class.equals(type)) {
			return Integer.valueOf(value);
		} else if (type.getName().equals("java.lang.Long") || long.class.equals(type)) {
			return Long.valueOf(value);
		} else if (type.getName().equals("java.lang.Double") || double.class.equals(type)) {
			return Double.valueOf(value);
		} else if(type.getName().equals("java.lang.Boolean") || boolean.class.equals(type)){
			return Boolean.valueOf(value);
		} else if (type.getName().equals("java.util.Date")) {
			return new Date(Long.valueOf(value));
		} else if (type.isEnum()){
			for(Object obj : type.getEnumConstants()){
				if(obj.toString().equals(value))
					return obj;
			}
			return null;
		} else {
			return value;
		}
	}

	private Class<?> getGenericClassOfList(Field field) {
		ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
		Class<?> genericClass = (Class<?>) stringListType.getActualTypeArguments()[0];
		return genericClass;
	}
}
