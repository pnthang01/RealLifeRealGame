package com.rlrg.webserver.frontend.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.webserver.frontend.form.TaskForm;
import com.rlrg.webserver.frontend.service.TaskService;
import com.rlrg.webserver.frontend.utils.Constants;

@Controller
@RequestMapping(value = "/task")
public class TaskController {
	private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	private TaskService taskService;

	@RequestMapping(value = "/getAvailableTasks", method=RequestMethod.GET)
	@ResponseBody
	public ResultList<TaskDTO> getAvailableTasks(HttpSession session, @RequestParam("pageNumber") Integer pageNumber){
		try {
			Object tokenSession = session.getAttribute(Constants.SESSION_TOKEN);
			ResultList<TaskDTO> list = taskService.getTasksByRangeTime(tokenSession.toString(), 0, Constants.DEFAULT_DAYS_TO_GET_TASK, pageNumber);
			return list;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/createNewTask", method=RequestMethod.POST)
	@ResponseBody
	public TaskDTO createNewTask(HttpSession session, @Valid TaskForm taskForm, BindingResult bResult){
		try {
			if (bResult.hasErrors()) {
				return null;
			} else {
				Object tokenSession = session.getAttribute(Constants.SESSION_TOKEN);
				TaskDTO data = taskService.createNewTask(tokenSession.toString(), taskForm);
				return data;
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value = "/updateTaskStatus", method=RequestMethod.POST)
	@ResponseBody
	public String updateTaskStatus(HttpSession session, @RequestParam("id") Long taskId){
		try {
			Object tokenSession = session.getAttribute(Constants.SESSION_TOKEN);
			String newStatus = taskService.updateTaskStatus(tokenSession.toString(), taskId);
			return newStatus;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
	}
} 
