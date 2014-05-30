package com.rlrg.dataserver.task.service;

import java.util.List;

import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.dataserver.task.entity.Task;
import com.rlrg.dataserver.task.repository.TaskRepository;
import com.rlrg.utillities.json.JsonExporter;

@Service
public class TaskService {
	
	@Transient
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private CategoryService cateService;
	
	@Autowired
	private JsonExporter jsonExporter;
	
	public Task findById(Long id){
		return taskRepo.findOne(id);
	}
	
	public Task saveTask(Task task){
		return taskRepo.save(task);
	}
	
	public void deleteTask(Long taskId){
		taskRepo.delete(taskId);
	}
	
	public List<Task> getTasksByNameAndUser(String name, Long userId){
		return taskRepo.getTasksByNameAndUser(name, userId);
	}
	
	public List<Task> getTaskByCategoryAndUser(Integer categoryId, Long userId){
		return taskRepo.getTasksByCategoryAndUser(categoryId, userId);
	}	
	
	public TaskDTO convertTaskToDTO(Task task){
		TaskDTO dto = new TaskDTO();
		dto.setCategory(cateService.convertCategoryToDTO(task.getCategory()));
		dto.setCompleteTime(task.getCompleteTime());
		dto.setDescription(task.getDescription());
		dto.setDifficultyLevel(task.getDifficultyLevel());
		dto.setId(task.getId());
		dto.setName(task.getName());
		dto.setPoint(task.getPoint());
		dto.setStartTime(task.getStartTime());
		dto.setStatus(task.getStatus());
		//
		return dto;
	}
	
	public String encodeTask(Task task){
		TaskDTO dto = this.convertTaskToDTO(task);
		return jsonExporter.encodeObjectToJson(dto, dto.getClass());
	}
}