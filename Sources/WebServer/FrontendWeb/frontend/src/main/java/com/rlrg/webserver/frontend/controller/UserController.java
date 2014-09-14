package com.rlrg.webserver.frontend.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.dataserver.statistic.dto.StatisticDTO;
import com.rlrg.webserver.frontend.service.StatisticService;
import com.rlrg.webserver.frontend.utils.Constants;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	public static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private StatisticService statisticService;
	
	@RequestMapping(value = "/getBasicUserStatistics", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
	@ResponseBody
	public StatisticDTO getUserStatistics(HttpSession session){
		try {
			Object tokenSession = session.getAttribute(Constants.SESSION_TOKEN);
			StatisticDTO dto = statisticService.getBasicUserStatistic(tokenSession.toString());
			return dto;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
	}
}
