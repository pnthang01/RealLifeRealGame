package com.rlrg.webserver.frontend.domain;

public class UserProfile {
	private String firstName;
	private Integer completedTask;

	public Integer getCompletedTask() {
		return completedTask;
	}

	public void setCompletedTask(Integer completedTask) {
		this.completedTask = completedTask;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
