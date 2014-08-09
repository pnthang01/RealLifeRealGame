package com.gamification.rlrg.application;

import org.joda.time.DateTime;

import lvnghiem.app.core.application.AssestsManager;
import lvnghiem.app.core.utils.Logger;
import android.content.Context;
import android.content.SharedPreferences;

import com.gamification.rlrg.gen.R;

public class DataPreferencesManager
{
	private static final Logger LOG = new Logger(DataPreferencesManager.class);
	private static int sMaxPoint = 0;

	public static final String PREFERENCE_USER_POINT = "USER_POINT";
	public static final String PREFERENCE_LAST_LOGIN = "LAST_LOGIN";
	public static final String PREFERENCE_CONTINUOUS_LOGIN = "CONTINUOUS_LOGIN";
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
		if (sMaxPoint == 0)
		{
			sMaxPoint = RlrgApp.getInstance().getResources().getInteger(R.integer.settings_rules_point_max);
		}
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
	 * Get last login
	 */
	public final long getLastLogin()
	{
		long last = getStorage().getLong(PREFERENCE_LAST_LOGIN, DateTime.now().getMillis());
		LOG.debug("getLastLogin: " + last);
		return last;
	}

	/**
	 * Save last login
	 */
	public void saveLastLogin(long last)
	{
		LOG.debug("saveLastLogin: " + last);
		getStorage().edit().putLong(PREFERENCE_LAST_LOGIN, last).commit();
	}

	/**
	 * Get continuous login count
	 */
	public final int getContinuousLogin()
	{
		int count = getStorage().getInt(PREFERENCE_CONTINUOUS_LOGIN, 0);
		LOG.debug("getContinuousLogin: " + count);
		return count;
	}

	/**
	 * Save continuous login count
	 */
	public void saveContinuousLogin(int count)
	{
		LOG.debug("saveContinuousLogin: " + count);
		getStorage().edit().putInt(PREFERENCE_CONTINUOUS_LOGIN, count).commit();
	}

	/**
	 * Increase continuous login count
	 */
	public void increaseContinuousLogin()
	{
		saveContinuousLogin(getContinuousLogin() + 1);
	}

	/**
	 * Get user point
	 */
	public final int getUserPoint()
	{
		int point = getStorage().getInt(PREFERENCE_USER_POINT, 0);
		LOG.debug("getUserPoint: " + point);
		return point;
	}

	/**
	 * Save user point
	 */
	public void saveUserPoint(int point)
	{
		int maxPoint = sMaxPoint;
		if (point < -maxPoint)
		{
			point = -maxPoint;
		}
		if (point > maxPoint)
		{
			point = maxPoint;
		}
		LOG.debug("saveUserPoint: " + point);
		getStorage().edit().putLong(PREFERENCE_USER_POINT, point).commit();
	}

	/**
	 * Add user point
	 */
	public int addUserPoint(int point)
	{
		int newPoint = getUserPoint() + point;
		saveUserPoint(newPoint);
		return newPoint;
	}

	public void saveJsonUsers(String json)
	{
		LOG.debug("saveJsonUsers: " + json);
		getStorage().edit().putString(JSON_USERS, json).commit();
	}

	public String loadJsonUsers()
	{
		String json = getStorage().getString(JSON_USERS, AssestsManager.getInstance().getData(RlrgApp.getInstance().getString(R.string.settings_path_users)));
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
		String json = getStorage().getString(JSON_BADGES, AssestsManager.getInstance().getData(RlrgApp.getInstance().getString(R.string.settings_path_badges)));
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
		String json = getStorage().getString(JSON_CATEGORIES, AssestsManager.getInstance().getData(RlrgApp.getInstance().getString(R.string.settings_path_categories)));
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
		String json = getStorage().getString(JSON_TASKS, AssestsManager.getInstance().getData(RlrgApp.getInstance().getString(R.string.settings_path_tasks)));
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
		String json = getStorage().getString(JSON_ACHIEVEMENTS, AssestsManager.getInstance().getData(RlrgApp.getInstance().getString(R.string.settings_path_achievements)));
		LOG.debug("loadJsonAchievements: " + json);
		return json;
	}
}
