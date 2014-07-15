package com.gamification.rlrg.data.core;

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

	public String getMessage()
	{
		return message;
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
