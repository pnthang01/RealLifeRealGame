package com.rlrg.utillities.badgechecker;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;

import com.rlrg.dataserver.base.service.ITaskService;
import com.rlrg.dataserver.base.service.IUserService;

public class ActionObserver implements ActionPerformedListener {
	
	private String moduleName;
	
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
			if(BadgeCheckerConstants.CREATE_TASK.equals(event.getAction())){
				checkBadgesForCreateTaskModule(event.getUserId(), event.getProperties());
			}

		}
	}	
	private void checkBadgesForCreateTaskModule(Long userId, Object[] props){
		String performed = userService.getUserPerformedString(userId);
		JSONObject jsonObject = (JSONObject) JSONValue.parse(performed);
		//
	//new JacksonJsonParser().
	}

}
