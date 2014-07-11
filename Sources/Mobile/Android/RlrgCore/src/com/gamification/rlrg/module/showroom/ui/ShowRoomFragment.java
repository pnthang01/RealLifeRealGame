package com.gamification.rlrg.module.showroom.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.gamification.rlrg.core.components.ListViewFragment;
import com.gamification.rlrg.data.Badges.Badge;
import com.gamification.rlrg.gen.R;

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
			//String title = mArguments.getString("title");
			
			// TODO: Move to CoreApp
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