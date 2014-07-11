package com.gamification.rlrg.application;

import com.gamification.rlrg.data.Achievement;
import com.gamification.rlrg.data.Badge;
import com.gamification.rlrg.data.BaseResult;
import com.gamification.rlrg.data.Category;
import com.gamification.rlrg.data.Task;
import com.gamification.rlrg.data.User;
import com.gamification.rlrg.gen.R;
import com.google.gson.Gson;

import android.app.Application;
import android.content.res.Resources;

public class CoreApp extends Application
{
	public static boolean isStart = true;
	
	private Gson mGson = new Gson();
	private Resources mResources = null;
	
	private BaseResult<User> users;
	private BaseResult<Category> categories;
	private BaseResult<Task> tasks;
	private BaseResult<Achievement> achievements;
	private BaseResult<Badge> badges;
	
	@SuppressWarnings("unchecked")
	public void init()
	{
		users = (BaseResult<User>) fromJson(R.string.get_users);
		badges = (BaseResult<Badge>) fromJson(R.string.get_badges);
		categories = (BaseResult<Category>) fromJson(R.string.get_categories);
		tasks = (BaseResult<Task>) fromJson(R.string.get_tasks_by_category_and_user);
		achievements = (BaseResult<Achievement>) fromJson(R.string.get_user_achievements);
	}
	
	private Object fromJson(int id)
	{
		if (mResources == null)
		{
			mResources = getResources();
		}
		return mGson.fromJson(mResources.getString(id), BaseResult.class);
	}
	
	public BaseResult<User> getUsers()
	{
		return users;
	}
	
	public void setUsers(BaseResult<User> users)
	{
		this.users = users;
	}
	
	public BaseResult<Category> getCategories()
	{
		return categories;
	}
	
	public void setCategories(BaseResult<Category> categories)
	{
		this.categories = categories;
	}
	
	public BaseResult<Task> getTasks()
	{
		return tasks;
	}
	
	public void setTasks(BaseResult<Task> tasks)
	{
		this.tasks = tasks;
	}
	
	public BaseResult<Achievement> getAchievements()
	{
		return achievements;
	}
	
	public void setAchievements(BaseResult<Achievement> achievements)
	{
		this.achievements = achievements;
	}
	
	public BaseResult<Badge> getBadges()
	{
		return badges;
	}
	
	public void setBadges(BaseResult<Badge> badges)
	{
		this.badges = badges;
	}
}
