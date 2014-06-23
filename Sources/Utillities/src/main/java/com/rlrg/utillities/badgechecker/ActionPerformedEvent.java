package com.rlrg.utillities.badgechecker;

public class ActionPerformedEvent {
	private String action;
	private Long userId;

	public ActionPerformedEvent(String action, Long userId) {
		this.action = action;
		this.userId = userId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
