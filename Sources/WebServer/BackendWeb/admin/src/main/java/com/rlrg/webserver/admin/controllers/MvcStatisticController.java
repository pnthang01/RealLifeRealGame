package com.rlrg.webserver.admin.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rlrg.dataserver.statistic.dto.StatisticDTO;
import com.rlrg.webserver.admin.service.MvcStatisticService;

@RequestMapping("/statistic")
@Controller
public class MvcStatisticController {
	private static final Logger LOG = LoggerFactory.getLogger(MvcStatisticController.class);
	
	@Autowired
	private MvcStatisticService statisticService;
	
	@RequestMapping(value = "/dahsboard.html", method = RequestMethod.GET)
	public String edit(ModelMap model) {
		try {
			StatisticDTO data = statisticService.getLoginStatistic();
			model.addAttribute("statisticDTO", data);
			//
			return "dashboard";
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return "error";
		}
	}
}
