package com.rlrg.checker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.rlrg.utillities.badgechecker.BadgeCheckerConstants;
import com.rlrg.utillities.badgechecker.domain.AbstractCheckerDTO;
import com.rlrg.utillities.badgechecker.domain.IMainChecker;

public class MainChecker implements IMainChecker{
	
	@Autowired
	private TaskChecker taskChecker;
	
	@Bean(name="taskChecker")
	public TaskChecker getTaskChecker(){
		return new TaskChecker();
	}
	
	public void mainProcess(String module, Long userId, AbstractCheckerDTO props){
		if(BadgeCheckerConstants.TASK_MODULE.equals(module)){
			taskChecker.process(userId, props);
		}
	}
}
