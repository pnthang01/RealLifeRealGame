package com.rlrg.utillities.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.json.JsonExporter;

public abstract class BaseWebServiceReader<T>{
	private static final Logger LOG = LoggerFactory.getLogger(BaseWebServiceReader.class);
	
	private final String SERVER_URI = "http://localhost:9090/data/";
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	private final JsonExporter jsonExporter = new JsonExporter();
	
	protected abstract Class<T> getTClass();

	/**
	 * Accept part of url and their url parameters, combine that url with the uri domain to fully url
	 * Then get a json string from the fully url and decode it to a list of class T
	 * @param uri
	 * @param urlVars
	 * @return
	 * @throws ConvertException
	 */
	protected ResultList<T> getListOfObjects(String url, String moduleName, Object... urlParams) throws ConvertException, RestClientException{
		String finalUrl = new StringBuilder(SERVER_URI).append(url).toString();
		//
		String json = restTemplate.getForObject(finalUrl, String.class, urlParams);
		if(null == json){
			LOG.error("Received null result when reading data from url:{}.", finalUrl);
			throw new RestClientException("Received null result from url.");
		}
		//TODO
		//
		RestObject restobject = jsonExporter.decodeJsonToRestObject(json);
		if(restobject.getErrorCode() == RestObject.ERROR){
			if(restobject.getMsg().equals("The list of T values is null or empty.")){
				return null;
			} else {
				LOG.error("Error occurs when reading data from url:{}.", finalUrl);
				throw new ConvertException(restobject.getMsg());
			}
		}
		if(null == restobject.getData()){
			LOG.error("Decode json to object failed with json:{}", json);
			throw new ConvertException("Error occurs when decoding json to objects.");
		}
		List<T> list = jsonExporter.decodeJsonToObjects(restobject.getData().toString(), getTClass());
		//
		ResultList<T> resultList = new ResultList<T>();
		resultList.setTotal(restobject.getTotal());
		resultList.setList(list);
		//
		return resultList; 
	}
	
	/**
	 * Accept part of url and their url parameters, combine that url with the uri domain to fully url
	 * Then get a json string from the fully url and decode it to an object of class T
	 * @param url
	 * @param urlVars
	 * @return
	 * @throws ConvertException
	 * @throws RestClientException
	 */
	protected T getAnObject(String url, String moduleName, Object... urlParams) throws ConvertException, RestClientException{
		String finalUrl = new StringBuilder(SERVER_URI).append(url).toString();
		//
		String json = restTemplate.getForObject(finalUrl, String.class, urlParams);
		if(null == json){
			LOG.error("Received null result when reading data from url:{}.", finalUrl);
			throw new RestClientException("Received null result from url.");
		}
		//TODO
		//
		RestObject restobject = jsonExporter.decodeJsonToRestObject(json);
		if(restobject.getErrorCode() == RestObject.ERROR){
			LOG.error("Error occurs when reading data from url:{}.", finalUrl);
			throw new ConvertException(restobject.getMsg());
		}
		if(null == restobject.getData()){
			LOG.error("Decode json to object failed with json:{}", json);
			throw new ConvertException("Error occurs when decoding json to objects.");
		}
		//
		return jsonExporter.decodeJsonToObject(restobject.getData().toString(), getTClass());
	}
	
	/**
	 * Accept part of url and their url parameters, combine that url with the uri domain to fully url
	 * Then post a json string from the fully url and decode it to an object of class T
	 * @param url
	 * @param moduleName
	 * @param params
	 * @return
	 * @throws ConvertException
	 */
	protected T postAnObject(String url, String moduleName, Object... params) throws ConvertException{
		String finalUrl = new StringBuilder(SERVER_URI).append(url).toString();
		//
		String json = restTemplate.postForObject(finalUrl, null, String.class, params);
		if(null == json){
			LOG.error("Received null result when reading data from url:{}.", finalUrl);
			throw new RestClientException("Received null result from url.");
		}
		//TODO
		//
		RestObject restobject = jsonExporter.decodeJsonToRestObject(json);
		if(restobject.getErrorCode() == RestObject.ERROR){
			LOG.error("Error occurs when reading data from url:{}.", finalUrl);
			throw new ConvertException(restobject.getMsg());
		}
		if(null == restobject.getData()){
			LOG.error("Decode json to object failed with json:{}", json);
			throw new ConvertException("Error occurs when decoding json to objects.");
		}
		return jsonExporter.decodeJsonToObject(restobject.getData().toString(), getTClass());
	}
	
	/**
	 * Accept part of url and an object of class T, then encode it to json.
	 * And post this json to data server, the result will be whether OK or ERROR
	 * @param url
	 * @param objectT
	 * @return
	 * @throws ConvertException
	 */
	protected boolean postAnObjectT(String url, String moduleName, T objectT) throws ConvertException{
		String finalUrl = new StringBuilder(SERVER_URI).append(url).toString();
		//
		String json = jsonExporter.encodeObjectToJson(objectT);
		if(null == json){
			LOG.error("Encode object {} with class {} to json failed.", objectT, objectT.getClass());
			throw new ConvertException("Error when encoding an object to json string.");
		}
		//
		String resultJson = restTemplate.postForObject(finalUrl, null, String.class, json);
		if(null == resultJson){
			LOG.error("Received null result when reading data from url:{}.", finalUrl);
			throw new RestClientException("Received null result from url.");
		}
		//TODO
		//
		RestObject restobject = jsonExporter.decodeJsonToRestObject(resultJson);
		//
		return restobject.getErrorCode() == RestObject.OK;
	}
	
	/**
	 * Accept part of url and list of url paramerters.
	 * And post this json to data server, the result will be whether OK or ERROR
	 * @param url
	 * @param urlParams
	 * @return
	 */
	protected boolean postListOfParameters(String url, String moduleName, Object... urlParams){
		String finalUrl = new StringBuilder(SERVER_URI).append(url).toString();
		//
		String resultJson = restTemplate.postForObject(finalUrl, null, String.class, urlParams);
		if(null == resultJson){
			LOG.error("Received null result when reading data from url:{}.", finalUrl);
			throw new RestClientException("Received null result from url.");
		}
		//TODO
		RestObject restobject = jsonExporter.decodeJsonToRestObject(resultJson);
		//
		return restobject.getErrorCode() == RestObject.OK;
	}
	
}
