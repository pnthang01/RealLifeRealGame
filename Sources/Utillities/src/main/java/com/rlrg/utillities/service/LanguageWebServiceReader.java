package com.rlrg.utillities.service;

import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.language.dto.LanguageDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;

public class LanguageWebServiceReader extends BaseWebServiceReader<LanguageDTO> {
	
	private final String MODULE_NAME = "LanguageModule";
	
	private final String GET_ALL_LANGUAGES_URL = "language/getAllLanguages";
	
	public ResultList<LanguageDTO> getAllLanguages() throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_LANGUAGES_URL, MODULE_NAME);
	}

	@Override
	protected Class<LanguageDTO> getTClass() {
		return LanguageDTO.class;
	}

}
