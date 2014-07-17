package nghiem.app.core.application;

import android.app.Application;
import android.content.res.Configuration;

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
}
