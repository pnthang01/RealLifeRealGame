package com.rlrg.dataserver.task.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.dataserver.task.entity.Task;
import com.rlrg.dataserver.task.service.TaskService;
import com.rlrg.dataserver.utils.base.controller.BaseController;

@RequestMapping("/task")
@Controller
public class TaskController extends BaseController{
	@Autowired
	private TaskService taskService;
	
	/**
	 * This a action to get a task bases on Task Id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getTask", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String getTask(@RequestParam("taskId") Long taskId, HttpServletRequest request, HttpServletResponse response){
		Task task = taskService.findById(taskId);
		//
		return taskService.encodeTask(task);
	}
}
