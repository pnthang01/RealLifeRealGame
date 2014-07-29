package com.rlrg.dataserver.badge.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rlrg.dataserver.badge.dto.AchievementDTO;
import com.rlrg.dataserver.badge.dto.BadgeDTO;
import com.rlrg.dataserver.badge.entity.Achievement;
import com.rlrg.dataserver.badge.entity.Badge;
import com.rlrg.dataserver.badge.entity.enums.BadgeStatus;
import com.rlrg.dataserver.badge.helper.BadgeCriteriaHelper;
import com.rlrg.dataserver.badge.repository.BadgeRepository;
import com.rlrg.dataserver.base.exception.RepositoryException;
import com.rlrg.dataserver.base.service.BaseService;
import com.rlrg.dataserver.base.service.IAchievementService;
import com.rlrg.dataserver.base.service.IBadgeLanguageService;
import com.rlrg.dataserver.base.service.IBadgeService;
import com.rlrg.dataserver.language.dto.BadgeLangDTO;
import com.rlrg.dataserver.language.entity.BadgeLanguage;
import com.rlrg.dataserver.language.entity.Language;
import com.rlrg.dataserver.utillities.Constants;

@Service
public class BadgeService extends BaseService<Badge, BadgeDTO> implements IBadgeService<Badge, BadgeDTO>{
	
	private static final Logger LOG = LoggerFactory.getLogger(BadgeService.class);

	@Autowired
	private BadgeRepository badgeRepo;
	
	@Autowired
	private IBadgeLanguageService<BadgeLanguage, BadgeLangDTO> badgeLangService;
	
	@Autowired
	private IAchievementService<Achievement, AchievementDTO> achievementService;
	
	@Autowired
	private Language DEFAULT_LANGUAGE;
	
	public Badge findBadgeById(Integer badgeId){
		return badgeRepo.findOne(badgeId);
	}
	
	public List<BadgeDTO> getAllBadges(Integer pageNumber){
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, Constants.PAGE_SIZE);
		//
		return badgeRepo.getAllBadgeDTO(DEFAULT_LANGUAGE.getId(), pageRequest);
	}
	
	public Long countAllBadges(){
		return badgeRepo.countAllBadges();
	}
	
	public BadgeDTO getBadgeById(Integer id){
		return badgeRepo.getBadgeDTOById(id, DEFAULT_LANGUAGE.getId());
	}
	
	public List<BadgeDTO> getBadgesByStatus(BadgeStatus status, Integer pageNumber){
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, Constants.PAGE_SIZE);
		//
		return badgeRepo.getBadgeDTOByStatus(status, DEFAULT_LANGUAGE.getId(), pageRequest);
	}
			
	@Transactional
	public void update(BadgeDTO dto) throws RepositoryException{
		try {
			Badge b = badgeRepo.findOne(dto.getId());
			if(null == b){
				LOG.error("Cannot find entity Badge with BadgeID:{}", dto.getId());
				throw new RepositoryException("Cannot find entity");				
			}
			//
			BadgeLanguage badgeLang = badgeLangService.getBadgeLangByBadgeIdAndLangId(b.getId(), DEFAULT_LANGUAGE.getId());
			if(null == badgeLang){
				LOG.error("Cannot find entity BadgeLanguage with BadgeID:{}", dto.getId());
				throw new RepositoryException("Cannot find entity");		
			}
			badgeLang.setName(dto.getName());
			badgeLang.setDescription(dto.getDescription());
			badgeLangService.save(badgeLang);
			//
			b.setFileName(dto.getFileName());
			b.setStatus(dto.getStatus());
			b.setEligibility(dto.getEligibility());
			badgeRepo.save(b);
			//
		} catch(Exception e){
			LOG.error("<< Error occurs when updating badge with id: " + dto.getId(), e);
			throw new RepositoryException("Error occurs when updating badge.");
		}
	}
	
	@Override
	public List<Badge> getBadgeByEligibility(Long userId, List<String> params) {
		//Specification<Badge> spec = BadgeCriteriaHelper.findAvaiableBadgeByEligibilityAndUserId(userId, params);
		List<Integer> usersAchie = achievementService.getAllBadgeIdByUserId(userId);
		Specification<Badge> spec = BadgeCriteriaHelper.findAvaiableBadgeByEligibilityAndUserId(usersAchie, params);
		return badgeRepo.findAll(spec);
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

	/**
	 * Search a list of badge base on keyword
	 * @param keyword
	 * @param pageNumber
	 * @return
	 */
	public List<BadgeDTO> searchBadgesByKeyword(String keyword, Integer pageNumber){
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, Constants.PAGE_SIZE);
		//
		return badgeRepo.searchBadgesDTOByKeyword(keyword, DEFAULT_LANGUAGE.getId(), pageRequest);
	}
	

	@Override
	public Long countBadgesByKeyword(String keyword) {
		return badgeRepo.countBadgesByKeyword(keyword, DEFAULT_LANGUAGE.getId());
	}
	
	@Override
	public Class<BadgeDTO> getVClass() {
		return BadgeDTO.class;
	}

}
