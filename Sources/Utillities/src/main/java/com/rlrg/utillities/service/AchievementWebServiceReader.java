package com.rlrg.utillities.service;

import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.badge.dto.AchievementDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;

public class AchievementWebServiceReader extends BaseWebServiceReader<AchievementDTO> {
	
	private final String MODULE_NAME = "AchievementModule";
	
	private final String GET_ACHIEVEMENTS_URL = "achievement/getAchievements?token={token}&pageNumber={pageNumber}";
	private final String GET_ALL_ACHIEVEMENTS_BY_USER_NAME_URL = "achievement/getAllAchivementsByUserName?token={token}&pageNumber={pageNumber}";
	private final String GET_ALL_ACHIEVEMENTS_BY_BADGE_ID_URL = "achievement/getAllAchivementsByBadgeId?badgeId={badgeId}";
	private final String ADD_AN_ACHIEVEMENT_URL = "achievement/add?restobject={restobject}";
	
	public ResultList<AchievementDTO> getAchievements(String token, Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ACHIEVEMENTS_URL, MODULE_NAME, token, pageNumber);
	}
	
	public ResultList<AchievementDTO> getAllAchievementsByUserName(String username, Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_ACHIEVEMENTS_BY_USER_NAME_URL, MODULE_NAME, username, pageNumber);
	}
	
	public ResultList<AchievementDTO> getAllAchievementsByBadgeId(Integer badgeId) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_ACHIEVEMENTS_BY_BADGE_ID_URL, MODULE_NAME, badgeId);
	}
	
	public boolean addAnAchievement(AchievementDTO dto) throws RestClientException, ConvertException{
		return this.postAnObjectT(ADD_AN_ACHIEVEMENT_URL, MODULE_NAME, dto);
	}

	@Override
	protected Class<AchievementDTO> getTClass() {
		return AchievementDTO.class;
	}

}
