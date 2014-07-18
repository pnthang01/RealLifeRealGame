package nghiem.app.core.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class NghiemBaseApp extends Application
{
	private static NghiemBaseApp sInstance;

	public static NghiemBaseApp getInstance()
	{
		if (sInstance == null)
		{
			synchronized (NghiemBaseApp.class)
			{
				if (sInstance == null)
				{
					sInstance = new NghiemBaseApp();
				}
			}
		}
		return sInstance;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		sInstance = this;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		sInstance = this;
	}

	public void showKeyboard(View view, boolean isShowed)
	{
		InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (isShowed)
		{
			inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
		}
		else
		{
			inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}
}
