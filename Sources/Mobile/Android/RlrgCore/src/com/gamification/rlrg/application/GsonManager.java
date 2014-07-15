package com.gamification.rlrg.application;

import com.google.gson.Gson;

public class GsonManager
{
    private static GsonManager sInstance;
    private Gson mGson;
    
    public static GsonManager getInstance()
    {
        if (sInstance == null)
        {
            synchronized(GsonManager.class)
            {
                if (sInstance == null)
                {
                    sInstance = new GsonManager();
                }
            }            
        }

        return sInstance;
    }
    
    private GsonManager()
    {
        mGson = new Gson();
    }
    
    public Object fromJson(String json, Class<?> clazz)
    {
        return mGson.fromJson(json, clazz);
    }

    public String toJson(Object data, Class<?> clazz)
    {
        return mGson.toJson(data, clazz);
    }
}
