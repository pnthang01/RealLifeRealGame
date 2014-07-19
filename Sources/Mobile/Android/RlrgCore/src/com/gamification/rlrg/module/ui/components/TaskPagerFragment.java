package com.gamification.rlrg.module.ui.components;

import java.util.ArrayList;
import java.util.List;

import nghiem.app.core.components.ViewPagerFragment;
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
		pages.add(TaskPageFragment.newInstance());
		pages.add(TaskPageFragment.newInstance());
		pages.add(TaskPageFragment.newInstance());

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
	}

	@Override
	public void onClick(View v)
	{
		mActivity.replaceFragment(TaskCreateFragment.newInstance());
	}
}
