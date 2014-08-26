package com.rlrg.webserver.frontend.domain;

import java.util.Date;

public class AchievementDisplay {
	private Long id;
	private Integer badgeId;
	private String badgeImage;
	private Date achievedTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(Integer badgeId) {
		this.badgeId = badgeId;
	}

	public String getBadgeImage() {
		return badgeImage;
	}

	public void setBadgeImage(String badgeImage) {
		this.badgeImage = badgeImage;
	}

	public Date getAchievedTime() {
		return achievedTime;
	}

	public void setAchievedTime(Date achievedTime) {
		this.achievedTime = achievedTime;
	}

}
