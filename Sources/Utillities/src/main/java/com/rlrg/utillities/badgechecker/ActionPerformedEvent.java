package com.rlrg.utillities.badgechecker;

public class ActionPerformedEvent {
	private Long userId;
	private Object[] properties;

	public ActionPerformedEvent(Long userId,
			Object... properties) {
		//this.action = action;
		this.userId = userId;
		this.properties = properties;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Object[] getProperties() {
		return properties;
	}

	public void setProperties(Object[] properties) {
		this.properties = properties;
	}

}
