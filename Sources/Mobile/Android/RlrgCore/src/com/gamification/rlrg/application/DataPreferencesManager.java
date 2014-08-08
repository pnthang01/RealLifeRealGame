package com.gamification.rlrg.application;

import lvnghiem.app.core.application.AssestsManager;
import lvnghiem.app.core.utils.Logger;
import android.content.Context;
import android.content.SharedPreferences;

import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.settings.Settings;

public class DataPreferencesManager
{
	private static final Logger LOG = new Logger(DataPreferencesManager.class);

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
		LOG.debug("getUuid: " + uuid);
		return uuid;
	}

	/**
	 * Save the Device ID
	 */
	public void saveUuid(String uuid)
	{
		LOG.debug("saveUuid: " + uuid);
		getStorage().edit().putString(PREFERENCE_UUID, uuid).commit();
	}

	/**
	 * Get username
	 */
	public final String getUsername()
	{
		String username = getStorage().getString(PREFERENCE_USER, "");
		LOG.debug("getUsername: " + username);
		return username;
	}

	/**
	 * Save username
	 */
	public void saveUsername(String username)
	{
		LOG.debug("saveUsername: " + username);
		getStorage().edit().putString(PREFERENCE_USER, username).commit();
	}

	/**
	 * Get password
	 */
	public final String getPassword()
	{
		String password = getStorage().getString(PREFERENCE_PASS, "");
		LOG.debug("getPassword: " + password);
		return password;
	}

	/**
	 * Save password
	 */
	public void savePassword(String password)
	{
		LOG.debug("savePassword: " + password);
		getStorage().edit().putString(PREFERENCE_PASS, password).commit();
	}

	/**
	 * Is remember user password?
	 */
	public final boolean getRememberPassword()
	{
		boolean remember = getStorage().getBoolean(PREFERENCE_REMEMBER_PASS, false);
		LOG.debug("getRememberPassword: " + remember);
		return remember;
	}

	/**
	 * Save remember user password
	 */
	public void saveRememberPassword(boolean isRemember)
	{
		LOG.debug("saveRememberPassword: " + isRemember);
		getStorage().edit().putBoolean(PREFERENCE_REMEMBER_PASS, isRemember).commit();
	}

	/**
	 * Get login count
	 */
	public final int getLoginCount()
	{
		int count = getStorage().getInt(PREFERENCE_LOGIN_COUNT, 0);
		LOG.debug("getLoginCount: " + count);
		return count;
	}

	/**
	 * Save login count
	 */
	public void increaseLoginCount()
	{
		int count = getLoginCount() + 1;
		LOG.debug("saveLoginCount: " + count);
		getStorage().edit().putInt(PREFERENCE_LOGIN_COUNT, count).commit();
	}

	public void saveJsonUsers(String json)
	{
		LOG.debug("saveJsonUsers: " + json);
		getStorage().edit().putString(JSON_USERS, json).commit();
	}

	public String loadJsonUsers()
	{
		String json = getStorage().getString(JSON_USERS, AssestsManager.getInstance().getData(Settings.Assets.Data.USERS));
		LOG.debug("loadJsonUsers: " + json);
		return json;
	}

	public void saveJsonBadges(String json)
	{
		LOG.debug("saveJsonBadges: " + json);
		getStorage().edit().putString(JSON_BADGES, json).commit();
	}

	public String loadJsonBadges()
	{
		String json = getStorage().getString(JSON_BADGES, AssestsManager.getInstance().getData(Settings.Assets.Data.BADGES));
		LOG.debug("loadJsonBadges: " + json);
		return json;
	}

	public void saveJsonCategories(String json)
	{
		LOG.debug("saveJsonCategories: " + json);
		getStorage().edit().putString(JSON_CATEGORIES, json).commit();
	}

	public String loadJsonCategories()
	{
		String json = getStorage().getString(JSON_CATEGORIES, AssestsManager.getInstance().getData(Settings.Assets.Data.CATEGORIES));
		LOG.debug("loadJsonCategories: " + json);
		return json;
	}

	public void saveJsonTasks(String json)
	{
		LOG.debug("saveJsonTasks: " + json);
		getStorage().edit().putString(JSON_TASKS, json).commit();
	}

	public String loadJsonTasks()
	{
		String json = getStorage().getString(JSON_TASKS, AssestsManager.getInstance().getData(Settings.Assets.Data.TASKS));
		LOG.debug("loadJsonTasks: " + json);
		return json;
	}

	public void saveJsonAchievements(String json)
	{
		LOG.debug("saveJsonAchievements: " + json);
		getStorage().edit().putString(JSON_ACHIEVEMENTS, json).commit();
	}

	public String loadJsonAchievements()
	{
		String json = getStorage().getString(JSON_ACHIEVEMENTS, AssestsManager.getInstance().getData(Settings.Assets.Data.ACHIEVEMENTS));
		LOG.debug("loadJsonAchievements: " + json);
		return json;
	}
}
