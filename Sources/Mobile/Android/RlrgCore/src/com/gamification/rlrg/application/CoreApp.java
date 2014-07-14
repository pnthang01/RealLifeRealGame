package com.gamification.rlrg.application;

import java.security.MessageDigest;

import com.gamification.rlrg.core.utils.Utils;
import com.gamification.rlrg.data.Achievements;
import com.gamification.rlrg.data.Badges;
import com.gamification.rlrg.data.Categories;
import com.gamification.rlrg.data.Tasks;
import com.gamification.rlrg.data.Users;
import com.google.gson.Gson;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class CoreApp extends Application
{
	public static boolean isStart = true;
	
	private Gson mGson = new Gson();
	
	private Users users;
	private Categories categories;
	private Tasks tasks;
	private Achievements achievements;
	private Badges badges;
	
	private static CoreApp sInstance;
	private static SharedPreferences sSharedPreferences;
	
	public static CoreApp getInstance()
	{
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
		users = (Users) fromJson(DataPreferences.loadJsonUsers(), Users.class);
		badges = (Badges) fromJson(DataPreferences.loadJsonBadges(), Badges.class);
		categories = (Categories) fromJson(DataPreferences.loadJsonCategories(), Categories.class);
		tasks = (Tasks) fromJson(DataPreferences.loadJsonTasks(), Tasks.class);
		achievements = (Achievements) fromJson(DataPreferences.loadJsonAchievements(), Achievements.class);
	}
	
	private Object fromJson(String json, Class<?> clazz)
	{
		return mGson.fromJson(json, clazz);
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

	/**
	 * Get Hash key of keystore are using.
	 * 
	 * @param packageId
	 * @return Hash key
	 */
	public String getKeystoreHashkey(String packageId)
	{
		try
		{
			PackageInfo info = getPackageManager().getPackageInfo(packageId, PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures)
			{
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				return Base64.encodeToString(md.digest(), Base64.DEFAULT);
			}
		}
		catch (Exception e)
		{
			Utils.logException(e);
		}
		return "";
	}

    public static int getScreenWidth()
    {
        DisplayMetrics displayMetrics = getCurrentDisplayMetrics();
        return Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public static int getScreenHeight()
    {
        DisplayMetrics displayMetrics = getCurrentDisplayMetrics();
        return Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public static float getScreenDensity()
    {
        return getCurrentDisplayMetrics().density;
    }

    private static DisplayMetrics getCurrentDisplayMetrics()
    {
        WindowManager sWindowManager = (WindowManager) getInstance().getSystemService(
                Context.WINDOW_SERVICE);
        Display display = sWindowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static SharedPreferences getSharedPreferences(String fileName)
    {
        if (sSharedPreferences == null)
        {
            sSharedPreferences = getInstance().getSharedPreferences(fileName,
                    MODE_PRIVATE);
        }
        return sSharedPreferences;
    }
}
