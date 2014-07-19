package com.gamification.rlrg.module.ui;

import java.util.ArrayList;
import java.util.List;

import nghiem.app.core.components.NavigationActivity;
import nghiem.app.core.data.NavigationData;
import nghiem.app.core.utils.LogUtils;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.gamification.rlrg.application.RlrgApp;
import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.ui.components.FragmentFactory;
import com.gamification.rlrg.module.ui.components.FragmentFactory.Type;

public final class StartActivity extends NavigationActivity implements Runnable
{
	private String[] mNavigationTitles;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		getCoreApp().init();

		mNavigationTitles = getResources().getStringArray(R.array.navigation);
		List<NavigationData> list = new ArrayList<NavigationData>();
		for (String title : mNavigationTitles)
		{
			list.add(new NavigationData(title, getResources().getDrawable(R.drawable.ic_drawer)));
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

		if (RlrgApp.isStart)
		{
			hideActionBar();
		}
		else
		{
			showActionBar();
			setActionBarOverLay(false);
		}
		addFragment(R.id.fragment_container, FragmentFactory.create(Type.LOGIN));
	}

	public RlrgApp getCoreApp()
	{
		return (RlrgApp) getApplication();
	}

	@Override
	protected void onNavigationItemClick(ListView adapter, View view, int position, long id)
	{
		super.onNavigationItemClick(adapter, view, position, id);
		if (position == mNavigationTitles.length - 1)
		{
			showDialog(DIALOG_EXIT, true);
		}
		else
		{
			Bundle args = new Bundle();
			args.putString("title", mNavigationTitles[position]);
			Fragment fragment = getNavigationFragment(position);
			fragment.setArguments(args);
			replaceFragment(fragment);
		}
	}

	private Fragment getNavigationFragment(int position)
	{
		switch (position)
		{
			case 1:
				return FragmentFactory.create(Type.TASK_PAGER);
			case 2:
				return FragmentFactory.create(Type.TASK_CREATE);
			default:
				return FragmentFactory.create(Type.SHOWROOM);
		}
	}

	public void replaceFragment(Fragment child)
	{
		replaceFragment(R.id.fragment_container, child);
	}

	@Override
	public void run()
	{
		showActionBar();
		setActionBarOverLay(false);
		RlrgApp.isStart = false;
	}

	public void onLoginSuccess()
	{
		findViewById(R.id.fragment_container).setBackgroundResource(R.drawable.bg1);
		Fragment fragment = FragmentFactory.create(Type.SHOWROOM);
		Bundle args = new Bundle();
		args.putString("title", mNavigationTitles[0]);
		fragment.setArguments(args);
		replaceFragment(fragment);
		setActionBarOverLay(false);
		showActionBar();
	}

	@Override
	public void onBackPressed()
	{
		if (getSupportFragmentManager().getBackStackEntryCount() > 1)
		{
			super.onBackPressed();
		}
		else
		{
			showDialog(DIALOG_EXIT, true);
		}
	}

	@Override
	public Dialog onCreateDialog(String type)
	{
		super.onCreateDialog(type);
		LogUtils.log(TAG, "alert type " + type);
		if (type.equals(DIALOG_NETWORK_NOT_AVAILABLE))
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.title_no_internet_connection);
			builder.setMessage(R.string.message_no_internet_connection);
			builder.setPositiveButton(R.string.action_ok, null);
			return builder.create();
		}
		if (type.equals(DIALOG_SEARCH))
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.action_search);
			builder.setView(inflate(R.layout.dialog_search));
			builder.setPositiveButton(R.string.action_ok, null);
			builder.setNegativeButton(R.string.action_cancel, null);

			return builder.create();
		}
		if (type.equals(DIALOG_EXIT))
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.action_quit_app);
			builder.setMessage(R.string.message_quit_app);
			builder.setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					finish();
				}
			});
			builder.setNegativeButton(R.string.action_cancel, null);

			return builder.create();
		}
		return null;
	}
}
