package com.rlrg.dataserver.task.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rlrg.checker.dto.TaskCheckerDTO;
import com.rlrg.dataserver.base.domain.UserToken;
import com.rlrg.dataserver.base.exception.RepositoryException;
import com.rlrg.dataserver.base.exception.UserTokenException;
import com.rlrg.dataserver.base.service.BaseService;
import com.rlrg.dataserver.base.service.CommonService;
import com.rlrg.dataserver.base.service.ICategoryService;
import com.rlrg.dataserver.base.service.ITaskService;
import com.rlrg.dataserver.base.service.IUserService;
import com.rlrg.dataserver.language.entity.Language;
import com.rlrg.dataserver.profile.dto.UserDTO;
import com.rlrg.dataserver.profile.entity.User;
import com.rlrg.dataserver.task.dto.CategoryDTO;
import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.dataserver.task.entity.Category;
import com.rlrg.dataserver.task.entity.Task;
import com.rlrg.dataserver.task.entity.enums.TaskStatus;
import com.rlrg.dataserver.task.repository.TaskRepository;
import com.rlrg.dataserver.utillities.Constants;
import com.rlrg.dataserver.utillities.Utils;
import com.rlrg.utillities.badgechecker.BadgeCheckerConstants;

@Service
public class TaskService extends BaseService<Task, TaskDTO> implements ITaskService<Task, TaskDTO>{
	
	@Transient
	private static final Logger LOG = LoggerFactory.getLogger(TaskService.class);

	@Autowired
	private Language DEFAULT_LANGUAGE;
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private ICategoryService<Category, CategoryDTO> categoryService;
	
	@Autowired
	private IUserService<User, UserDTO> userService;
	
	@Autowired
	private CommonService commonService;
	
	@Override
	public Task findById(Long id){
		return taskRepo.findOne(id);
	}
	
	@Override
	public Task saveTask(Task task){
		return taskRepo.save(task);
	}
	
	@Override
	public List<Task> saveTasks(List<Task> tasks){
		return taskRepo.save(tasks);
	}
	
	public List<Task> getTasksByStatus(TaskStatus status, Integer day, Integer month){
		if (TaskStatus.PROGRESSING.equals(status)){
			return taskRepo.getTasksByStatusSpecial2(status, day, month);
		} else {
			return taskRepo.getTasksByStatusSpecial1(status, day, month);
		}
	}
	
	public List<Task> getTasksByStatus(TaskStatus status){
		return taskRepo.getTasksByStatus(status);
	}
	
	/**
	 * Create new #Task entity, the method will get #User and #Category from database.
	 * If they are null, #RepositoryException will be thrown
	 * @param dto
	 * @return
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public TaskDTO create(TaskDTO dto, String token) throws Exception{
		try {
			Category c = categoryService.getCategoryByCode(dto.getCategory().getCode());
			UserToken userToken = commonService.getUserToken(token);
			User u = userService.getUserById(userToken.getId());
			if(null == c || null == u){
				LOG.error("Cannot find entity Category with Code:{} And/Or User with Id:{}", dto.getCategory().getCode(), u.getId());
				throw new RepositoryException("Cannot find entity");
			}
			Task t = new Task();
			t.setUser(u);
			t.setCategory(c);
			t.setCompleteTime(dto.getCompleteTime());
			t.setDescription(dto.getDescription());
			//
			Date createDate = new Date();
			t.setCreateTime(createDate);
			t.setDifficultyLevel(dto.getDifficultyLevel());
			t.setName(dto.getName());
			t.setPoint(Constants.DEFAULT_CREATE_TASK_POINTS);
			t.setStartTime(dto.getStartTime());
			t.setStatus(TaskStatus.CREATED);
			//
			Task entity = taskRepo.save(t);
			//
			u.setPoint(u.getPoint() + Constants.DEFAULT_CREATE_TASK_POINTS);
			userService.save(u);
			return convertEntityToDTO(entity);
		} catch(Exception e) {
			LOG.error(e.getMessage(), e);
			throw e;
		}
	}
	
	/**
	 * Update #Task entity, the method will get #Task and #Category from database.
	 * If they are null, #RepositoryException will be thrown
	 * @param dto
	 * @return
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public void update(TaskDTO dto, String token) throws Exception{
		try {
			UserToken userToken = commonService.getUserToken(token);
			//
			Task t = taskRepo.getTaskByIdAndUser(dto.getId(), userToken.getId());
			Category c = categoryService.getCategoryByCode(dto.getCategory().getCode());
			if(null == t || null == c){
				LOG.error("Cannot find entity Task with TaskId:{} and UserId:{}", dto.getId(), dto.getUserId());
				throw new RepositoryException("Cannot find entity");
			}
			//
			t.setCategory(c);
			t.setCompleteTime(dto.getCompleteTime());
			t.setDescription(dto.getDescription());
			t.setDifficultyLevel(dto.getDifficultyLevel());
			t.setName(dto.getName());
			t.setStartTime(dto.getStartTime());
			//
			taskRepo.save(t);
		} catch(Exception e){
			LOG.error(e.getMessage(), e);
			throw e;
		}
	}
	
	/**
	 * Update Task'status bases on taskId and task's user
	 * @param taskId
	 * @param taskStatus
	 * @param userId
	 * @throws Exception 
	 */
	@Override
	public void updateTaskStatus(Long taskId, TaskStatus taskStatus, String token) throws Exception {
		try {
			UserToken userToken = commonService.getUserToken(token);
			User user = userService.getUserById(userToken.getId());
			//
			Task t = taskRepo.getTaskByIdAndUser(taskId, userToken.getId());
			if(null == t){
				LOG.error("Cannot find entity Task with TaskId:{} and UserId:{}", taskId, userToken.getId());
				throw new RepositoryException("Cannot find entity");
			} 
			updateTaskStatus(t, user, taskStatus);
		} catch(Exception e){
			LOG.error(e.getMessage(), e);
			throw e;
		}
	}
	
