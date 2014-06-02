package com.rlrg.dataserver.language.service;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.language.dto.CateLangDTO;
import com.rlrg.dataserver.language.entity.CategoryLanguage;
import com.rlrg.dataserver.language.repository.CategoryLanguageRepository;
import com.rlrg.dataserver.utils.base.service.BaseService;

@Service
public class CategoryLanguageService extends BaseService<CategoryLanguage, CateLangDTO>{
	@Transient
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CategoryLanguageRepository cateLangRepo;

	@Override
	public CateLangDTO convertEntityToDTO(CategoryLanguage data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryLanguage revertDTOToEntity(CateLangDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<CateLangDTO> getVClass() {
		// TODO Auto-generated method stub
		return null;
	}
}
