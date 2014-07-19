package com.rlrg.dataserver.task.service;

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
import com.rlrg.utillities.badgechecker.BadgeCheckerConstants;
import com.rlrg.utillities.badgechecker.domain.ModuleName;

@ModuleName(name=BadgeCheckerConstants.TASK_MODULE)
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

	
	/**
	 * Create new #Task entity, the method will get #User and #Category from database.
	 * If they are null, #RepositoryException will be thrown
	 * @param dto
	 * @return
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public void create(TaskDTO dto) throws Exception{
		try {
			Category c = categoryService.getCategoryByCode(dto.getCategory().getCode());
			User u = userService.getUserById(dto.getUserId());
			if(null == c || null == u){
				LOG.error("Cannot find entity Category with Code:{} And/Or User with Id:{}", dto.getCategory().getCode(), dto.getUserId());
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
			t.setPoint(dto.getPoint());
			t.setStartTime(dto.getStartTime());
			t.setStatus(dto.getStatus());
			//
			taskRepo.save(t);
			//
			TaskCheckerDTO checkerDTO = new TaskCheckerDTO();
			checkerDTO.setAction(BadgeCheckerConstants.CREATE_TASK);
			checkerDTO.setCategory(c.getTag());
			checkerDTO.setDiffLevel(t.getDifficultyLevel().name());
			checkerDTO.setActionDate(createDate);
			//
			submitValueToBadgeChecker(BadgeCheckerConstants.TASK_MODULE, u.getId(), checkerDTO);
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
	public void update(TaskDTO dto) throws Exception{
		try {
			Task t = taskRepo.getTaskByIdAndUser(dto.getId(), dto.getUserId());
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
			t.setPoint(dto.getPoint());
			t.setStartTime(dto.getStartTime());
			t.setStatus(dto.getStatus());
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
			//
			Task t = taskRepo.getTaskByIdAndUser(taskId, userToken.getId());
			if(null == t){
				LOG.error("Cannot find entity Task with TaskId:{} and UserId:{}", taskId, userToken.getId());
				throw new RepositoryException("Cannot find entity");
			} 
			t.setStatus(taskStatus);
			taskRepo.save(t);
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
		//notifyListeners("test",1l); //TODO
		return taskRepo.getTaskById(taskId, DEFAULT_LANGUAGE.getId());
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

	@Override
	public Task revertDTOToEntity(TaskDTO dto) {
		// TODO Auto-generated method stub
		return null;
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
	

	public Long countTasksByKeyword(String keyword) {
		return taskRepo.countTasksByKeyword(keyword);
	}

	@Override
	public Class<TaskDTO> getVClass() {
		return TaskDTO.class;
	}
}