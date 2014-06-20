package com.rlrg.dataserver.language.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.base.service.BaseService;
import com.rlrg.dataserver.language.dto.BadgeLangDTO;
import com.rlrg.dataserver.language.entity.BadgeLanguage;
import com.rlrg.dataserver.language.repository.BadgeLanguageRepository;

@Service
public class BadgeLanguageService extends BaseService<BadgeLanguage, BadgeLangDTO> {
	
	private final static Logger LOG = LoggerFactory.getLogger(BadgeLanguageService.class);
	
	@Autowired
	private BadgeLanguageRepository badgeLangRepo;
	
	public BadgeLanguage getBadgeLangByBadgeIdAndLangId(Integer badgeId, Integer languageId){
		return badgeLangRepo.getBadgeLangByBadgeIdAndLangId(badgeId, languageId);
	}

	public BadgeLanguage save(BadgeLanguage bl){
		return badgeLangRepo.save(bl);
	}

	@Override
	public BadgeLangDTO convertEntityToDTO(BadgeLanguage data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BadgeLanguage revertDTOToEntity(BadgeLangDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<BadgeLangDTO> getVClass() {
		return BadgeLangDTO.class;
	}
	
}
