package com.gamification.rlrg.application;

import android.content.Context;
import android.content.SharedPreferences;

import com.gamification.rlrg.gen.R;

public class DataPreferences
{
	public static final String JSON_USERS = "JSON_USERS";
	public static final String JSON_CATEGORIES = "JSON_CATEGORIES";
	public static final String JSON_BADGES = "JSON_BADGES";
	public static final String JSON_TASKS = "JSON_TASKS";
	public static final String JSON_ACHIEVEMENTS = "JSON_ACHIEVEMENTS";
	
	private static DataPreferences sInstance;

    private Context mContext;
    private SharedPreferences mSharedPreferences;

    public static DataPreferences getInstance()
    {
        if (sInstance == null)
        {
            synchronized(DataPreferences.class)
            {
                if (sInstance == null)
                {
                    sInstance = new DataPreferences();
                }
            }            
        }
        return sInstance;
    }
	
	private DataPreferences()
	{
	    mContext = CoreApp.getInstance();
	}
	
	public SharedPreferences getSharedPreferences(String fileName)
    {
        if (mSharedPreferences == null)
        {
            mSharedPreferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        }
        return mSharedPreferences;
    }

    private final SharedPreferences getStorage()
    {
        return getSharedPreferences(mContext.getResources().getString(R.string.app_name) + ".txt");
    }
    
    public void saveJsonUsers(String json)
    {
    	getStorage().edit().putString(JSON_USERS, json).commit();
    }
    
    public void saveJsonBadges(String json)
    {
    	getStorage().edit().putString(JSON_BADGES, json).commit();
    }
    
    public void saveJsonCategories(String json)
    {
    	getStorage().edit().putString(JSON_CATEGORIES, json).commit();
    }
    
    public void saveJsonTasks(String json)
    {
    	getStorage().edit().putString(JSON_TASKS, json).commit();
    }
    
    public void saveJsonAchievements(String json)
    {
    	getStorage().edit().putString(JSON_ACHIEVEMENTS, json).commit();
    }
    
    public String loadJsonUsers()
    {
    	return getStorage().getString(JSON_USERS, AssestsManager.getData(Defination.Assets.Data.USERS));
    }
    
    public String loadJsonBadges()
    {
    	return getStorage().getString(JSON_BADGES, AssestsManager.getData(Defination.Assets.Data.BADGES));
    }
    
    public String loadJsonCategories()
    {
    	return getStorage().getString(JSON_CATEGORIES, AssestsManager.getData(Defination.Assets.Data.CATEGORIES));
    }
    
    public String loadJsonTasks()
    {
    	return getStorage().getString(JSON_TASKS, AssestsManager.getData(Defination.Assets.Data.TASKS));
    }
    
    public String loadJsonAchievements()
    {
    	return getStorage().getString(JSON_ACHIEVEMENTS, AssestsManager.getData(Defination.Assets.Data.ACHIEVEMENTS));
    }
}
