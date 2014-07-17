package com.gamification.rlrg.application;

import nghiem.app.core.application.AssestsManager;
import android.content.Context;
import android.content.SharedPreferences;

import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.settings.Settings;

public class DataPreferencesManager
{
    public static final String PREFERENCE_UUID = "uuid";
    
    public static final String JSON_USERS = "JSON_USERS";
	public static final String JSON_CATEGORIES = "JSON_CATEGORIES";
	public static final String JSON_BADGES = "JSON_BADGES";
	public static final String JSON_TASKS = "JSON_TASKS";
	public static final String JSON_ACHIEVEMENTS = "JSON_ACHIEVEMENTS";
	
	private static DataPreferencesManager sInstance;

    private Context mContext;
    private SharedPreferences mSharedPreferences;

    public static DataPreferencesManager getInstance()
    {
        if (sInstance == null)
        {
            synchronized(DataPreferencesManager.class)
            {
                if (sInstance == null)
                {
                    sInstance = new DataPreferencesManager();
                }
            }            
        }
        return sInstance;
    }
	
	private DataPreferencesManager()
	{
	    mContext = RlrgApp.getInstance();
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
    
    /**
     * Get the Device ID
     */
    public final String getUuid()
    {
        return getStorage().getString(PREFERENCE_UUID, "");
    }

    /**
     * Save the Device ID
     */
    public void saveUuid(String uuid)
    {
        getStorage().edit().putString(PREFERENCE_UUID, uuid).commit();
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
    	return getStorage().getString(JSON_USERS, AssestsManager.getData(Settings.Assets.Data.USERS));
    }
    
    public String loadJsonBadges()
    {
    	return getStorage().getString(JSON_BADGES, AssestsManager.getData(Settings.Assets.Data.BADGES));
    }
    
    public String loadJsonCategories()
    {
    	return getStorage().getString(JSON_CATEGORIES, AssestsManager.getData(Settings.Assets.Data.CATEGORIES));
    }
    
    public String loadJsonTasks()
    {
    	return getStorage().getString(JSON_TASKS, AssestsManager.getData(Settings.Assets.Data.TASKS));
    }
    
    public String loadJsonAchievements()
    {
    	return getStorage().getString(JSON_ACHIEVEMENTS, AssestsManager.getData(Settings.Assets.Data.ACHIEVEMENTS));
    }
}
