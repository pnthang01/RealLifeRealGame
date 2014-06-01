package com.rlrg.dataserver.utils.base.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rlrg.utillities.json.JsonExporter;


public abstract class BaseService <T, V>{
	@Autowired
	private JsonExporter jsonExporter;
	
	public abstract V convertEntityToDTO(T data);
	
	public String encodeSingleObject(T data){
		V dto = convertEntityToDTO(data);
		//
		return jsonExporter.encodeObjectToJson(dto);
	}

	public String encodeMutipleObjects(List<T> list){
		List<V> jsonValues = new ArrayList<V>();
		for(T obj : list){
			jsonValues.add(convertEntityToDTO(obj));
		}
		//
		return jsonExporter.encodeObjectsToJson(jsonValues);
	}
}
