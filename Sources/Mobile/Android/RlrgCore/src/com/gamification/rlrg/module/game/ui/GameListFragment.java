package com.gamification.rlrg.module.game.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.gamification.rlrg.core.components.BaseActivity;
import com.gamification.rlrg.core.components.ListViewFragment;
import com.gamification.rlrg.core.data.GameData;
import com.gamification.rlrg.gen.R;

@SuppressLint("ValidFragment")
public class GameListFragment extends ListViewFragment<GameData>
{
	public static GameListFragment newInstance()
	{
		return new GameListFragment(R.layout.list_view, R.layout.list_item_game);
	}
	
	public GameListFragment(int listLayout, int itemLayout)
	{
		super(listLayout, itemLayout);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (mArguments != null)
		{
			String title = mArguments.getString("title");
			
			// TODO: Move to CoreApp
			for (String navigationTitle : getResources().getStringArray(R.array.navigation))
			{
				if (title.equals(navigationTitle))
				{
					mList.add(new GameData(title + ": Đọc sách"));
					mList.add(new GameData(title + ": Chạy bộ"));
					mList.add(new GameData(title + ": Bắn cung"));
					mList.add(new GameData(title + ": Bơi lội"));
					break;
				}
			}
		}
	}
	
	@Override
	protected View getListItemView(ListView parent, View view, int position)
	{
		GameData item = (GameData) getListAdapter().getItem(position);
		((TextView) view).setText(item.getTitle());
		return view;
	}
	
	@Override
	public void onListItemClick(ListView listView, View view, int position, long id)
	{
		GameData item = (GameData) getListAdapter().getItem(position);
		((BaseActivity) getActivity()).toast(item.getTitle());
	}
}