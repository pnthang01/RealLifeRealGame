package com.rlrg.dataserver.base.service;

import java.util.List;

import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.ConvertException;

public interface IBaseService<T, V> {
	
	public V convertEntityToDTO(T data);
	
	public T revertDTOToEntity(V dto);
	
	public String encodeCheckingRestObject(Boolean result) throws ConvertException;

	public String encodeCountingRestObject(Long count, String controllerName) throws ConvertException;
	
	public String encodeBlankRestObject(RestObject restObject);
	
	public String encodeSingleObjectFromTdata(T data) throws ConvertException;
	
	public String encodeSingleObjectFromVdto(V dto) throws ConvertException;
	
	public String encodeMutipleObjectsFromListT(List<T> list) throws ConvertException;
	
	public String encodeMutipleObjectsFromListV(List<V> list) throws ConvertException;
	
	public String encodeMutipleObjectsFromListV(List<V> list, Long total) throws ConvertException;
	
	public V decodeSingleObject(String json) throws ConvertException;
	
	public List<V> decodeMutipleObjects(String json);
}
