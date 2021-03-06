package com.gamification.rlrg.data.entity;

import com.google.gson.annotations.SerializedName;

public class Badge
{
	@SerializedName("ID")
	private String id = "";

	@SerializedName("Name")
	private String name = "";

	@SerializedName("Description")
	private String description = "";

	@SerializedName("Status")
	private String status = "";

	@SerializedName("Eligibility")
	private String eligibility = "";

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getEligibility()
	{
		return eligibility;
	}

	public void setEligibility(String eligibility)
	{
		this.eligibility = eligibility;
	}
}
