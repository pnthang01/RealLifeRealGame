package com.rlrg.utillities.badgechecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.rlrg.utillities.badgechecker.domain.ActionPerformedEvent;
import com.rlrg.utillities.badgechecker.domain.IMainChecker;

@Component
@Scope("prototype")
public class ActionObserver implements ActionPerformedListener {
	
	private String moduleName;

	private IMainChecker mainChecker;

	@Autowired 
	private AutowireCapableBeanFactory factory; 
	
	public ActionObserver(){
	}
	
	public ActionObserver(BaseSource source) {
		source.addPerformedListener(this);
		this.mainChecker = this.factory.getBean("mainChecker", IMainChecker.class);
	}
	
	public void setBaseSource(BaseSource source){
		source.addPerformedListener(this);
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void actionPerformed(ActionPerformedEvent event) {
		this.mainChecker.mainProcess(moduleName, event.getUserId(), event.getProperties());
	}	

}
