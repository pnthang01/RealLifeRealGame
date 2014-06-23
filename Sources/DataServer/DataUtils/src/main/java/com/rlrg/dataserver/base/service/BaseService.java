package com.rlrg.dataserver.base.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rlrg.dataserver.base.domain.CountableDTO;
import com.rlrg.utillities.badgechecker.BaseSource;
import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.json.JsonExporter;


public abstract class BaseService <T, V> extends BaseSource{

	@Autowired
	private JsonExporter jsonExporter;
	
	protected abstract V convertEntityToDTO(T data);
	
	protected abstract T revertDTOToEntity(V dto);
	
	protected abstract Class<V> getVClass();

	/**
	 * Encode counting services for statistic to json.
	 * @param count
	 * @param controllerName
	 * @return
	 * @throws ConvertException
	 */
	public String encodeCountingRestObject(Long count, String controllerName) throws ConvertException{
		CountableDTO dto = new CountableDTO();
		dto.setCount(count);
		dto.setControllerName(controllerName);
		return jsonExporter.encodeObjectToJson(dto);
	}
	
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
	 * @throws ConvertException 
	 */
	public String encodeSingleObjectFromTdata(T data) throws ConvertException{
		V dto = convertEntityToDTO(data);
		//
		return jsonExporter.encodeObjectToJson(dto);
	}
	
	/**
	 * Encode V dto to json string
	 * @param dto
	 * @return
	 * @throws ConvertException 
	 */
	public String encodeSingleObjectFromVdto(V dto) throws ConvertException{
		return jsonExporter.encodeObjectToJson(dto);
	}

	/**
	 * Convert list of T data to list of V dto and encode the converted list to json string
	 * @param list
	 * @return
	 * @throws ConvertException 
	 */
	public String encodeMutipleObjectsFromListT(List<T> list) throws ConvertException{
		List<V> jsonValues = new ArrayList<V>();
		for(T obj : list){
			V dto = convertEntityToDTO(obj);
			if(null == dto){
				throw new ConvertException("Cannot convert Language entity To Language DTO.");
			}
			jsonValues.add(dto);
		}
		//
		return jsonExporter.encodeObjectsToJson(jsonValues);
	}
	
	/**
	 * Encode a list of V dto to json string
	 * @param list
	 * @return
	 * @throws ConvertException 
	 */
	public String encodeMutipleObjectsFromListV(List<V> list) throws ConvertException{
		return jsonExporter.encodeObjectsToJson(list);
	}
	
	/**
	 * Decode json string to a V dto
	 * @param json
	 * @return
	 * @throws ConvertException 
	 */
	public V decodeSingleObject(String json) throws ConvertException{
		RestObject restobject = jsonExporter.decodeJsonToRestObject(json);
		return jsonExporter.decodeJsonToObject(restobject.getData().toString(), getVClass());
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
