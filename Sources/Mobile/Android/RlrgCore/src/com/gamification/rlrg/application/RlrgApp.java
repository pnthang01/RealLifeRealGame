package com.gamification.rlrg.application;

import java.util.List;

import nghiem.app.core.application.DeviceManager;
import nghiem.app.core.application.NghiemBaseApp;
import nghiem.app.core.exception.ExceptionHandler;

import org.joda.time.DateTime;

import android.content.res.Configuration;
import android.text.TextUtils;

import com.gamification.rlrg.data.Achievements;
import com.gamification.rlrg.data.Badges;
import com.gamification.rlrg.data.Categories;
import com.gamification.rlrg.data.Tasks;
import com.gamification.rlrg.data.Users;
import com.gamification.rlrg.data.entity.Achievement;
import com.gamification.rlrg.data.entity.Badge;
import com.gamification.rlrg.data.entity.Category;
import com.google.gson.Gson;

public class RlrgApp extends NghiemBaseApp
{
	public static final Class<?> CLASS = RlrgApp.class;

	public static boolean isStart = true;

	@SuppressWarnings("rawtypes")
	private static ThreadLocal sInitHolder = new ThreadLocal();

	private Users users;
	private Categories categories;
	private Tasks tasks;
	private Achievements achievements;
	private Badges badges;

	private static RlrgApp sInstance;

	@SuppressWarnings("unchecked")
	public static RlrgApp getInstance()
	{
		if (sInitHolder.get() == null)
		{
			synchronized (RlrgApp.class)
			{
				if (sInstance == null)
				{
					sInstance = new RlrgApp();
				}
				sInitHolder.set(Boolean.TRUE);
			}
		}
		return sInstance;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		if (sInstance == null)
		{
			sInstance = this;
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		if (sInstance == null)
		{
			sInstance = this;
		}
	}

	public void init()
	{
		initUuid();
		initErrorHandle();

		Gson gson = new Gson();
		DataPreferencesManager preferences = DataPreferencesManager.getInstance();

		users = (Users) gson.fromJson(preferences.loadJsonUsers(), Users.class);
		badges = (Badges) gson.fromJson(preferences.loadJsonBadges(), Badges.class);
		categories = (Categories) gson.fromJson(preferences.loadJsonCategories(), Categories.class);
		tasks = (Tasks) gson.fromJson(preferences.loadJsonTasks(), Tasks.class);
		achievements = (Achievements) gson.fromJson(preferences.loadJsonAchievements(), Achievements.class);
	}

	private void initErrorHandle()
	{
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
	}

	private void initUuid()
	{
		String lastUuid = DataPreferencesManager.getInstance().getUuid();
		if (TextUtils.isEmpty(lastUuid) || lastUuid.startsWith("FAKE"))
		{
			lastUuid = DeviceManager.getInstance().generateUuid();
			// TODO: register UUID with server
		}
	}

	public void checkAchievemnt()
	{
		for (Badge badge : badges.getData().getElements())
		{
			String[] eligibility = badge.getEligibility().split(":");
			for (Category category : categories.getData().getElements())
			{
				if (eligibility[0].equals(category.getName()) && Integer.parseInt(eligibility[1]) == tasks.getTaskCount(category))
				{
					List<Achievement> achievementList = achievements.getData().getElements();
					Achievement achievement = new Achievement();
					achievement.setId(achievementList.get(achievementList.size() - 1).getId());
					achievement.setBadge(badge);
					achievement.setAchievedTime(DateTime.now().getMillis());
					achievementList.add(achievement);
					DataPreferencesManager.getInstance().saveJsonAchievements(new Gson().toJson(achievements, Achievements.class));
					break;
				}
			}
		}
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
