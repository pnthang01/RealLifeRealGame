package com.gamification.rlrg.module.start.ui;

import java.util.Timer;
import java.util.TimerTask;

import com.gamification.rlrg.activity.BaseActivity;
import com.gamification.rlrg.core.app.CoreApp;
import com.gamification.rlrg.gen.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class StartActivity extends BaseActivity implements Runnable
{
	private View mLogoText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		if (CoreApp.isStart)
		{
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
			mActionBar.show();
		}
	}
	
	@Override
	public void run()
	{
		mLogoText.setVisibility(View.GONE);
		mActionBar.show();
		CoreApp.isStart = false;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.start_option, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				showDialog(DIALOG_SEARCH, true);
				break;
			case R.id.action_search:
				showDialog(DIALOG_SEARCH, true);
				break;
			default:
				break;
		}

		return super.onOptionsItemSelected(item);
	}
}
