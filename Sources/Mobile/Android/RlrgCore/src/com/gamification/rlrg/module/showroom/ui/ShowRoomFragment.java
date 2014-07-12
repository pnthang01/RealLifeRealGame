package com.gamification.rlrg.module.showroom.ui;

import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.gamification.rlrg.application.CoreApp;
import com.gamification.rlrg.core.components.BaseEntity;
import com.gamification.rlrg.core.components.ListViewFragment;
import com.gamification.rlrg.data.Badges;
import com.gamification.rlrg.data.Badges.Badge;
import com.gamification.rlrg.data.Badges.BadgeList;
import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.start.ui.StartActivity;

public class ShowRoomFragment extends ListViewFragment<Badge>
{
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
			
			StartActivity activity = (StartActivity) getActivity();
			if (activity != null)
			{
				mList = activity.getCoreApp().getBadges().getData().getList();
			}
		}
	}
	
	@Override
	protected View getListItemView(ListView parent, View view, int position)
	{
		Badge item = (Badge) getListAdapter().getItem(position);
		((TextView) view).setText(item.getName());
		return view;
	}
	
	@Override
	public void onListItemClick(ListView listView, View view, int position, long id)
	{
	}
}