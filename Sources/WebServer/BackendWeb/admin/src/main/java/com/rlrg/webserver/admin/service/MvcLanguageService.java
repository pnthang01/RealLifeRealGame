package com.rlrg.webserver.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.language.dto.LanguageDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.service.LanguageWebServiceReader;

@Service
public class MvcLanguageService {
	
	@Autowired
	private LanguageWebServiceReader languageReader;

	public ResultList<LanguageDTO> getAllLanguages() throws RestClientException, ConvertException{
		return languageReader.getAllLanguages();
	}
}
