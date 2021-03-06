package com.gamification.rlrg.module.ui.components;

import java.util.ArrayList;
import java.util.List;

import lvnghiem.app.core.components.ViewPagerFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.gamification.rlrg.application.RlrgApp;
import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.ui.StartActivity;

final class TaskPagerFragment extends ViewPagerFragment implements View.OnClickListener
{
	private StartActivity mActivity;

	static TaskPagerFragment newInstance()
	{
		List<Fragment> pages = new ArrayList<Fragment>();

		Fragment today = TaskPageFragment.newInstance();
		Bundle todayArgs = new Bundle();
		todayArgs.putString("time", "Today");
		today.setArguments(todayArgs);
		pages.add(today);

		Fragment tomorrow = TaskPageFragment.newInstance();
		Bundle tomorrowArgs = new Bundle();
		tomorrowArgs.putString("time", "Tomorrow");
		tomorrow.setArguments(tomorrowArgs);
		pages.add(tomorrow);

		Fragment week = TaskPageFragment.newInstance();
		Bundle weekArgs = new Bundle();
		weekArgs.putString("time", "Week");
		week.setArguments(weekArgs);
		pages.add(week);

		Fragment outdate = TaskPageFragment.newInstance();
		Bundle outdateArgs = new Bundle();
		outdateArgs.putString("time", "Uutdate");
		outdate.setArguments(outdateArgs);
		pages.add(outdate);

		String[] titles = RlrgApp.getInstance().getResources().getStringArray(R.array.task);

		return new TaskPagerFragment(pages, titles, TYPE_NORMAL);
	}

	private TaskPagerFragment(List<Fragment> pages, CharSequence[] titles, int type)
	{
		super(pages, titles, type);
	}

	@Override
	public void onPageSelected(int position)
	{
		super.onPageSelected(position);
		((StartActivity) getActivity()).setActionBarTitle(mTitles.get(position));
	}

	@Override
	public void onStart()
	{
		super.onStart();
		mActivity = (StartActivity) getActivity();
		if (mActivity == null)
		{
			return;
		}
		mActivity.setBtnActionBarRightText("Add task", this);
		((StartActivity) getActivity()).setActionBarTitle(mTitles.get(0));
	}

	@Override
	public void onClick(View v)
	{
		mActivity.replaceFragment(TaskCreateFragment.newInstance());
	}
}
