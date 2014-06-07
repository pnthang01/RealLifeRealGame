package com.rlrg.dataserver.badge.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rlrg.dataserver.badge.dto.BadgeDTO;
import com.rlrg.dataserver.badge.entity.Badge;
import com.rlrg.dataserver.badge.entity.enums.BadgeStatus;
import com.rlrg.dataserver.badge.repository.BadgeRepository;
import com.rlrg.dataserver.language.entity.BadgeLanguage;
import com.rlrg.dataserver.language.entity.Language;
import com.rlrg.dataserver.language.service.BadgeLanguageService;
import com.rlrg.dataserver.utils.base.controller.WebVariables;
import com.rlrg.dataserver.utils.base.exception.RepositoryException;
import com.rlrg.dataserver.utils.base.service.BaseService;

@Service
public class BadgeService extends BaseService<Badge, BadgeDTO>{
	
	private static final Logger LOG = LoggerFactory.getLogger(BadgeService.class);

	@Autowired
	private BadgeRepository badgeRepo;
	
	@Autowired
	private BadgeLanguageService badgeLangService;
	
	@Autowired
	private Language DEFAULT_LANGUAGE;
	
	public Badge findBadgeById(Integer badgeId){
		return badgeRepo.findOne(badgeId);
	}
	
	public List<BadgeDTO> getAllBadges(Integer pageNumber){
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, WebVariables.PAGE_SIZE);
		//
		return badgeRepo.getAllBadgeDTO(DEFAULT_LANGUAGE.getId(), pageRequest);
	}
	
	public List<BadgeDTO> getBadgesByStatus(BadgeStatus status, Integer pageNumber){
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, WebVariables.PAGE_SIZE);
		//
		return badgeRepo.getBadgeDTOByStatus(status, DEFAULT_LANGUAGE.getId(), pageRequest);
	}
			
	@Transactional
	public void update(BadgeDTO dto) throws RepositoryException{
		try {
			Badge b = badgeRepo.findOne(dto.getId());
			//
			BadgeLanguage badgeLang = badgeLangService.getBadgeLangByBadgeIdAndLangId(b.getId(), DEFAULT_LANGUAGE.getId());
			badgeLang.setName(dto.getName());
			badgeLang.setDescription(dto.getDescription());
			badgeLangService.save(badgeLang);
			//
			b.setStatus(dto.getStatus());
			b.setStatus(dto.getStatus());
			badgeRepo.save(b);
			//
		} catch(Exception e){
			LOG.error("<< Error occurs when updating badge with id: " + dto.getId(), e);
			throw new RepositoryException("Error occurs when updating badge.");
		}
	}
	
	
	@Override
	public BadgeDTO convertEntityToDTO(Badge data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Badge revertDTOToEntity(BadgeDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<BadgeDTO> getVClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
