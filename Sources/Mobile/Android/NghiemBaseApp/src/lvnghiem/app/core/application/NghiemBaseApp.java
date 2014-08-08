package lvnghiem.app.core.application;

import lvnghiem.app.core.utils.Logger;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class NghiemBaseApp extends Application
{
	protected final Logger LOG = new Logger(getClass());

	@SuppressWarnings("rawtypes")
	private static ThreadLocal sInitHolder = new ThreadLocal();
	private static NghiemBaseApp sInstance;

	@SuppressWarnings("unchecked")
	public static NghiemBaseApp getInstance()
	{
		if (sInitHolder.get() == null)
		{
			synchronized (NghiemBaseApp.class)
			{
				if (sInstance == null)
				{
					sInstance = new NghiemBaseApp();
				}
				sInitHolder.set(Boolean.TRUE);
			}
		}
		return sInstance;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		if (sInstance == null)
		{
			sInstance = this;
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		if (sInstance == null)
		{
			sInstance = this;
		}
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
