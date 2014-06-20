package com.gamification.rlrg.module.start.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.gamification.rlrg.core.app.CoreApp;
import com.gamification.rlrg.core.components.NavigationActivity;
import com.gamification.rlrg.core.data.NavigationData;
import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.game.ui.GameListFragment;
import com.gamification.rlrg.module.showroom.ui.ShowRoomFragment;

public class StartActivity extends NavigationActivity implements Runnable
{
	private static final int LOGO_APPEAR_INTERVAL = 7000;
	
	private View mLogoText;
	
	private String[] mNavigationTitles;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		mNavigationTitles = getResources().getStringArray(R.array.navigation);
		List<NavigationData> list = new ArrayList<NavigationData>();
		for (String title : mNavigationTitles)
		{
			list.add(new NavigationData(title, getResources().getDrawable(R.drawable.ic_drawer_black)));
		}
		setNavigationData(list);
		
		setBtnActionBarRightOne(android.R.drawable.ic_search_category_default, new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				showDialog(DIALOG_SEARCH, true);
			}
		});
		
		if (CoreApp.isStart)
		{
			hideActionBar();
			mLogoText = findViewById(R.id.logo);
			new Timer().schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					runOnUiThread(StartActivity.this);
				}
			}, LOGO_APPEAR_INTERVAL);
		}
		else
		{
			showActionBar();
			setActionBarOverLay(false);
			mLogoText.setVisibility(View.GONE);
		}
		findViewById(R.id.fragment_container).setBackgroundResource(R.drawable.bg1);
		addFragment(R.id.fragment_container, ShowRoomFragment.newInstance());
	}
	
	@Override
	protected void onNavigationItemClick(ListView adapter, View view, int position, long id)
	{
		super.onNavigationItemClick(adapter, view, position, id);
		setActionBarOverLay(false);
		Fragment fragment = GameListFragment.newInstance();
		Bundle args = new Bundle();
		args.putString("title", mNavigationTitles[position]);
		int bgId = 0;
		
		switch (position)
		{
			case 0:
				fragment = ShowRoomFragment.newInstance();
				bgId = R.drawable.bg1;
				break;
			case 1:
				bgId = R.drawable.bg2;
				break;
			case 2:
				bgId = R.drawable.bg3;
				break;
			case 3:
				bgId = R.drawable.bg4;
				break;
			case 4:
				bgId = R.drawable.bg5;
				break;
			case 5:
				bgId = R.drawable.bg6;
				break;
		}
		findViewById(R.id.fragment_container).setBackgroundResource(bgId);
		fragment.setArguments(args);
		replaceFragment(fragment);
	}
	
	protected void replaceFragment(Fragment child)
	{
		replaceFragment(R.id.fragment_container, child);
	}
	
	@Override
	public void run()
	{
		showActionBar();
		setActionBarOverLay(false);
		mLogoText.setVisibility(View.GONE);
		CoreApp.isStart = false;
	}
}
