package com.rlrg.dataserver.utils.base.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.json.JsonExporter;


public abstract class BaseService <T, V>{
	@Autowired
	private JsonExporter jsonExporter;
	
	public abstract V convertEntityToDTO(T data);
	
	public abstract T revertDTOToEntity(V dto);
	
	public abstract Class<V> getVClass();
	
	/**
	 * If the client just needs the result without data, 
	 * we can encode a blank RestOjbect to make the result
	 * @param restObject
	 */
	public String encodeBlankRestObject(RestObject restObject){
		return jsonExporter.encodeBlankRestObject(restObject);
	}
	
	/**
	 * Convert T data to V dto and encode V dto to json string
	 * @param data
	 * @return
	 */
	public String encodeSingleObjectFromTdata(T data){
		V dto = convertEntityToDTO(data);
		//
		return jsonExporter.encodeObjectToJson(dto);
	}
	
	/**
	 * Encode V dto to json string
	 * @param dto
	 * @return
	 */
	public String encodeSingleObjectFromVdto(V dto){
		return jsonExporter.encodeObjectToJson(dto);
	}

	/**
	 * Convert list of T data to list of V dto and encode the converted list to json string
	 * @param list
	 * @return
	 */
	public String encodeMutipleObjectsFromListT(List<T> list){
		List<V> jsonValues = new ArrayList<V>();
		for(T obj : list){
			jsonValues.add(convertEntityToDTO(obj));
		}
		//
		return jsonExporter.encodeObjectsToJson(jsonValues);
	}
	
	/**
	 * Encode a list of V dto to json string
	 * @param list
	 * @return
	 */
	public String encodeMutipleObjectsFromListV(List<V> list){
		return jsonExporter.encodeObjectsToJson(list);
	}
	
	/**
	 * Decode json string to a V dto
	 * @param json
	 * @return
	 */
	public V decodeSingleObject(String json){
		RestObject test = jsonExporter.decodeJsonToRestObject(json);
		System.out.println(test);
		return jsonExporter.decodeJsonToObject(test.getData().toString(), getVClass());
	}
	
	/**
	 * Decode json string to list of V dto
	 * @param json
	 * @return
	 */
	public List<V> decodeMutipleObjects(String json){
		return jsonExporter.decodeJsonToObjects(json, getVClass());
	}
}
