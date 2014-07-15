package com.rlrg.webserver.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.badge.dto.AchievementDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.service.AchievementWebServiceReader;

@Service
public class MvcAchievementService {
	
	@Autowired
	private AchievementWebServiceReader achievementReader;

	public ResultList<AchievementDTO> getAllAchievementsByUserName(String username, Integer pageNumber) throws RestClientException, ConvertException{
		return achievementReader.getAllAchievementsByUserName(username, pageNumber);
	}
	
	public ResultList<AchievementDTO> getAllAchievementsByBadgeId(Integer badgeId) throws RestClientException, ConvertException{
		return achievementReader.getAllAchievementsByBadgeId(badgeId);
	}
	
	public boolean addAnAchievement(AchievementDTO dto) throws RestClientException, ConvertException{
		return achievementReader.addAnAchievement(dto);
	}
	
	public ResultList<AchievementDTO>  searchAchievementsByKeyword(String keyword, Integer pageNumber) throws RestClientException, ConvertException{
		return null;
		//return achievementReader.searchAchievementsByKeyword(keyword, pageNumber);
	}
}
