package com.rlrg.utillities.badgechecker.domain;

import java.util.Date;

public abstract class AbstractCheckerDTO {
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

}
