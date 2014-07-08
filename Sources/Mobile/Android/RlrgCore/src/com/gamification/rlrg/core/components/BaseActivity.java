package com.gamification.rlrg.core.components;

import android.R.fraction;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.gamification.rlrg.gen.BuildConfig;
import com.gamification.rlrg.gen.R;

public class BaseActivity extends FragmentActivity
{
	public static final String DIALOG_NETWORK_NOT_AVAILABLE = "NETWORK_NOT_AVAILABLE";
	public static final String DIALOG_SEARCH = "DIALOG_SEARCH";
	public static final String DIALOG_EXIT = "DIALOG_EXIT";
	private float sScreenDensity = 0;
	private int sScreenHeight = 0, sScreenWidth = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	protected View inflate(int layout)
	{
		log("inflate " + layout);
		return inflate(layout, new FrameLayout(this));
	}
	
	protected View inflate(int layout, ViewGroup root)
	{
		log("inflate " + layout + " to " + root.toString());
		return getLayoutInflater().inflate(layout, root);
	}
	
	protected View inflate(int layout, ViewGroup root, boolean attachToRoot)
	{
		log("inflate " + layout + " no attach to " + root.toString());
		return getLayoutInflater().inflate(layout, root, attachToRoot);
	}
	
	protected void addFragment(int container, Fragment child)
	{
		log("add fragment " + child.toString() + " to " + container);
		getSupportFragmentManager().beginTransaction().addToBackStack(null).add(container, child).commit();
	}
	
	protected void replaceFragment(int container, Fragment child)
	{
		log("replace fragment " + child.toString() + " to " + container);
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.popBackStack();
		fragmentManager.beginTransaction().addToBackStack(null).replace(container, child).commit();
	}
	
	protected void showDialog(String type, boolean isCancelable)
	{
		final Dialog dialog = onCreateDialog(type);
		if (dialog == null)
		{
			error("cannot create dialog type " + type);
			return;
		}
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		Fragment oldFragment = fragmentManager.findFragmentByTag(type);
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
		baseDialogFragment.show(fragmentManager, type);
	}
	
	protected Dialog onCreateDialog(String type)
	{
		if (type == null)
		{
			error("type is null");
			return null;
		}
		
		log("alert type " + type);
		if (type.equals(DIALOG_NETWORK_NOT_AVAILABLE))
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.no_internet_connection);
			builder.setMessage(R.string.message_no_internet_connection);
			builder.setPositiveButton(R.string.ok, null);
			return builder.create();
		}
		if (type.equals(DIALOG_SEARCH))
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.action_search);
			builder.setView(inflate(R.layout.dialog_search));
			builder.setPositiveButton(R.string.ok, null);
			builder.setNegativeButton(R.string.cancel, null);
			
			return builder.create();
		}
		if (type.equals(DIALOG_EXIT))
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.quit_app);
			builder.setMessage(R.string.quit_app_message);
			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					finish();
				}
			});
			builder.setNegativeButton(R.string.cancel, null);
			
			return builder.create();
		}
		return null;
	}
	
	public void log(String msg)
	{
		if (BuildConfig.DEBUG)
		{
			Log.d(getClass().getName(), msg);
		}
	}
	
	public void error(String msg)
	{
		if (BuildConfig.DEBUG)
		{
			Log.e(getClass().getName(), msg);
		}
	}
	
	public void toast(CharSequence msg)
	{
		log("toast: " + msg);
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	public void toast(int msg)
	{
		String text = getResources().getString(msg);
		log("toast id: " + text);
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
	
	public void alert(CharSequence title, CharSequence message)
	{
		log("alert " + title + " width message: " + message);
		new AlertDialog.Builder(this).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).show();
	}
	
	public void alert(CharSequence message)
	{
		log("warning: " + message);
		alert("Warning", message);
	}
	
	public void alert(int title, int message)
	{
		String textTitle = getResources().getString(title);
		String textMessage = getResources().getString(message);
		alert(textTitle, textMessage);
	}
	
	public void alert(int message)
	{
		String textMessage = getResources().getString(message);
		alert(textMessage);
	}
	
	public int getScreenWidth()
    {
        if (sScreenWidth < 1)
        {
            DisplayMetrics displayMetrics = getCurrentDisplayMetrics();
            sScreenWidth = Math.min(displayMetrics.widthPixels,
                    displayMetrics.heightPixels);
        }
        return sScreenWidth;
    }

    public int getScreenHeight()
    {
        if (sScreenHeight < 1)
        {
            DisplayMetrics displayMetrics = getCurrentDisplayMetrics();
            sScreenHeight = Math.max(displayMetrics.widthPixels,
                    displayMetrics.heightPixels);
        }
        return sScreenHeight;
    }

    public float getScreenDensity()
    {
        if (sScreenDensity <= 0)
        {
            sScreenDensity = getCurrentDisplayMetrics().density;
        }
        return sScreenDensity;
    }

    private DisplayMetrics getCurrentDisplayMetrics()
    {
        WindowManager windowManager = (WindowManager) getSystemService(
                Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return displayMetrics;
    }

    public int getScreenOrientationWidth()
    {
        return getCurrentDisplayMetrics().widthPixels;
    }

    public int getScreenOrientationHeight()
    {
        return getCurrentDisplayMetrics().heightPixels;
    }
}
