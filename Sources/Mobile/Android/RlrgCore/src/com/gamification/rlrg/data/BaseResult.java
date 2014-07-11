package com.gamification.rlrg.data;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class BaseResult<T>
{
	@SerializedName("ErrorCode")
	private String error;
	
	@SerializedName("Msg")
	private String message;
	
	private List<T> data;

	public String getError()
	{
		return error;
	}

	public void setError(String error)
	{
		this.error = error;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public List<T> getData()
	{
		return data;
	}

	public void setData(List<T> data)
	{
		this.data = data;
	}
}
