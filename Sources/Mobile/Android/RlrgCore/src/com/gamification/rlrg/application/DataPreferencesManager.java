package com.gamification.rlrg.application;

import nghiem.app.core.application.AssestsManager;
import nghiem.app.core.utils.LogUtils;
import android.content.Context;
import android.content.SharedPreferences;

import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.settings.Settings;

public class DataPreferencesManager
{
	public static final String TAG = DataPreferencesManager.class.getName();

	public static final String PREFERENCE_UUID = "UUID";
	public static final String PREFERENCE_USER = "USER";
	public static final String PREFERENCE_PASS = "PASS";
	public static final String PREFERENCE_REMEMBER_PASS = "REMEMBER_PASS";

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
			synchronized (DataPreferencesManager.class)
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
		LogUtils.log(TAG, "saveUuid: " + uuid);
		getStorage().edit().putString(PREFERENCE_UUID, uuid).commit();
	}

	/**
	 * Get User username
	 */
	public final String getUsername()
	{
		return getStorage().getString(PREFERENCE_USER, "");
	}

	/**
	 * Save the Device ID
	 */
	public void saveUsername(String username)
	{
		LogUtils.log(TAG, "saveUsername: " + username);
		getStorage().edit().putString(PREFERENCE_USER, username).commit();
	}

	/**
	 * Get User password
	 */
	public final String getPassword()
	{
		return getStorage().getString(PREFERENCE_PASS, "");
	}

	/**
	 * Save the Device ID
	 */
	public void savePassword(String password)
	{
		LogUtils.log(TAG, "savePassword: " + password);
		getStorage().edit().putString(PREFERENCE_PASS, password).commit();
	}

	/**
	 * Is remember user password?
	 */
	public final boolean getRememberPassword()
	{
		return getStorage().getBoolean(PREFERENCE_REMEMBER_PASS, false);
	}

	/**
	 * Save remember user password
	 */
	public void saveRememberPassword(boolean isRemember)
	{
		LogUtils.log(TAG, "saveRememberPassword: " + isRemember);
		getStorage().edit().putBoolean(PREFERENCE_REMEMBER_PASS, isRemember).commit();
	}

	public void saveJsonUsers(String json)
	{
		LogUtils.log(TAG, "saveJsonUsers: " + json);
		getStorage().edit().putString(JSON_USERS, json).commit();
	}

	public void saveJsonBadges(String json)
	{
		LogUtils.log(TAG, "saveJsonBadges: " + json);
		getStorage().edit().putString(JSON_BADGES, json).commit();
	}

	public void saveJsonCategories(String json)
	{
		LogUtils.log(TAG, "saveJsonCategories: " + json);
		getStorage().edit().putString(JSON_CATEGORIES, json).commit();
	}

	public void saveJsonTasks(String json)
	{
		LogUtils.log(TAG, "saveJsonTasks: " + json);
		getStorage().edit().putString(JSON_TASKS, json).commit();
	}

	public void saveJsonAchievements(String json)
	{
		LogUtils.log(TAG, "saveJsonAchievements: " + json);
		getStorage().edit().putString(JSON_ACHIEVEMENTS, json).commit();
	}

	public String loadJsonUsers()
	{
		String json = getStorage().getString(JSON_USERS, AssestsManager.getInstance().getData(Settings.Assets.Data.USERS));
		LogUtils.log(TAG, "loadJsonUsers: " + json);
		return json;
	}

	public String loadJsonBadges()
	{
		String json = getStorage().getString(JSON_BADGES, AssestsManager.getInstance().getData(Settings.Assets.Data.BADGES));
		LogUtils.log(TAG, "loadJsonBadges: " + json);
		return json;
	}

	public String loadJsonCategories()
	{
		String json = getStorage().getString(JSON_CATEGORIES, AssestsManager.getInstance().getData(Settings.Assets.Data.CATEGORIES));
		LogUtils.log(TAG, "loadJsonCategories: " + json);
		return json;
	}

	public String loadJsonTasks()
	{
		String json = getStorage().getString(JSON_TASKS, AssestsManager.getInstance().getData(Settings.Assets.Data.TASKS));
		LogUtils.log(TAG, "loadJsonTasks: " + json);
		return json;
	}

	public String loadJsonAchievements()
	{
		String json = getStorage().getString(JSON_ACHIEVEMENTS, AssestsManager.getInstance().getData(Settings.Assets.Data.ACHIEVEMENTS));
		LogUtils.log(TAG, "loadJsonAchievements: " + json);
		return json;
	}
}
