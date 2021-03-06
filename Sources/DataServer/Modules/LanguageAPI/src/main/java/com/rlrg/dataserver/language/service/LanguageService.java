package com.rlrg.dataserver.language.service;

import java.util.List;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.base.service.BaseService;
import com.rlrg.dataserver.base.service.ILanguageService;
import com.rlrg.dataserver.language.dto.LanguageDTO;
import com.rlrg.dataserver.language.entity.Language;
import com.rlrg.dataserver.language.repository.LanguageRepository;
import com.rlrg.dataserver.utillities.Constants;

@Service
public class LanguageService extends BaseService<Language, LanguageDTO> implements ILanguageService<Language, LanguageDTO>{
	@Transient
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Bean(name="DEFAULT_LANGUAGE")
	public Language defaultLanguage(){
		return this.getLanguageByI18N(Constants.DEFAULT_I18N);
	}

	@Autowired
	private LanguageRepository langRepo;
	
	public Language getLanguageByI18N(String i18n){
		return langRepo.getLanguageByI18N(i18n);
	}
	
	public List<Language> getAllLanguage(){
		return langRepo.getAllLanguages();
	}

	@Override
	public LanguageDTO convertEntityToDTO(Language data) {
		LanguageDTO dto = new LanguageDTO();
		dto.setCountry(data.getCountry());
		dto.setI18n(data.getI18n());
		dto.setId(data.getId());
		dto.setLanguage(data.getLanguage());
		//
		return dto;
	}

	@Override
	public Language revertDTOToEntity(LanguageDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Search a list of language base on keyword
	 * @param keyword
	 * @param pageNumber
	 * @return
	 */
	public List<LanguageDTO> searchLanguagesByKeyword(String keyword, Integer pageNumber){
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, Constants.PAGE_SIZE);
		//
		return langRepo.searchLanguagesDTOByKeyword(keyword, pageRequest);
	}
	

	public Long countLanguagesByKeyword(String keyword) {
		return langRepo.countLanguagesByKeyword(keyword);
	}

	@Override
	public Class<LanguageDTO> getVClass() {
		return LanguageDTO.class;
	}

}
