package com.rlrg.checker;

import org.springframework.beans.factory.annotation.Autowired;

import com.rlrg.utillities.badgechecker.BadgeCheckerConstants;
import com.rlrg.utillities.badgechecker.domain.AbstractCheckerDTO;
import com.rlrg.utillities.badgechecker.domain.IMainChecker;

public class MainChecker implements IMainChecker{
	
	@Autowired
	private TaskChecker taskChecker;
	
	@Autowired
	private ProfileChecker profileChecker;
	
	
	
	public void mainProcess(String module, Long userId, AbstractCheckerDTO props){
		try {
			if(BadgeCheckerConstants.TASK_MODULE.equals(module)){
				taskChecker.process(userId, props);
			} else if (BadgeCheckerConstants.PROFILE_MODULE.equals(module)){
				profileChecker.process(userId, props);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
