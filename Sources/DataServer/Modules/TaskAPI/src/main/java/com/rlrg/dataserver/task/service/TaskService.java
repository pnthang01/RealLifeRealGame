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
import com.rlrg.dataserver.utils.base.service.BaseService;
import com.rlrg.utillities.json.JsonExporter;

@Service
public class TaskService extends BaseService<Task, TaskDTO>{
	
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
	
	public List<Task> getTasksByNameAndUser(String name, Long userId){
		return taskRepo.getTasksByNameAndUser(name, userId);
	}
	
	public List<Task> getTaskByCategoryAndUser(Integer categoryId, Long userId){
		return taskRepo.getTasksByCategoryAndUser(categoryId, userId);
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