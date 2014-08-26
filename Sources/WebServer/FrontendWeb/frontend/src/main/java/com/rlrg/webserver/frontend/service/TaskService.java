package com.rlrg.webserver.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.ConvertException;
import com.rlrg.utillities.service.TaskWebServiceReader;

@Service
public class TaskService {
	
	@Autowired
	private TaskWebServiceReader taskReader;
	
	public ResultList<TaskDTO> getAvailableTasks(String token, Integer days, Integer pageNumber) throws RestClientException, ConvertException{
		return taskReader.getAvailableTasksByTime(token, days, pageNumber);
	}
}
