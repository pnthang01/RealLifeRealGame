package com.gamification.rlrg.module.start.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		String[] navigationTitles = getResources().getStringArray(R.array.navigation);
		List<NavigationData> list = new ArrayList<NavigationData>();
		for (String title : navigationTitles)
		{
			list.add(new NavigationData(title, null));
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
	@SuppressLint("DefaultLocale")
	protected void onNavigationItemClick(ListView adapter, View view, int position, long id)
	{
		super.onNavigationItemClick(adapter, view, position, id);
		toast("Item " + position + " clicked!");
	}
	
	@Override
	public void run()
	{
		showActionBar();
		mLogoText.setVisibility(View.GONE);
		CoreApp.isStart = false;
	}
}
