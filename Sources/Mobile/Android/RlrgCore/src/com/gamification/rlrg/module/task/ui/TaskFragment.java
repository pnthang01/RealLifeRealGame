package com.gamification.rlrg.module.task.ui;

import java.util.ArrayList;
import java.util.List;

import nghiem.app.core.components.ViewPagerFragment;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.gamification.rlrg.application.RlrgApp;
import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.start.ui.StartActivity;

public class TaskFragment extends ViewPagerFragment implements View.OnClickListener
{
	StartActivity mActivity;

	public static TaskFragment newInstance()
	{
		List<Fragment> pages = new ArrayList<Fragment>();
		pages.add(TodayFragment.newInstance());
		pages.add(TomorrowFragment.newInstance());
		pages.add(WeekFragment.newInstance());
		pages.add(MonthFragment.newInstance());
		pages.add(YearFragment.newInstance());

		String[] titles = RlrgApp.getInstance().getResources().getStringArray(R.array.task);

		return new TaskFragment(pages, titles, TYPE_NORMAL);
	}

	public TaskFragment(List<Fragment> pages, CharSequence[] titles, int type)
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
	}

	@Override
	public void onClick(View v)
	{
		Fragment fragment = TaskCreatorFragment.newInstance();
		mActivity.replaceFragment(R.id.fragment_container, fragment);
	}
}
