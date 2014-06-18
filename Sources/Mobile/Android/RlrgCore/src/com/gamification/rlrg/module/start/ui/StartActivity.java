package com.gamification.rlrg.module.start.ui;

import java.util.Timer;
import java.util.TimerTask;

import com.gamification.rlrg.activity.NavigationActivity;
import com.gamification.rlrg.core.app.CoreApp;
import com.gamification.rlrg.gen.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class StartActivity extends NavigationActivity implements Runnable
{
	private View mLogoText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
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
	public void run()
	{
		showActionBar();
		mLogoText.setVisibility(View.GONE);
		CoreApp.isStart = false;
	}
}
