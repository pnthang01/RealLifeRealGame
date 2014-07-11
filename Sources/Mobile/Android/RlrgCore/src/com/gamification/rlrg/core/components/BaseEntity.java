package com.gamification.rlrg.core.components;

import com.google.gson.annotations.SerializedName;

public class BaseEntity<T>
{
	@SerializedName("ErrorCode")
	private String error;
	
	@SerializedName("Msg")
	private String message;
	
	@SerializedName("data")
	private T data;

	public boolean isSuccessful()
	{
		return error.equals("0");
	}

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

	public T getData()
	{
		return data;
	}

	public void setData(T data)
	{
		this.data = data;
	}
}
