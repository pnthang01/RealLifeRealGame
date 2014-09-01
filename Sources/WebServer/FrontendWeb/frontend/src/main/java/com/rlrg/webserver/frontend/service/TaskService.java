package com.rlrg.webserver.frontend.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.task.dto.CategoryDTO;
import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.dataserver.task.entity.enums.DifficultyLevel;
import com.rlrg.dataserver.utillities.Utils;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.service.TaskWebServiceReader;
import com.rlrg.webserver.frontend.form.TaskForm;

@Service
public class TaskService {
	
	@Autowired
	private TaskWebServiceReader taskReader;
	
	public ResultList<TaskDTO> getAvailableTasks(String token, Integer days, Integer pageNumber) throws RestClientException, ConvertException{
		return taskReader.getAvailableTasksByTime(token, days, pageNumber);
	}
	
	public TaskDTO createNewTask(String token, TaskForm taskForm) throws ConvertException, ParseException{
		TaskDTO dto = new TaskDTO();
		dto.setCategory(new CategoryDTO(taskForm.getCategory()));
		dto.setName(taskForm.getName());
		Date completeTime = Utils.convertDateBaseOnI18N(taskForm.getCompleteTime(), "vi");
		dto.setCompleteTime(completeTime);
		dto.setDifficultyLevel(DifficultyLevel.NORMAL);
		//
		return taskReader.createTask(dto, token);
	}
}
