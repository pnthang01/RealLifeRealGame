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

import com.gamification.rlrg.activity.navigation.NavigationActivity;
import com.gamification.rlrg.activity.navigation.NavigationData;
import com.gamification.rlrg.core.app.CoreApp;
import com.gamification.rlrg.gen.R;

public class StartActivity extends NavigationActivity implements Runnable
{
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
			}, 7000);
		}
		else
		{
			showActionBar();
			mLogoText.setVisibility(View.GONE);
		}
	}
	
	@Override
	protected void onNavigationItemClick(ListView adapter, View view, int position, long id)
	{
		super.onNavigationItemClick(adapter, view, position, id);
		
		Bundle args = new Bundle();
		args.putString("title", mNavigationTitles[position]);
		
		Fragment detail = new GameFagment();
		detail.setArguments(args);
		getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container, detail).commit();
	}
	
	@Override
	public void run()
	{
		showActionBar();
		mLogoText.setVisibility(View.GONE);
		CoreApp.isStart = false;
	}
}
