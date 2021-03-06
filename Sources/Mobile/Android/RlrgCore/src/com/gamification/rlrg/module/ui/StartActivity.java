package com.gamification.rlrg.module.ui;

import java.util.ArrayList;
import java.util.List;

import lvnghiem.app.core.application.SharingManager;
import lvnghiem.app.core.components.NavigationActivity;
import lvnghiem.app.core.data.NavigationData;

import org.joda.time.DateTime;
import org.joda.time.Period;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.gamification.rlrg.application.DataPreferencesManager;
import com.gamification.rlrg.application.RlrgApp;
import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.ui.components.FragmentFactory;
import com.gamification.rlrg.module.ui.components.FragmentFactory.Type;

public final class StartActivity extends NavigationActivity implements Runnable
{
	public static final String DIALOG_NETWORK_NOT_AVAILABLE = "DIALOG_NETWORK_NOT_AVAILABLE";
	public static final String DIALOG_SEARCH = "DIALOG_SEARCH";
	public static final String DIALOG_SHARE = "DIALOG_SHARE";
	public static final String DIALOG_EXIT = "DIALOG_EXIT";

	private static final DataPreferencesManager DATA = DataPreferencesManager.getInstance();

	private String[] mNavigationTitles;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
		getCoreApp().init();

		mNavigationTitles = getResources().getStringArray(R.array.navigation);
		List<NavigationData> list = new ArrayList<NavigationData>();
		for (String title : mNavigationTitles)
		{
			list.add(new NavigationData(title, getResources().getDrawable(R.drawable.ic_drawer)));
		}
		setNavigationData(list);

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
			default:
				return FragmentFactory.create(Type.SHOWROOM);
			case 1:
				return FragmentFactory.create(Type.TASK_PAGER);
			case 2:
				return FragmentFactory.create(Type.TASK_CREATE);
			case 3:
				return FragmentFactory.create(Type.SHARING);
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
		if (RlrgApp.isStart)
		{
			RlrgApp.isStart = false;
		}
	}

	public void onLoginSuccess()
	{
		DateTime lastLogin = new DateTime(DATA.getLastLogin());
		if (new Period(lastLogin, DateTime.now()).getDays() > 1)
		{
			DATA.addUserPoint(getResources().getInteger(R.integer.settings_rules_point_login_over_period));
			DATA.saveContinuousLogin(0);
		}
		else
		{
			DATA.increaseContinuousLogin();
		}
		DATA.saveLastLogin(lastLogin.getMillis());

		RlrgApp.getInstance().checkTask();
		RlrgApp.getInstance().checkAchievements();

		Fragment fragment = FragmentFactory.create(Type.SHOWROOM);
		Bundle args = new Bundle();
		args.putString("title", mNavigationTitles[0]);
		fragment.setArguments(args);
		replaceFragment(fragment);
		setActionBarOverLay(false);
		showActionBar();
		setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
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
		LOG.debug("alert type " + type);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		if (type.equals(DIALOG_NETWORK_NOT_AVAILABLE))
		{
			builder.setTitle(R.string.title_no_internet_connection);
			builder.setMessage(R.string.message_no_internet_connection);
			builder.setPositiveButton(R.string.action_ok, null);
		}
		if (type.equals(DIALOG_SEARCH))
		{
			builder.setTitle(R.string.action_search);
			builder.setView(inflate(R.layout.dialog_search));
			builder.setPositiveButton(R.string.action_ok, null);
			builder.setNegativeButton(R.string.action_cancel, null);
		}
		if (type.equals(DIALOG_SHARE))
		{
			final String args = RlrgApp.getInstance().getSharingMessage();
			View view = inflate(R.layout.dialog_share);
			view.findViewById(R.id.btn_twitter).setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					SharingManager.getInstance().shareOnTwitter(String.format(RlrgApp.getInstance().getString(R.string.message_share_twitter), args));
				}
			});
			view.findViewById(R.id.btn_facebook).setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					String subject = RlrgApp.getInstance().getString(R.string.title_share_facebook);
					String message = String.format(RlrgApp.getInstance().getString(R.string.message_share_facebook), args);
					SharingManager.getInstance().shareOnFacebook(subject, message);
				}
			});
			builder.setTitle(R.string.action_sharing);
			builder.setView(view);
			builder.setNegativeButton(R.string.action_cancel, null);
		}
		if (type.equals(DIALOG_EXIT))
		{
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
		}
		return builder.create();
	}
}