	private void updateTaskStatus(Task t, User user, TaskStatus taskStatus) throws Exception{
		try {
			String behaviour = null;
			if(TaskStatus.COMPLETED.equals(taskStatus)){
				t.setPoint(t.getPoint() + Constants.DEFAULT_COMPLETED_TASK_POINTS);
				user.setPoint(user.getPoint() + + Constants.DEFAULT_COMPLETED_TASK_POINTS);
				behaviour = BadgeCheckerConstants.CHECKING;
				
			} else if(TaskStatus.NOTCOMPLETED.equals(taskStatus) || TaskStatus.DELETED.equals(taskStatus)){
				t.setPoint(t.getPoint() + Constants.DEFAULT_NOTCOMPLETED_TASK_POINTS);
				user.setPoint(user.getPoint() + + Constants.DEFAULT_NOTCOMPLETED_TASK_POINTS);
				behaviour = BadgeCheckerConstants.CHECKING;
				
			} else if((TaskStatus.PROGRESSING.equals(taskStatus) || TaskStatus.CREATED.equals(taskStatus)) 
					&& TaskStatus.COMPLETED.equals(t.getStatus())){
				t.setPoint(t.getPoint() - Constants.DEFAULT_COMPLETED_TASK_POINTS);
				user.setPoint(user.getPoint() - Constants.DEFAULT_COMPLETED_TASK_POINTS);
				behaviour = BadgeCheckerConstants.UNCHECKING;
				
			} else if((TaskStatus.PROGRESSING.equals(taskStatus) || TaskStatus.CREATED.equals(taskStatus)) 
					&& (TaskStatus.NOTCOMPLETED.equals(t.getStatus()) || TaskStatus.DELETED.equals(t.getStatus()))){
				t.setPoint(t.getPoint() - Constants.DEFAULT_NOTCOMPLETED_TASK_POINTS);
				user.setPoint(user.getPoint() - Constants.DEFAULT_NOTCOMPLETED_TASK_POINTS);
				behaviour = BadgeCheckerConstants.UNCHECKING;
			}
			t.setStatus(taskStatus);
			taskRepo.save(t);
			userService.save(user);
			//	
			TaskCheckerDTO checkerDTO = new TaskCheckerDTO();
			checkerDTO.setAction(taskStatus.name());
			checkerDTO.setCategory(t.getCategory().getTag());
			checkerDTO.setActionDate(new Date());
			//
			submitValueToBadgeChecker(BadgeCheckerConstants.TASK_MODULE, user.getId(), checkerDTO, behaviour);	
		} catch(Exception e){
			LOG.error(e.getMessage(), e);
			throw e;
		}
	}
	

