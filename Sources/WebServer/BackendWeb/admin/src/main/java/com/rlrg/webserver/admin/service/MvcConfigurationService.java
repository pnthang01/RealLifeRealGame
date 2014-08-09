package com.rlrg.webserver.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.dto.ConfigDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.service.ConfigurationWebServiceReader;

@Service
public class MvcConfigurationService {

	@Autowired
	private ConfigurationWebServiceReader configReader;
	
	public ResultList<ConfigDTO> getAllConfigurations(Integer pageNumber) throws RestClientException, ConvertException{
		return configReader.getAllConfig(pageNumber);
	}
	
	public ResultList<ConfigDTO> searchConfigByKeyword(String keyword, Integer pageNumber) throws RestClientException, ConvertException{
		return configReader.searchConfigByKeyword(keyword, pageNumber);
	}
}
