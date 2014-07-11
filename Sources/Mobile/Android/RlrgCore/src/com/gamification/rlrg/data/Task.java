package com.gamification.rlrg.data;

import com.google.gson.annotations.SerializedName;

public class Task
{
	@SerializedName("ID")
	private String id;

	@SerializedName("Category")
	private Category category;

	@SerializedName("Name")
	private String name;
	
	@SerializedName("Complete Time")
	private long completeTime;
	
	@SerializedName("Difficulty Level")
	private String difficultyLevel;	
	
	@SerializedName("Status")
	private String status;
	
	@SerializedName("Point")
	private String point;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public Category getCategory()
	{
		return category;
	}

	public void setCategory(Category category)
	{
		this.category = category;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public long getCompleteTime()
	{
		return completeTime;
	}

	public void setCompleteTime(long completeTime)
	{
		this.completeTime = completeTime;
	}

	public String getDifficultyLevel()
	{
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel)
	{
		this.difficultyLevel = difficultyLevel;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getPoint()
	{
		return point;
	}

	public void setPoint(String point)
	{
		this.point = point;
	}
}
