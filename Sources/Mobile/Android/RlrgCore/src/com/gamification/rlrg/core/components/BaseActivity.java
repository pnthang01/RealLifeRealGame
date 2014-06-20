package com.gamification.rlrg.core.components;

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
import android.widget.FrameLayout;
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
	
	protected void addFragment(int containerResId, Fragment child)
	{
		getSupportFragmentManager().beginTransaction().addToBackStack(null).add(containerResId, child).commit();
	}
	
	protected void replaceFragment(int containerResId, Fragment child)
	{
		getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(containerResId, child).commit();
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
			builder.setTitle(R.string.no_internet_connection);
			builder.setMessage(R.string.message_no_internet_connection);
			builder.setPositiveButton(R.string.ok, null);
			return builder.create();
		}
		else if (dialogType.equals(DIALOG_SEARCH))
		{
			ViewGroup view = new FrameLayout(this);
			inflate(R.layout.dialog_search, view);
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.action_search);
			builder.setView(view);
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
	
	protected void toast(int msg)
	{
		if (BuildConfig.DEBUG)
		{
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		}
	}
	
	protected void inform(CharSequence message)
	{
		if (BuildConfig.DEBUG)
		{
			new AlertDialog.Builder(this).setTitle("Warning").setMessage(message).setPositiveButton(R.string.ok, null).show();
		}
	}
}
