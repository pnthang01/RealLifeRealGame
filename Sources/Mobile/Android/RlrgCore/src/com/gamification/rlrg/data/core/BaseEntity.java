package com.gamification.rlrg.data.core;

import com.google.gson.annotations.SerializedName;

public class BaseEntity<Data>
{
    @SerializedName("ErrorCode")
    private String error = "";

    @SerializedName("Msg")
    private String message = "";

    @SerializedName("data")
    private Data data;

    public boolean isSuccessful()
    {
        return error.equals("0");
    }

    public String getMessage()
    {
        return message;
    }

    public Data getData()
    {
        return data;
    }

    public void setData(Data data)
    {
        this.data = data;
    }
}
