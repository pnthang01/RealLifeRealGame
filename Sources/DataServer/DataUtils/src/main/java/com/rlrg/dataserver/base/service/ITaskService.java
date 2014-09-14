package com.rlrg.dataserver.base.service;

import java.util.List;

import com.rlrg.dataserver.base.exception.UserTokenException;
import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.dataserver.task.entity.enums.TaskStatus;

public interface ITaskService<T, V> extends IBaseService<T, V> {
	public T findById(Long id);

	public T saveTask(T task);
	
	public List<T> saveTasks(List<T> tasks);
	
	public V create(V dto, String token) throws Exception;
	
	public void update(V dto, String token) throws Exception;
	
	public void updateTaskStatus(Long taskId, TaskStatus taskStatus, String token) throws Exception;
	
	public String updateTaskStatus(String token, Long taskId) throws Exception;
	
	public TaskDTO getTaskById(Long taskId);
	
	public List<T> getTasksByStatus(TaskStatus status);
	
	public List<T> getTasksByStatus(TaskStatus status, Integer day, Integer month);
	
	public List<V> getTasksByNameAndUser(String name, String token, Integer pageNumber) throws UserTokenException;
	
	public List<V> getTasksByRangeTimeAndStatues(String token, Integer start, Integer end, Integer pageNumber, TaskStatus...taskStatuses) throws UserTokenException;
	
	public List<V> getTaskByCategoryAndUser(String categoryCode, String token, Integer pageNumber) throws UserTokenException;
	
	public List<V> searchTasksByKeyword(String keyword, Integer pageNumber);
	
	public Long countTasksByKeyword(String keyword);
	
	public Long countTaskByStatus(Long userId, TaskStatus taskStatus);
	
	public Long countTotalCreatedTaskByUserId(Long userId);
	
	public Long countTotalCompletedTaskByUserIdAndCateTag(Long userId, String cateTag);
}
