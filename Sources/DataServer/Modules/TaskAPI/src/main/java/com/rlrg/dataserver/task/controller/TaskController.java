package com.rlrg.dataserver.task.controller;

import java.util.List;

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
		return taskService.encodeSingleObjectFromTdata(task);
	}
	
	/**
	 * Get All tasks bases on categoryID and UserId
	 * @param categoryId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getTasksByCategoryAndUser", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String getTasksByCategoryAndUser(@RequestParam("categoryId") Integer categoryId, @RequestParam("userId") Long userId){
		List<Task> tasks = taskService.getTaskByCategoryAndUser(categoryId, userId);
		//
		return taskService.encodeMutipleObjectsFromListT(tasks);
	}
	
	/**
	 * Get All tasks bases on their name and userID
	 * @param name
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getTasksByNameAndUser", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String getTasksByNameAndUser(@RequestParam("name") String name, @RequestParam("userId") Long userId){
		List<Task> tasks = taskService.getTasksByNameAndUser(name, userId);
		//
		return taskService.encodeMutipleObjectsFromListT(tasks);
	}
}
