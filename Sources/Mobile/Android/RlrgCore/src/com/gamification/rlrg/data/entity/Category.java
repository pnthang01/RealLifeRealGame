package com.gamification.rlrg.data.entity;

import com.google.gson.annotations.SerializedName;

public class Category
{
	@SerializedName("Code")
	private String code = "";

	@SerializedName("Name")
	private String name = "";

	@SerializedName("Description")
	private String description = "";

	@SerializedName("Position")
	private String position = "";

	private String status = "";

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getPosition()
	{
		return position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}
}
