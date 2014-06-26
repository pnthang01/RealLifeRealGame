package com.rlrg.dataserver.task.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rlrg.dataserver.base.controller.BaseController;
import com.rlrg.dataserver.base.service.ITaskService;
import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.dataserver.task.entity.Task;
import com.rlrg.dataserver.task.entity.enums.TaskStatus;
import com.rlrg.utillities.domain.RestObject;
import com.rlrg.utillities.exception.BaseException;

@RequestMapping("/task")
@Controller
public class TaskController extends BaseController{
	
	private static final Logger LOG = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	private ITaskService<Task, TaskDTO> taskService;

	@RequestMapping(value = "/updateTask", produces = "application/json", method=RequestMethod.POST)
	@ResponseBody
	public String updateTask(@RequestParam(value="restobject", required=true) String json){
		String result = null;
		LOG.info("<< Starting webservice /task/updateTask with parameters: restobject={}", json);
		try {
			TaskDTO dto = taskService.decodeSingleObject(json);
			taskService.update(dto);
			RestObject restObject = RestObject.successBlank();
			result = taskService.encodeBlankRestObject(restObject);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = taskService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = taskService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /task/updateTask");
		return result;
	}
	
	@RequestMapping(value = "/createTask", produces = "application/json", method=RequestMethod.POST)
	@ResponseBody
	public String createTask(@RequestParam(value="restobject", required=true) String json){
		LOG.info("<< Starting webservice /task/createTask with parameters: restobject={}", json);
		String result = null;
		try{
			TaskDTO dto = taskService.decodeSingleObject(json);
			taskService.create(dto);
			RestObject restObject = RestObject.successBlank();
			result = taskService.encodeBlankRestObject(restObject);
		} catch (BaseException e) {
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = taskService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = taskService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /task/createTask");
		return result;
	}
	
	@RequestMapping(value = "/updateTaskStatus", produces = "application/json", method=RequestMethod.POST)
	@ResponseBody
	public String updateTaskStatus(@RequestParam(value="taskId", required=true) Long taskId, @RequestParam(value="status", required=true) TaskStatus taskStatus,
			@RequestParam("token") String token){
		LOG.info("<< Starting webservice /task/updateTaskStatus with parameters: taskID={}, status={}, token{}", taskId, taskStatus, token);
		String result = null;
		try{
			taskService.updateTaskStatus(taskId, taskStatus, token);
			RestObject restObject = RestObject.successBlank();
			result = taskService.encodeBlankRestObject(restObject);
		} catch (BaseException e) {
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = taskService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = taskService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /task/updateTaskStatus");
		return result;
	}
	
	/**
	 * This a action to get a task bases on Task Id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getTask", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String getTask(@RequestParam("taskId") Long taskId, HttpServletRequest request, HttpServletResponse response){
		String result = null;
		LOG.info("<< Starting webservice /task/getTask with parameters: taskId={}", taskId);
		try{
			TaskDTO task = taskService.getTaskById(taskId);
			//
			result = taskService.encodeSingleObjectFromVdto(task);
		} catch (BaseException e) {
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = taskService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = taskService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /task/getTask");
		return result;
	}
	
	/**
	 * Get All tasks bases on categoryID and UserId
	 * @param categoryId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getTasksByCategoryAndUser", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String getTasksByCategoryAndUser(@RequestParam("categoryCode") String categoryCode, @RequestParam("token") String token,
			@RequestParam("pageNumber") Integer pageNumber){
		String result = null;
		LOG.info("<< Starting webservice /task/getTasksByCategoryAndUser with parameters: " +
				"categoryCode={}, token={}, pageNumber={}", categoryCode, token, pageNumber);
		try{
			List<TaskDTO> tasks = taskService.getTaskByCategoryAndUser(categoryCode, token, pageNumber);
			//
			result = taskService.encodeMutipleObjectsFromListV(tasks);
		} catch (BaseException e) {
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = taskService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = taskService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /task/getTasksByCategoryAndUser");
		return result;
	}
	
	/**
	 * Get All tasks bases on their name and userID
	 * @param name
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getTasksByNameAndUser", produces = "application/json", method=RequestMethod.GET)
	@ResponseBody
	public String getTasksByNameAndUser(@RequestParam("name") String name, @RequestParam("token") String token,
			@RequestParam("pageNumber") Integer pageNumber){
		String result = null;
		LOG.info("<< Starting webservice /task/getTasksByNameAndUser with parameters: " +
				"name={}, token={}, pageNumber={}", name, token, pageNumber);
		try {
			List<TaskDTO> tasks = taskService.getTasksByNameAndUser(name, token, pageNumber);
			//
			result = taskService.encodeMutipleObjectsFromListV(tasks);
		} catch (BaseException e) {
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = taskService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = taskService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /task/getTasksByNameAndUser");
		return result;
	}
}
