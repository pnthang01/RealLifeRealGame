package com.gamification.rlrg.module.ui.components;

import android.support.v4.app.Fragment;

public class FragmentFactory
{
	public static enum Type
	{
		LOGIN, SHOWROOM, TASK_CREATE, TASK_DETAIL, TASK_PAGER, TASK_PAGE
	}

	public static Fragment create(Type type)
	{
		switch (type)
		{
			case LOGIN:
				return LoginFragment.newInstance();
			case SHOWROOM:
				return ShowRoomFragment.newInstance();
			case TASK_CREATE:
				return TaskCreateFragment.newInstance();
			case TASK_DETAIL:
				return TaskDetailFragment.newInstance();
			case TASK_PAGER:
				return TaskPagerFragment.newInstance();
			case TASK_PAGE:
				return TaskPageFragment.newInstance();
			default:
				return null;
		}
	}
}
