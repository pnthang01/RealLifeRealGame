package com.rlrg.dataserver.badge.dto;

import java.util.Date;

import com.rlrg.utillities.annotation.JsonDTO;
import com.rlrg.utillities.annotation.JsonExport;
import com.rlrg.utillities.annotation.JsonObject;

@JsonDTO(singularName="Achievement", pluralName="Achievements")
public class AchievementDTO {
	@JsonExport(name="ID")
	private Long id;
	
	@JsonObject
	private BadgeDTO badge; 
	
	@JsonExport(name="Username")
	private String username;
	
	@JsonExport(name="AchievedTime")
	private Date achievedTime;
	
	public AchievementDTO(){
	}
	
	public AchievementDTO(Long id, Integer badgeId, String badgeName, String badgeFileName, String badgeDescription, Date achievedTime){
		this.id = id;
		BadgeDTO dto = new BadgeDTO(badgeId, badgeName, badgeFileName, badgeDescription);
		this.badge = dto;
		this.achievedTime = achievedTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BadgeDTO getBadge() {
		return badge;
	}

	public void setBadge(BadgeDTO badge) {
		this.badge = badge;
	}

	public Date getAchievedTime() {
		return achievedTime;
	}

	public void setAchievedTime(Date achievedTime) {
		this.achievedTime = achievedTime;
	}
}
