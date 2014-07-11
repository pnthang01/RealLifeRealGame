package com.gamification.rlrg.application;

import com.gamification.rlrg.data.Achievements;
import com.gamification.rlrg.data.Badges;
import com.gamification.rlrg.data.Categories;
import com.gamification.rlrg.data.Tasks;
import com.gamification.rlrg.data.Users;
import com.gamification.rlrg.gen.R;
import com.google.gson.Gson;

import android.app.Application;
import android.content.res.Resources;

public class CoreApp extends Application
{
	public static boolean isStart = true;
	
	private Gson mGson = new Gson();
	private Resources mResources = null;
	
	private Users users;
	private Categories categories;
	private Tasks tasks;
	private Achievements achievements;
	private Badges badges;
	
	public void init()
	{
		users = (Users) fromJson(R.string.get_users, Users.class);
		badges = (Badges) fromJson(R.string.get_badges, Badges.class);
		categories = (Categories) fromJson(R.string.get_categories, Categories.class);
		tasks = (Tasks) fromJson(R.string.get_tasks_by_category_and_user, Tasks.class);
		achievements = (Achievements) fromJson(R.string.get_user_achievements, Achievements.class);
	}
	
	private Object fromJson(int id, Class<?> clazz)
	{
		if (mResources == null)
		{
			mResources = getResources();
		}
		return mGson.fromJson(mResources.getString(id), clazz);
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
