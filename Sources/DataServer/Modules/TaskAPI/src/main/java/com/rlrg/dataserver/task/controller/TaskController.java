package com.rlrg.dataserver.task.controller;

import java.util.List;

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

	@RequestMapping(value = "/updateTask", produces = "application/json; charset=utf-8", method=RequestMethod.POST)
	@ResponseBody
	public String updateTask(@RequestParam(value="restobject", required=true) String json, 
							@RequestParam(value="token", required=true) String token){
		String result = null;
		LOG.info("<< Starting webservice /task/updateTask with parameters: restobject={}", json);
		try {
			TaskDTO dto = taskService.decodeSingleObject(json);
			taskService.update(dto, token);
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
	
	@RequestMapping(value = "/createTask", produces = "application/json; charset=utf-8", method=RequestMethod.POST)
	@ResponseBody
	public String createTask(@RequestParam(value="restobject", required=true) String json,
						@RequestParam(value="token", required=true) String token){
		LOG.info("<< Starting webservice /task/createTask with parameters: restobject={}", json);
		String result = null;
		try{
			TaskDTO dto = taskService.decodeSingleObject(json);
			TaskDTO createdDTO = taskService.create(dto, token);
			result = taskService.encodeSingleObjectFromVdto(createdDTO);
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
	
	@RequestMapping(value = "/updateTaskStatusByStatus", produces = "application/json; charset=utf-8", method=RequestMethod.POST)
	@ResponseBody
	public String updateTaskStatusByStatus(@RequestParam(value="taskId", required=true) Long taskId, @RequestParam(value="status", required=true) TaskStatus taskStatus,
			@RequestParam("token") String token){
		LOG.info("<< Starting webservice /task/updateTaskStatusByStatus with parameters: taskID={}, status={}, token{}", taskId, taskStatus, token);
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
		LOG.info("<< End webservice /task/updateTaskStatusByStatus");
		return result;
	}
	
	@RequestMapping(value = "/updateTaskStatus", produces = "application/json; charset=utf-8", method=RequestMethod.POST)
	@ResponseBody
	public String updateTaskStatus(@RequestParam(value="taskId", required=true) Long taskId,
			@RequestParam("token") String token){
		LOG.info("<< Starting webservice /task/updateTaskStatus with parameters: taskID={}, token{}", taskId, token);
		String result = null;
		try{
			String newStatus = taskService.updateTaskStatus(token, taskId);
			result = taskService.encodeRestObject(newStatus);
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
	@RequestMapping(value = "/getTask", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
	@ResponseBody
	public String getTask(@RequestParam("taskId") Long taskId){
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
	 * Get All tasks bases on range time and all tasks are available to complete
	 * @param categoryId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getAvailableTasksByTime", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
	@ResponseBody
	public String getAvailableTasksByTime(@RequestParam("token") String token, 
			@RequestParam("days") Integer days,@RequestParam(value="pageNumber", required=false) Integer pageNumber){
		String result = null;
		LOG.info("<< Starting webservice /task/getAvailableTasksByTime with parameters: " +
				"token={}, days={}", token, days);
		try{
			List<TaskDTO> tasks = taskService.getTasksByTimeAndStatuses(token, days, pageNumber, 
					TaskStatus.CREATED, TaskStatus.PROGRESSING, TaskStatus.COMPLETED, TaskStatus.NOTCOMPLETED);
			Long total = 10l;//taskService.countTasksByKeyword(keyword); //TODO
			//
			result = taskService.encodeMutipleObjectsFromListV(tasks, total);
		} catch (BaseException e) {
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = taskService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = taskService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /task/getAvailableTasksByTime");
		return result;
	}
	
	/**
	 * Get All tasks bases on categoryID and UserId
	 * @param categoryId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getTasksByCategoryAndUser", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
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
	@RequestMapping(value = "/getTasksByNameAndUser", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
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
	
	/**
	 * Search all tasks which related to keyword
	 * @param keyword
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/searchTasks", produces = "application/json; charset=utf-8", method=RequestMethod.GET)
	@ResponseBody
	public String searchTasksByKeyword(@RequestParam(value="keyword", required=true) String keyword, 
			@RequestParam(value="pageNumber", required=false) Integer pageNumber){
		String result = null;
		LOG.info("<< Starting webservice /task/searchTasks with parameters: keyword={}, pageNumber={}", keyword, pageNumber);
		try {
			List<TaskDTO> listDTO = taskService.searchTasksByKeyword(keyword, pageNumber);
			Long total = taskService.countTasksByKeyword(keyword);
			//
			result = taskService.encodeMutipleObjectsFromListV(listDTO, total);
		} catch(BaseException e){
			RestObject restObject = RestObject.failBank(e.getTechnicalMsg());
			result = taskService.encodeBlankRestObject(restObject);
		} catch(Exception e){
			RestObject restObject = RestObject.failBank(e.getMessage());
			result = taskService.encodeBlankRestObject(restObject);
		}
		LOG.info("<< End webservice /task/searchTasks");
		return result;
	}
}
