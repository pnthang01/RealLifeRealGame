package com.rlrg.utillities.badgechecker;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;

import com.rlrg.dataserver.base.service.ITaskService;
import com.rlrg.dataserver.base.service.IUserService;

public class ActionObserver implements ActionPerformedListener {
	
	private String moduleName;
	
	@Autowired
	private IBadgeChecker badgeChecker;
	
	@Autowired
	private ITaskService<?, ?> taskService;
	
	@Autowired
	private IUserService<?, ?> userService;

	public ActionObserver(BaseSource source) {
		source.addPerformedListener(this);
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void actionPerformed(ActionPerformedEvent event) {
		if(BadgeCheckerConstants.TASK_MODULE.equals(moduleName)){
			checkBadgesForCreateTaskModule(event.getAction(), event.getUserId(), event.getProperties());		
		}
	}	
	private void checkBadgesForCreateTaskModule(String action, Long userId, Object[] props){
		badgeChecker.process(action, userId, props);
	}

}
