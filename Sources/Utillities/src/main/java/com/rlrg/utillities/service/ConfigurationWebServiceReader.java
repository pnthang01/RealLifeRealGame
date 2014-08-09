package com.rlrg.utillities.service;

import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.dto.ConfigDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;

public class ConfigurationWebServiceReader extends BaseWebServiceReader<ConfigDTO>{
	private final String MODULE_NAME = "ConfigurationModule";
	
	private final String GET_ALL_CONFIG_URL = "configuration/getAllConfig?pageNumber={pageNumber}";
	private final String SEARCH_CONFIGS_BY_KEYWORD_URL = "configuration/searchConfigs?keyword={keyword}&pageNumber={pageNumber}";
	
	public ResultList<ConfigDTO> getAllConfig(Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_CONFIG_URL, MODULE_NAME, pageNumber);
	}
	
	public ResultList<ConfigDTO> searchConfigByKeyword(String keyword, Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(SEARCH_CONFIGS_BY_KEYWORD_URL, MODULE_NAME, keyword, pageNumber);
	}

	@Override
	protected Class<ConfigDTO> getTClass() {
		return ConfigDTO.class;
	}
}
