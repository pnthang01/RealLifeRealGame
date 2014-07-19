package com.gamification.rlrg.module.ui.components;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import nghiem.app.core.components.ListViewFragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.gamification.rlrg.data.Achievements;
import com.gamification.rlrg.data.entity.Achievement;
import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.ui.StartActivity;

final class ShowRoomFragment extends ListViewFragment<Achievement>
{
	public static final String TAG = ShowRoomFragment.class.getName();

	public static ShowRoomFragment newInstance()
	{
		return new ShowRoomFragment(R.layout.list_view, R.layout.list_item_game);
	}

	public ShowRoomFragment(int listLayout, int itemLayout)
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
			activity.hideActionBarButtonRight();
			Achievements achievements = activity.getCoreApp().getAchievements();
			if (achievements.isSuccessful())
			{
				setData(achievements.getData().getElements());
			}
			else
			{
				Log.d(TAG, achievements.getMessage());
			}
		}
	}

	@Override
	@SuppressLint("SimpleDateFormat")
	protected View getListItemView(ListView parent, View view, int position)
	{
		Achievement item = (Achievement) getListAdapter().getItem(position);

		Date date = new Date(item.getAchievedTime());
		Format format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		String message = "AchievedTime: %s\nBadge: %s";

		((TextView) view).setText(String.format(message, format.format(date), item.getBadge().getName()));
		return view;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id)
	{
	}
}