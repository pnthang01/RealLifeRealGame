package com.rlrg.webserver.frontend.service;

import java.io.UnsupportedEncodingException;
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
import com.rlrg.utillities.exception.BaseException;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.service.TaskWebServiceReader;
import com.rlrg.webserver.frontend.form.TaskForm;

@Service
public class TaskService {
	
	@Autowired
	private TaskWebServiceReader taskReader;
	
	public ResultList<TaskDTO> getTasksByRangeTime(String token, Integer start, Integer end, Integer pageNumber) throws RestClientException, ConvertException{
		return taskReader.getTasksByRangeTime(token, start, end, pageNumber);
	}
	
	public TaskDTO createNewTask(String token, TaskForm taskForm) throws ParseException, UnsupportedEncodingException, BaseException{
		TaskDTO dto = new TaskDTO();
		dto.setCategory(new CategoryDTO(taskForm.getCategory()));
		dto.setName(taskForm.getName());
		Date completeTime = Utils.convertDateBaseOnI18N(taskForm.getCompleteTime(), "vi");
		dto.setCompleteTime(completeTime);
		dto.setDifficultyLevel(DifficultyLevel.NORMAL);
		//
		return taskReader.createTask(dto, token);
	}
	
	public String updateTaskStatus(String token, Long taskId) throws RestClientException, ConvertException{
		return taskReader.updateTaskStatus(token, taskId);
	}
}