	@Override
	public String updateTaskStatus(String token, Long taskId) throws Exception {
		try {
			UserToken userToken = commonService.getUserToken(token);
			User user = userService.getUserById(userToken.getId());
			//
			Task t = taskRepo.getTaskByIdAndUser(taskId, userToken.getId());
			if(null == t){
				LOG.error("Cannot find entity Task with TaskId:{} and UserId:{}", taskId, userToken.getId());
				throw new RepositoryException("Cannot find entity");
			}
			//
			TaskStatus newStatus = null;
			if(TaskStatus.PROGRESSING.equals(t.getStatus()) || TaskStatus.CREATED.equals(t.getStatus())){
				newStatus = TaskStatus.COMPLETED;
			} else if (TaskStatus.NOTCOMPLETED.equals(t.getStatus()) || TaskStatus.COMPLETED.equals(t.getStatus())){
				newStatus = TaskStatus.PROGRESSING;
			} else {
				newStatus = TaskStatus.NOTCOMPLETED;
			}
			updateTaskStatus(t, user, newStatus);
			return newStatus.toString();
		} catch(Exception e){
			LOG.error(e.getMessage(), e);
			throw e;
		}
	}

	
	/**
	 * Get task by it's id then convert it to #TaskDTO
	 * @param taskId
	 * @return
	 */
	@Override
	public TaskDTO getTaskById(Long taskId){
		return taskRepo.getTaskById(taskId, DEFAULT_LANGUAGE.getId());
	}

	/**
	 * Get list of available Tasks base on token and days
	 * Then convert this list of DTO
	 * @param name
	 * @param userId
	 * @param pageNumber
	 * @return
	 * @throws UserTokenException 
	 */
	@Override
	public List<TaskDTO> getTasksByTimeAndStatuses(String token, Integer days, Integer pageNumber, TaskStatus... taskStatuses) throws UserTokenException {
		UserToken userToken = commonService.getUserToken(token);
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, Constants.PAGE_SIZE);
		//
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(Utils.truncateDate(calendar.getTime()));
		Date start = calendar.getTime();
		calendar.add(Calendar.DATE, days);
		Date end = calendar.getTime();
		//
		return taskRepo.getTasksByTimeAndStatuses(userToken.getId(), start, end, pageRequest, taskStatuses);
	}


	
	/**
	 * Get list of Task bases on task's name, user of this tasks and user's language.
	 * Then convert this list of DTO
	 * @param name
	 * @param userId
	 * @param pageNumber
	 * @return
	 * @throws UserTokenException 
	 */
	@Override
	public List<TaskDTO> getTasksByNameAndUser(String name, String token, Integer pageNumber) throws UserTokenException{
		UserToken userToken = commonService.getUserToken(token);
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, Constants.PAGE_SIZE);
		return taskRepo.getTasksByNameAndUser(name, userToken.getId(), DEFAULT_LANGUAGE.getId(), pageRequest);
	}
	
	/**
	 * Get list of Task base on taks's category and user of this tasks and user's language.
	 * Then convert this list to DTO
	 * @param categoryId
	 * @param userId
	 * @return
	 * @throws UserTokenException 
	 */
	@Override
	public List<TaskDTO> getTaskByCategoryAndUser(String categoryCode, String token, Integer pageNumber) throws UserTokenException{
		UserToken userToken = commonService.getUserToken(token);
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, Constants.PAGE_SIZE);
		return taskRepo.getTasksByCategoryAndUser(categoryCode, userToken.getId(), DEFAULT_LANGUAGE.getId(), pageRequest);
	}	

	@Override
	public Long countTotalCreatedTaskByUserId(Long userId) {
		return taskRepo.countTotalCreatedTaskByUserId(userId);
	}
	
	@Override
	public Long countTaskByStatus(Long userId, TaskStatus taskStatus){
		return taskRepo.countTaskByStatus(userId, taskStatus);
	}

	@Override
	public Long countTotalCompletedTaskByUserIdAndCateTag(Long userId, String cateTag) {
		return taskRepo.countTotalCompletedTaskByUserIdAndCateTag(userId, cateTag);
	}
	

	public Long countTasksByKeyword(String keyword) {
		return taskRepo.countTasksByKeyword(keyword);
	}

	@Override
	public TaskDTO convertEntityToDTO(Task data) {
		TaskDTO dto = new TaskDTO();
		dto.setCategory(categoryService.convertEntityToDTO(data.getCategory()));
		dto.setCompleteTime(data.getCompleteTime());
		dto.setDescription(data.getDescription());
		dto.setDifficultyLevel(data.getDifficultyLevel());
		dto.setId(data.getId());
		dto.setName(data.getName());
		dto.setPoint(data.getPoint());
		dto.setStartTime(data.getStartTime());
		dto.setStatus(data.getStatus());
		//
		return dto;
	}
	
	/**
	 * Search a list of task base on keyword
	 * @param keyword
	 * @param pageNumber
	 * @return
	 */
	public List<TaskDTO> searchTasksByKeyword(String keyword, Integer pageNumber){
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, Constants.PAGE_SIZE);
		//
		return taskRepo.searchTasksDTOByKeyword(keyword, pageRequest);
	}

	@Override
	public Task revertDTOToEntity(TaskDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<TaskDTO> getVClass() {
		return TaskDTO.class;
	}

}