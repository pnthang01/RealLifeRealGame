package com.rlrg.dataserver.badge.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.badge.dto.AchievementDTO;
import com.rlrg.dataserver.badge.dto.BadgeDTO;
import com.rlrg.dataserver.badge.entity.Achievement;
import com.rlrg.dataserver.badge.entity.Badge;
import com.rlrg.dataserver.badge.repository.AchievementRepository;
import com.rlrg.dataserver.base.exception.RepositoryException;
import com.rlrg.dataserver.base.exception.UserTokenException;
import com.rlrg.dataserver.base.service.BaseService;
import com.rlrg.dataserver.base.service.CommonService;
import com.rlrg.dataserver.base.service.IAchievementService;
import com.rlrg.dataserver.base.service.IBadgeService;
import com.rlrg.dataserver.base.service.IUserService;
import com.rlrg.dataserver.language.entity.Language;
import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.dataserver.profile.entity.User;
import com.rlrg.dataserver.utillities.Constants;

@Service
public class AchievementService extends BaseService<Achievement, AchievementDTO> implements IAchievementService<Achievement, AchievementDTO> {

	private static final Logger LOG = LoggerFactory.getLogger(AchievementService.class);
	
	@Autowired
	private AchievementRepository achievementRepo;

	@Autowired
	private IBadgeService<Badge, BadgeDTO> badgeService;
	
	@Autowired
	private IUserService<User, UserDTO> userService;
	
	@Autowired
	private Language DEFAULT_LANGUAGE;
	
	@Autowired
	private CommonService commonService;
	
	public Long countTimeBadgeBeAchieved(Integer badgeId){
		return achievementRepo.countTimeBadgeBeAchieved(badgeId);
	}
	
	/**
	 * 
	 * @param token
	 * @param pageNumber
	 * @return
	 * @throws UserTokenException 
	 */
	public List<AchievementDTO> getUserAchievementDTOs(String username, Integer pageNumber) throws UserTokenException{
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, Constants.PAGE_SIZE);
		//
		return achievementRepo.getUserAchievementDTOs(username, DEFAULT_LANGUAGE.getId(), pageRequest);
	}
	
	public void addAchievement(AchievementDTO dto) throws Exception{
		try {
			Badge b = badgeService.findBadgeById(dto.getBadge().getId());
			User u = userService.getUserByUsername(dto.getUsername());
			if(null == b || null == u){
				LOG.error("Cannot find entity Badge with ID:{} And/Or User with Id:{}", dto.getBadge().getId(), dto.getUsername());
				throw new RepositoryException("Cannot find entity");
			}
			Achievement a = new Achievement();
			a.setAchievedTime(dto.getAchievedTime());
			a.setBadge(b);
			a.setUser(u);
			//
			achievementRepo.save(a);
		} catch(Exception e) {
			LOG.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public AchievementDTO convertEntityToDTO(Achievement data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Achievement revertDTOToEntity(AchievementDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Search a list of achievement base on keyword
	 * @param keyword
	 * @param pageNumber
	 * @return
	 */
//	public List<AchievementDTO> searchAchievementsByKeyword(String keyword, Integer pageNumber){
//		if(null == pageNumber){
//			pageNumber = 1;
//		}
//		PageRequest pageRequest = new PageRequest(pageNumber - 1, Constants.PAGE_SIZE);
//		//
//		return achievementRepo.searchAchievementsDTOByKeyword(keyword, pageRequest);
//	}
//	
//
//	@Override
//	public Long countAchievementsByKeyword(String keyword) {
//		return achievementRepo.countAchievementsByKeyword(keyword);
//	}

	@Override
	public Class<AchievementDTO> getVClass() {
		return AchievementDTO.class;
	}

}
