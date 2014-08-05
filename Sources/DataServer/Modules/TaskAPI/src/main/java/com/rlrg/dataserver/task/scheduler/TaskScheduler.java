package com.rlrg.dataserver.task.scheduler;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import com.rlrg.dataserver.base.service.ITaskService;
import com.rlrg.dataserver.task.dto.TaskDTO;
import com.rlrg.dataserver.task.entity.Task;
import com.rlrg.dataserver.task.entity.enums.TaskStatus;
import com.rlrg.dataserver.task.service.TaskService;

public class TaskScheduler {
	private static final Logger LOG = LoggerFactory.getLogger(TaskService.class);
	
	@Autowired
	private ITaskService<Task, TaskDTO> taskService;

	@Scheduled(cron="0 0 6 * * ?")
	@Transactional(rollbackFor=Exception.class)
	public void processCreatedTasksToProgressing() throws Exception{
		try {
			Calendar calendar = Calendar.getInstance();
	        int currentMonth = calendar.get(Calendar.MONTH) + 1;
	        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
			List<Task> tasks = taskService.getTasksByStatus(TaskStatus.CREATED, currentDay, currentMonth);
			//
			for(Task t : tasks){
				t.setStatus(TaskStatus.PROGRESSING);
			} 
			taskService.saveTasks(tasks);
		} catch(Exception e){
			LOG.error("Error occurs when running processCreatedTasksToProgressing:", e);
			throw e;
		}
	}
	
	@Scheduled(cron="0 59 23 * * ?")
	@Transactional(rollbackFor=Exception.class)
	public void processProgressingTasksToNotCompleted() throws Exception{
		try {
			Calendar calendar = Calendar.getInstance();
	        int currentMonth = calendar.get(Calendar.MONTH) + 1;
	        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
			List<Task> tasks = taskService.getTasksByStatus(TaskStatus.PROGRESSING, currentDay, currentMonth);
			//
			for(Task t : tasks){
				t.setStatus(TaskStatus.NOTCOMPLETED);
			} 
			taskService.saveTasks(tasks);
		} catch(Exception e){
			LOG.error("Error occurs when running processProgressingTasksToNotCompleted:", e);
			throw e;
		}
	}
}
