package com.rlrg.webserver.admin.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rlrg.dataserver.statistic.dto.StatisticDTO;
import com.rlrg.dataserver.utillities.Constants;
import com.rlrg.webserver.admin.service.MvcStatisticService;

@Controller
public class CommonController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	private MvcStatisticService statisticService;
	
	@RequestMapping("/login.html")
	public String loginForm(){
		return "login";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "redirect:/dashboard.html";
	}
	
	@RequestMapping("/dashboard.html")
	public String dashboard(ModelMap model){
		try {
			StatisticDTO data = statisticService.getLoginStatistic(Constants.TOTAL_STATISTIC_ROWS);
			model.addAttribute("statisticDTO", data);
			//
			return "dashboard";
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping("/upload")
	public String upload(){
		return null;
	}

}