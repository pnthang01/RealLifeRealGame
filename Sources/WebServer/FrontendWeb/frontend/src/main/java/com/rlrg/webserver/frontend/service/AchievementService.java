package com.rlrg.webserver.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.badge.dto.AchievementDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.service.AchievementWebServiceReader;

@Service
public class AchievementService {

	@Autowired
	private AchievementWebServiceReader achievementReader;
	
	public ResultList<AchievementDTO> getAchievements(String token, Integer pageNumber) throws RestClientException, ConvertException{
		return achievementReader.getAchievements(token, pageNumber);
	}
}
