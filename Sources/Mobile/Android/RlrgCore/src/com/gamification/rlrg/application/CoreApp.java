package com.gamification.rlrg.application;

import android.app.Application;
import android.content.res.Configuration;

import com.gamification.rlrg.data.Achievements;
import com.gamification.rlrg.data.Badges;
import com.gamification.rlrg.data.Categories;
import com.gamification.rlrg.data.Tasks;
import com.gamification.rlrg.data.Users;

public class CoreApp extends Application
{
	public static boolean isStart = true;

	private Users users;
	private Categories categories;
	private Tasks tasks;
	private Achievements achievements;
	private Badges badges;

	private static CoreApp sInstance;
    
    public static CoreApp getInstance()
    {
        if (sInstance == null)
        {
            synchronized(CoreApp.class)
            {
                if (sInstance == null)
                {
                    sInstance = new CoreApp();
                }
            }            
        }
        return sInstance;
    }

	@Override
	public void onCreate()
	{
		super.onCreate();
		sInstance = this;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		sInstance = this;
	}

	public void init()
	{
	    GsonManager gsonManager = GsonManager.getInstance();
	    DataPreferences preferences = DataPreferences.getInstance();
	    
		users = (Users) gsonManager.fromJson(preferences.loadJsonUsers(), Users.class);
		badges = (Badges) gsonManager.fromJson(preferences.loadJsonBadges(), Badges.class);
		categories = (Categories) gsonManager.fromJson(preferences.loadJsonCategories(), Categories.class);
		tasks = (Tasks) gsonManager.fromJson(preferences.loadJsonTasks(), Tasks.class);
		achievements = (Achievements) gsonManager.fromJson(preferences.loadJsonAchievements(), Achievements.class);
	}

	public Users getUsers()
	{
		return users;
	}

	public void setUsers(Users users)
	{
		this.users = users;
	}

	public Categories getCategories()
	{
		return categories;
	}

	public void setCategories(Categories categories)
	{
		this.categories = categories;
	}

	public Tasks getTasks()
	{
		return tasks;
	}

	public void setTasks(Tasks tasks)
	{
		this.tasks = tasks;
	}

	public Achievements getAchievements()
	{
		return achievements;
	}

	public void setAchievements(Achievements achievements)
	{
		this.achievements = achievements;
	}

	public Badges getBadges()
	{
		return badges;
	}

	public void setBadges(Badges badges)
	{
		this.badges = badges;
	}
}
