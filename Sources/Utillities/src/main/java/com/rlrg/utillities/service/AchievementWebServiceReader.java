package com.rlrg.utillities.service;

import java.util.List;

import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.badge.dto.AchievementDTO;
import com.rlrg.utillities.exception.ConvertException;

public class AchievementWebServiceReader extends BaseWebServiceReader<AchievementDTO> {
	
	private final String MODULE_NAME = "AchievementModule";
	
	private final String GET_ALL_ACHIEVEMENTS_BY_USER_NAME_URL = "achievement/getAllAchivementsByUserName?username={username}&pageNumber={pageNumber}";
	private final String GET_ALL_ACHIEVEMENTS_BY_BADGE_ID_URL = "achievement/getAllAchivementsByBadgeId?badgeId={badgeId}";
	private final String ADD_AN_ACHIEVEMENT_URL = "achievement/add?restobject={restobject}";
	
	public List<AchievementDTO> getAllAchievementsByUserName(String username, Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_ACHIEVEMENTS_BY_USER_NAME_URL, MODULE_NAME, username, pageNumber);
	}
	
	public List<AchievementDTO> getAllAchievementsByBadgeId(Integer badgeId) throws RestClientException, ConvertException{
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
