package com.rlrg.webserver.frontend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.dataserver.badge.dto.AchievementDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.webserver.frontend.service.AchievementService;
import com.rlrg.webserver.frontend.utils.Constants;

@Controller
@RequestMapping(value = "/achievement")
public class AchievementController {

	public static final Logger LOG = LoggerFactory.getLogger(AchievementController.class);
	
	@Autowired
	private AchievementService achievementService;

	@RequestMapping(value = "/getAchievements", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
	@ResponseBody
	public ResultList<AchievementDTO> getAchievements(@CookieValue(Constants.PROFILE_TOKEN) String token, @RequestParam("pageNumber") Integer pageNumber){
		try {
			ResultList<AchievementDTO> list = achievementService.getAchievements(token, pageNumber);
			return list;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
	}
}
