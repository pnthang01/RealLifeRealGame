package com.gamification.rlrg.application;

import android.content.SharedPreferences;

import com.gamification.rlrg.assests.Assests;
import com.gamification.rlrg.assests.AssestsHelper;
import com.gamification.rlrg.gen.R;

public class DataPreferences
{
	private static final String PREFERENCE_FILE = CoreApp.getInstance().getResources().getString(R.string.app_name) + ".txt";

	public static final String JSON_USERS = "JSON_USERS";
	public static final String JSON_CATEGORIES = "JSON_CATEGORIES";
	public static final String JSON_BADGES = "JSON_BADGES";
	public static final String JSON_TASKS = "JSON_TASKS";
	public static final String JSON_ACHIEVEMENTS = "JSON_ACHIEVEMENTS";

    private static final SharedPreferences getStorage()
    {
        return CoreApp.getSharedPreferences(PREFERENCE_FILE);
    }
    
    public static void saveJsonUsers(String json)
    {
    	getStorage().edit().putString(JSON_USERS, json).commit();
    }
    
    public static void saveJsonBadges(String json)
    {
    	getStorage().edit().putString(JSON_BADGES, json).commit();
    }
    
    public static void saveJsonCategories(String json)
    {
    	getStorage().edit().putString(JSON_CATEGORIES, json).commit();
    }
    
    public static void saveJsonTasks(String json)
    {
    	getStorage().edit().putString(JSON_TASKS, json).commit();
    }
    
    public static void saveJsonAchievements(String json)
    {
    	getStorage().edit().putString(JSON_ACHIEVEMENTS, json).commit();
    }
    
    public static String loadJsonUsers()
    {
    	return getStorage().getString(JSON_USERS, AssestsHelper.getData(Assests.Data.USERS));
    }
    
    public static String loadJsonBadges()
    {
    	return getStorage().getString(JSON_BADGES, AssestsHelper.getData(Assests.Data.BADGES));
    }
    
    public static String loadJsonCategories()
    {
    	return getStorage().getString(JSON_CATEGORIES, AssestsHelper.getData(Assests.Data.CATEGORIES));
    }
    
    public static String loadJsonTasks()
    {
    	return getStorage().getString(JSON_TASKS, AssestsHelper.getData(Assests.Data.TASKS));
    }
    
    public static String loadJsonAchievements()
    {
    	return getStorage().getString(JSON_ACHIEVEMENTS, AssestsHelper.getData(Assests.Data.ACHIEVEMENTS));
    }
}
