package com.rlrg.utillities.badgechecker.domain;

public class ActionPerformedEvent {
	private Long userId;
	private AbstractCheckerDTO properties;

	public ActionPerformedEvent(Long userId, AbstractCheckerDTO checkerDTO) {
		//this.action = action;
		this.userId = userId;
		this.properties = checkerDTO;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public AbstractCheckerDTO getProperties() {
		return properties;
	}

	public void setProperties(AbstractCheckerDTO properties) {
		this.properties = properties;
	}
}
