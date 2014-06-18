package com.gamification.rlrg.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gamification.rlrg.gen.BuildConfig;
import com.gamification.rlrg.gen.R;

public class BaseActivity extends FragmentActivity
{
	public static final String DIALOG_NETWORK_NOT_AVAILABLE = "NETWORK_NOT_AVAILABLE";
	public static final String DIALOG_SEARCH = "DIALOG_SEARCH";
	
	protected LayoutInflater mInflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mInflater = getLayoutInflater();
	}
	
	protected void inflate(int layoutResID, ViewGroup viewGroup)
	{
		mInflater.inflate(layoutResID, viewGroup);
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
	
	protected void toast(CharSequence msg)
	{
		if (BuildConfig.DEBUG)
		{
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		}
	}
	
	protected void toast(int resId)
	{
		if (BuildConfig.DEBUG)
		{
			Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
		}
	}
}
