package com.rlrg.dataserver.base.service;

import java.util.List;
import com.rlrg.dataserver.base.exception.UserTokenException;
import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.dataserver.task.entity.enums.TaskStatus;

public interface ITaskService<T, V> extends IBaseService<T, V> {
	public T findById(Long id);

	public T saveTask(T task);
	
	public void create(V dto) throws Exception;
	
	public void update(V dto) throws Exception;
	
	public void updateTaskStatus(Long taskId, TaskStatus taskStatus, String token) throws Exception;
	
	public TaskDTO getTaskById(Long taskId);
	
	public List<TaskDTO> getTasksByNameAndUser(String name, String token, Integer pageNumber) throws UserTokenException;
	
	public List<TaskDTO> getTaskByCategoryAndUser(String categoryCode, String token, Integer pageNumber) throws UserTokenException;
	
	public List<V> searchTasksByKeyword(String keyword, Integer pageNumber);
	
	public Long countTasksByKeyword(String keyword);
	
	public Long countTotalCreatedTaskByUserId(Long userId);
	
	public Long countTotalCompletedTaskByUserIdAndCateTag(Long userId, String cateTag);
}
