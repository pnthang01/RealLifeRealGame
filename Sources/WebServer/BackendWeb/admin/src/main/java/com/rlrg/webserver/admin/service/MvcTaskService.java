package com.rlrg.webserver.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.service.TaskWebServiceReader;

@Service
public class MvcTaskService {
	
	@Autowired
	private TaskWebServiceReader taskReader;

	public ResultList<TaskDTO> getTasksByStatus(boolean status, Integer pageNumber) throws RestClientException, ConvertException{
		return taskReader.getTasksByStatus(status, pageNumber);
	}
	
	public ResultList<TaskDTO> getAllTasks(Integer pageNumber) throws RestClientException, ConvertException{
		return taskReader.getAllTasks(pageNumber);
	}
	
	public TaskDTO getTaskById(Long id) throws RestClientException, ConvertException{
		return taskReader.getTaskById(id);
	}
	
	public ResultList<TaskDTO> getTasksByUserId(Long userId) throws RestClientException, ConvertException{
		return taskReader.getTasksByUserId(userId);
	}
	
	public ResultList<TaskDTO> getTasksByLevel(Integer difficultyLevel) throws RestClientException, ConvertException{
		return taskReader.getTasksByLevel(difficultyLevel);
	}
	
	public ResultList<TaskDTO> getTasksByPoint(Integer point) throws RestClientException, ConvertException{
		return taskReader.getTasksByPoint(point);
	}
	
	public ResultList<TaskDTO> searchTasksByKeyword(String keyword, Integer pageNumber) throws RestClientException, ConvertException{
		return taskReader.searchTasksByKeyword(keyword, pageNumber);
	}
	
	public boolean updateTask(TaskDTO dto) throws RestClientException, ConvertException{
		return taskReader.updateTask(dto);
	}
}
