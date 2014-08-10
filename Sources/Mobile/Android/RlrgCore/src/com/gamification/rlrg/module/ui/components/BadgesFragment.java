package com.gamification.rlrg.module.ui.components;

import lvnghiem.app.core.components.ListViewFragment;

import org.joda.time.DateTime;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.gamification.rlrg.data.Achievements;
import com.gamification.rlrg.data.entity.Achievement;
import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.ui.StartActivity;

final class BadgesFragment extends ListViewFragment<Achievement>
{
	public static BadgesFragment newInstance()
	{
		return new BadgesFragment();
	}

	public BadgesFragment()
	{
		super(R.layout.list_view_badges, R.layout.list_item_game);
	}

	public BadgesFragment(int listLayout, int itemLayout)
	{
		super(listLayout, itemLayout);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (mArguments != null)
		{
			// String title = mArguments.getString("title");
		}
		StartActivity activity = (StartActivity) getActivity();
		if (activity != null)
		{
			Achievements achievements = activity.getCoreApp().getAchievements();
			if (achievements.isSuccessful())
			{
				setData(achievements.getData().getElements());
			}
			else
			{
				LOG.debug(achievements.getMessage());
			}
		}
	}

	@Override
	@SuppressLint("SimpleDateFormat")
	protected View getListItemView(ListView parent, View view, int position)
	{
		Achievement item = (Achievement) getListAdapter().getItem(position);

		DateTime date = new DateTime(item.getAchievedTime());

		String message = "AchievedTime: %s\nBadge: %s";

		((TextView) view).setText(String.format(message, date.toString(getString(R.string.settings_date_time_format)), item.getBadge().getName()));
		return view;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id)
	{
	}
}