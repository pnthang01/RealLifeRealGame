package com.rlrg.webserver.frontend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.webserver.frontend.service.TaskService;
import com.rlrg.webserver.frontend.utils.Constants;

@Controller
public class HomeController {
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private TaskService taskService;
	
	@RequestMapping("/home")
	public String home(@CookieValue(Constants.PROFILE_TOKEN) String token, 
			@RequestParam(value="pageNumber",defaultValue="1",required=false) Integer pageNumber, ModelMap model){
		try {
			if(null == token || token.isEmpty()){
				return "redirect:/welcome"; 
			}
			ResultList<TaskDTO> list = taskService.getAvailableTasks(token, Constants.DEFAULT_DAYS_TO_GET_TASK, pageNumber);
			model.addAttribute("data", list);
			return "home";
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
	}
}
