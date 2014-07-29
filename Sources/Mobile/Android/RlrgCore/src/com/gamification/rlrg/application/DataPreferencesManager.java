package com.gamification.rlrg.application;

import nghiem.app.core.application.AssestsManager;
import nghiem.app.core.utils.LogUtils;
import android.content.Context;
import android.content.SharedPreferences;

import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.settings.Settings;

public class DataPreferencesManager
{
	public static final Class<?> CLASS = DataPreferencesManager.class;

	public static final String PREFERENCE_LOGIN_COUNT = "LOGIN_COUNT";
	public static final String PREFERENCE_UUID = "UUID";
	public static final String PREFERENCE_USER = "USER";
	public static final String PREFERENCE_PASS = "PASS";
	public static final String PREFERENCE_REMEMBER_PASS = "REMEMBER_PASS";

	public static final String JSON_USERS = "JSON_USERS";
	public static final String JSON_CATEGORIES = "JSON_CATEGORIES";
	public static final String JSON_BADGES = "JSON_BADGES";
	public static final String JSON_TASKS = "JSON_TASKS";
	public static final String JSON_ACHIEVEMENTS = "JSON_ACHIEVEMENTS";

	@SuppressWarnings("rawtypes")
	private static ThreadLocal sInitHolder = new ThreadLocal();
	private static DataPreferencesManager sInstance;

	private Context mContext;
	private SharedPreferences mSharedPreferences;

	@SuppressWarnings("unchecked")
	public static DataPreferencesManager getInstance()
	{
		if (sInitHolder.get() == null)
		{
			synchronized (DataPreferencesManager.class)
			{
				if (sInstance == null)
				{
					sInstance = new DataPreferencesManager();
				}
				sInitHolder.set(Boolean.TRUE);
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
		String uuid = getStorage().getString(PREFERENCE_UUID, "");
		LogUtils.debug(CLASS, "getUuid: " + uuid);
		return uuid;
	}

	/**
	 * Save the Device ID
	 */
	public void saveUuid(String uuid)
	{
		LogUtils.debug(CLASS, "saveUuid: " + uuid);
		getStorage().edit().putString(PREFERENCE_UUID, uuid).commit();
	}

	/**
	 * Get username
	 */
	public final String getUsername()
	{
		String username = getStorage().getString(PREFERENCE_USER, "");
		LogUtils.debug(CLASS, "getUsername: " + username);
		return username;
	}

	/**
	 * Save username
	 */
	public void saveUsername(String username)
	{
		LogUtils.debug(CLASS, "saveUsername: " + username);
		getStorage().edit().putString(PREFERENCE_USER, username).commit();
	}

	/**
	 * Get password
	 */
	public final String getPassword()
	{
		String password = getStorage().getString(PREFERENCE_PASS, "");
		LogUtils.debug(CLASS, "getPassword: " + password);
		return password;
	}

	/**
	 * Save password
	 */
	public void savePassword(String password)
	{
		LogUtils.debug(CLASS, "savePassword: " + password);
		getStorage().edit().putString(PREFERENCE_PASS, password).commit();
	}

	/**
	 * Is remember user password?
	 */
	public final boolean getRememberPassword()
	{
		boolean remember = getStorage().getBoolean(PREFERENCE_REMEMBER_PASS, false);
		LogUtils.debug(CLASS, "getRememberPassword: " + remember);
		return remember;
	}

	/**
	 * Save remember user password
	 */
	public void saveRememberPassword(boolean isRemember)
	{
		LogUtils.debug(CLASS, "saveRememberPassword: " + isRemember);
		getStorage().edit().putBoolean(PREFERENCE_REMEMBER_PASS, isRemember).commit();
	}

	/**
	 * Get login count
	 */
	public final int getLoginCount()
	{
		int count = getStorage().getInt(PREFERENCE_LOGIN_COUNT, 0);
		LogUtils.debug(CLASS, "getLoginCount: " + count);
		return count;
	}

	/**
	 * Save login count
	 */
	public void increaseLoginCount()
	{
		int count = getLoginCount() + 1;
		LogUtils.debug(CLASS, "saveLoginCount: " + count);
		getStorage().edit().putInt(PREFERENCE_LOGIN_COUNT, count).commit();
	}

	public void saveJsonUsers(String json)
	{
		LogUtils.debug(CLASS, "saveJsonUsers: " + json);
		getStorage().edit().putString(JSON_USERS, json).commit();
	}

	public String loadJsonUsers()
	{
		String json = getStorage().getString(JSON_USERS, AssestsManager.getInstance().getData(Settings.Assets.Data.USERS));
		LogUtils.debug(CLASS, "loadJsonUsers: " + json);
		return json;
	}

	public void saveJsonBadges(String json)
	{
		LogUtils.debug(CLASS, "saveJsonBadges: " + json);
		getStorage().edit().putString(JSON_BADGES, json).commit();
	}

	public String loadJsonBadges()
	{
		String json = getStorage().getString(JSON_BADGES, AssestsManager.getInstance().getData(Settings.Assets.Data.BADGES));
		LogUtils.debug(CLASS, "loadJsonBadges: " + json);
		return json;
	}

	public void saveJsonCategories(String json)
	{
		LogUtils.debug(CLASS, "saveJsonCategories: " + json);
		getStorage().edit().putString(JSON_CATEGORIES, json).commit();
	}

	public String loadJsonCategories()
	{
		String json = getStorage().getString(JSON_CATEGORIES, AssestsManager.getInstance().getData(Settings.Assets.Data.CATEGORIES));
		LogUtils.debug(CLASS, "loadJsonCategories: " + json);
		return json;
	}

	public void saveJsonTasks(String json)
	{
		LogUtils.debug(CLASS, "saveJsonTasks: " + json);
		getStorage().edit().putString(JSON_TASKS, json).commit();
	}

	public String loadJsonTasks()
	{
		String json = getStorage().getString(JSON_TASKS, AssestsManager.getInstance().getData(Settings.Assets.Data.TASKS));
		LogUtils.debug(CLASS, "loadJsonTasks: " + json);
		return json;
	}

	public void saveJsonAchievements(String json)
	{
		LogUtils.debug(CLASS, "saveJsonAchievements: " + json);
		getStorage().edit().putString(JSON_ACHIEVEMENTS, json).commit();
	}

	public String loadJsonAchievements()
	{
		String json = getStorage().getString(JSON_ACHIEVEMENTS, AssestsManager.getInstance().getData(Settings.Assets.Data.ACHIEVEMENTS));
		LogUtils.debug(CLASS, "loadJsonAchievements: " + json);
		return json;
	}
}
