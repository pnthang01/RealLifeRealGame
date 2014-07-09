package com.gamification.rlrg.data;

import com.google.gson.annotations.SerializedName;

public class Achievement
{
	@SerializedName("ID")
	private String id;
	
	@SerializedName("Badge")
	private Badge badge;
	
	@SerializedName("AchievedTime")
	private long achievedTime;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Badge getBadge()
	{
		return badge;
	}

	public void setBadge(Badge badge)
	{
		this.badge = badge;
	}

	public long getAchievedTime()
	{
		return achievedTime;
	}

	public void setAchievedTime(long achievedTime)
	{
		this.achievedTime = achievedTime;
	}
}
