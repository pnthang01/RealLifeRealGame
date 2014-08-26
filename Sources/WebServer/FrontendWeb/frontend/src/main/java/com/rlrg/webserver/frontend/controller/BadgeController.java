package com.rlrg.webserver.frontend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.webserver.frontend.domain.AchievementDisplay;

@Controller
@RequestMapping(value = "/badge")
public class BadgeController {
	public static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/getAchievements", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
	@ResponseBody
	public List<AchievementDisplay> getUserProfile(Model model){
		try {
			List<AchievementDisplay> list = new ArrayList<AchievementDisplay>();
			long temp = new Date().getTime() % 10 + 1;
			for(int i = 0; i < temp ; i++){
				AchievementDisplay achievement = new AchievementDisplay();
				achievement.setAchievedTime(new Date());
				achievement.setBadgeId(new Random(10).nextInt());
				achievement.setBadgeImage("image.jpg");
				achievement.setId(new Random(1000000).nextLong());
				list.add(achievement);
			}
			return list;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
	}
}
