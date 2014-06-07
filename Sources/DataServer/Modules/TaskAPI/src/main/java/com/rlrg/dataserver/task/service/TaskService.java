package com.rlrg.dataserver.task.service;

import java.util.List;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rlrg.dataserver.language.entity.Language;
import com.rlrg.dataserver.profile.entity.User;
import com.rlrg.dataserver.profile.service.UserService;
import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.dataserver.task.entity.Category;
import com.rlrg.dataserver.task.entity.Task;
import com.rlrg.dataserver.task.entity.enums.TaskStatus;
import com.rlrg.dataserver.task.repository.TaskRepository;
import com.rlrg.dataserver.utils.base.controller.WebVariables;
import com.rlrg.dataserver.utils.base.exception.RepositoryException;
import com.rlrg.dataserver.utils.base.service.BaseService;
import com.rlrg.utillities.json.JsonExporter;

@Service
public class TaskService extends BaseService<Task, TaskDTO>{
	
	@Transient
	private static final Logger LOG = LoggerFactory.getLogger(TaskService.class);

	@Autowired
	private Language DEFAULT_LANGUAGE;
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private CategoryService cateService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JsonExporter jsonExporter;
	
	public Task findById(Long id){
		return taskRepo.findOne(id);
	}
	
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
	@Transactional
	public void create(TaskDTO dto) throws Exception{
		try {
			Category c = cateService.getCategoryByCode(dto.getCategory().getCode());
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
			t.setDifficultyLevel(dto.getDifficultyLevel());
			t.setName(dto.getName());
			t.setPoint(dto.getPoint());
			t.setStartTime(dto.getStartTime());
			t.setStatus(dto.getStatus());
			//
			taskRepo.save(t);
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
	@Transactional
	public void update(TaskDTO dto) throws Exception{
		try {
			Task t = taskRepo.getTaskByIdAndUser(dto.getId(), dto.getUserId());
			Category c = cateService.getCategoryByCode(dto.getCategory().getCode());
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
	public void updateTaskStatus(Long taskId, TaskStatus taskStatus, Long userId) throws Exception{
		try {
			Task t = taskRepo.getTaskByIdAndUser(taskId, userId);
			if(null == t){
				LOG.error("Cannot find entity Task with TaskId:{} and UserId:{}", taskId, userId);
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
	public TaskDTO getTaskById(Long taskId){
		return taskRepo.getTaskById(taskId, DEFAULT_LANGUAGE.getId());
	}
	
	/**
	 * Get list of Task bases on task's name, user of this tasks and user's language.
	 * Then convert this list of DTO
	 * @param name
	 * @param userId
	 * @param pageNumber
	 * @return
	 */
	public List<TaskDTO> getTasksByNameAndUser(String name, Long userId, Integer pageNumber){
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, WebVariables.PAGE_SIZE);
		return taskRepo.getTasksByNameAndUser(name, userId, DEFAULT_LANGUAGE.getId(), pageRequest);
	}
	
	/**
	 * Get list of Task base on taks's category and user of this tasks and user's language.
	 * Then convert this list to DTO
	 * @param categoryId
	 * @param userId
	 * @return
	 */
	public List<TaskDTO> getTaskByCategoryAndUser(Integer categoryId, Long userId, Integer pageNumber){
		if(null == pageNumber){
			pageNumber = 1;
		}
		PageRequest pageRequest = new PageRequest(pageNumber - 1, WebVariables.PAGE_SIZE);
		return taskRepo.getTasksByCategoryAndUser(categoryId, userId, DEFAULT_LANGUAGE.getId(), pageRequest);
	}	


	@Override
	public TaskDTO convertEntityToDTO(Task data) {
		TaskDTO dto = new TaskDTO();
		dto.setCategory(cateService.convertEntityToDTO(data.getCategory()));
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

	@Override
	public Class<TaskDTO> getVClass() {
		return TaskDTO.class;
	}
}