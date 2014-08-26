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

import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.webserver.frontend.service.TaskService;
import com.rlrg.webserver.frontend.utils.Constants;

@Controller
@RequestMapping(value = "/task")
public class TaskController {
	private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	private TaskService taskService;
	
	@RequestMapping(value = "/countCompletedTasks", method=RequestMethod.GET)
	@ResponseBody
	public Integer countCompletedTasks(){
		try {
			return 400;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/getAvailableTasks", method=RequestMethod.GET)
	@ResponseBody
	public ResultList<TaskDTO> getAvailableTasks(@CookieValue(Constants.PROFILE_TOKEN) String token, @RequestParam("pageNumber") Integer pageNumber){
		try {
			ResultList<TaskDTO> list = taskService.getAvailableTasks(token, 1, pageNumber);
			return list;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
	}
} 
