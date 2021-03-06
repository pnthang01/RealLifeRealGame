package com.rlrg.utillities.service;

import java.io.UnsupportedEncodingException;

import org.springframework.web.client.RestClientException;

import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.utillities.domain.ResultList;
import com.rlrg.utillities.exception.BaseException;
import com.rlrg.utillities.exception.ConvertException;

public class TaskWebServiceReader extends BaseWebServiceReader<TaskDTO> {
	
	private final String MODULE_NAME = "TaskModule";
	
	private final String GET_ALL_TASKS_BY_STATUS_URL = "task/getTasksByStatus?status={status}&pageNumber={pageNumber}";
	private final String GET_ALL_TASKS_URL = "task/getAllTasks?pageNumber={pageNumber}";
	private final String GET_TASK_BY_ID_URL = "task/getTaskByID?id={id}";
	private final String GET_ALL_TASKS_BY_USER_ID_URL = "task/getAllTasksByUserID?userId={userId}";
	private final String GET_ALL_TASKS_BY_LEVEL_URL = "task/getAllTasksByLevel?difficultyLevel={difficultyLevel}";
	private final String GET_ALL_TASKS_BY_POINT_URL = "task/getAllTasksByPoint?point={point}";
	private final String SEARCH_TASKS_BY_KEYWORD_URL = "task/searchTasks?keyword={keyword}&pageNumber={pageNumber}";
	private final String GET_TASKS_BY_RANGE_TIME_URL = "task/getTasksByRangeTime?token={token}&start={start}&end={end}&pageNumber={pageNumber}";
	private final String UPDATE_TASK_URL = "task/update?restobject={restobject}";
	private final String CREATE_TASK_URL = "task/createTask?restobject={restobject}&token={token}";
	private final String UPDATE_TASK_STATUS_URL = "task/updateTaskStatus?token={token}&taskId={taskId}";
	
	public String updateTaskStatus(String token, Long taskId) throws RestClientException, ConvertException{
		return this.postAnObjectForResult(UPDATE_TASK_STATUS_URL, MODULE_NAME, token, taskId).toString();
	}
	
	public ResultList<TaskDTO> getTasksByRangeTime(String token, Integer start, Integer end, Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_TASKS_BY_RANGE_TIME_URL, MODULE_NAME, token, start, end, pageNumber);
	}
	
	public ResultList<TaskDTO> getTasksByStatus(boolean status, Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_TASKS_BY_STATUS_URL, MODULE_NAME, status, pageNumber);
	}
	
	public ResultList<TaskDTO> getAllTasks(Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_TASKS_URL, MODULE_NAME, pageNumber);
	}
	
	public TaskDTO getTaskById(Long id) throws RestClientException, ConvertException{
		return this.getAnObject(GET_TASK_BY_ID_URL, MODULE_NAME, id);
	}
	
	public ResultList<TaskDTO> getTasksByUserId(Long userId) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_TASKS_BY_USER_ID_URL, MODULE_NAME, userId);
	}
	
	public ResultList<TaskDTO> getTasksByLevel(Integer difficultyLevel) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_TASKS_BY_LEVEL_URL, MODULE_NAME, difficultyLevel);
	}
	
	public ResultList<TaskDTO> getTasksByPoint(Integer point) throws RestClientException, ConvertException{
		return this.getListOfObjects(GET_ALL_TASKS_BY_POINT_URL, MODULE_NAME, point);
	}
	
	public ResultList<TaskDTO> searchTasksByKeyword(String keyword, Integer pageNumber) throws RestClientException, ConvertException{
		return this.getListOfObjects(SEARCH_TASKS_BY_KEYWORD_URL, MODULE_NAME, keyword, pageNumber);
	}
	
	public boolean updateTask(TaskDTO dto) throws RestClientException, ConvertException{
		return this.postAnObjectT(UPDATE_TASK_URL, MODULE_NAME, dto);
	}
	
	public TaskDTO createTask(TaskDTO dto, String token) throws UnsupportedEncodingException, BaseException{
		return this.postAnObject(CREATE_TASK_URL, MODULE_NAME, dto, token);
	}

	@Override
	protected Class<TaskDTO> getTClass() {
		return TaskDTO.class;
	}

}
