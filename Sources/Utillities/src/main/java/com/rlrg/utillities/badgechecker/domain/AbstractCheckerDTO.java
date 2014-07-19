package com.rlrg.utillities.badgechecker.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AbstractCheckerDTO {
	protected String action;
	protected Date actionDate;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public List<String> getAllPropeties(){
		List<String> props = new ArrayList<String>(1);
		props.add(action);
		//
		return props;
	}
}
