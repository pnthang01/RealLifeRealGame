package com.gamification.rlrg.activity;

import com.gamification.rlrg.gen.BuildConfig;
import com.gamification.rlrg.gen.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class BaseActivity extends ActionBarActivity
{
	public static final String DIALOG_NETWORK_NOT_AVAILABLE = "NETWORK_NOT_AVAILABLE";
	public static final String DIALOG_SEARCH = "DIALOG_SEARCH";
	
	protected ActionBar mActionBar;
	
	@Override
	protected void onStart()
	{
		super.onStart();
		mActionBar = getSupportActionBar();
		mActionBar.setHomeButtonEnabled(true);
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#99009900")));
		mActionBar.hide();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	protected void showDialog(String dialogType, boolean isCancelable)
	{
		final Dialog dialog = onCreateDialog(dialogType);
		if (dialog == null)
		{
			return;
		}
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		Fragment oldFragment = fragmentManager.findFragmentByTag(dialogType);
		if (oldFragment != null)
		{
			fragmentTransaction.remove(oldFragment);
			fragmentTransaction.commitAllowingStateLoss();
		}
		
		DialogFragment baseDialogFragment = new DialogFragment()
		{
			@Override
			public Dialog onCreateDialog(Bundle savedInstanceState)
			{
				return dialog;
			}
		};
		baseDialogFragment.setCancelable(isCancelable);
		baseDialogFragment.show(fragmentManager, dialogType);
	}
	
	protected Dialog onCreateDialog(String dialogType)
	{
		if (dialogType == null)
		{
			return null;
		}
		
		if (dialogType.equals(DIALOG_NETWORK_NOT_AVAILABLE))
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.no_internet_connection).setMessage(R.string.message_no_internet_connection).setPositiveButton(R.string.ok, null);
			return builder.create();
		}
		if (dialogType.equals(DIALOG_SEARCH))
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.action_search);
			builder.setMessage("");
			builder.setPositiveButton(R.string.ok, null);
			builder.setNegativeButton(R.string.cancel, null);
			return builder.create();
		}
		return null;
	}
	
	protected void log(String tag, String msg)
	{
		if (BuildConfig.DEBUG)
		{
			Log.d(tag, msg);
		}
	}
	
	protected void error(String tag, String msg)
	{
		if (BuildConfig.DEBUG)
		{
			Log.e(tag, msg);
		}
	}
}
