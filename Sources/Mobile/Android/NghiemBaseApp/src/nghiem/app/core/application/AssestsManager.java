package nghiem.app.core.application;

import java.io.IOException;
import java.util.Scanner;

import android.content.Context;
import nghiem.app.core.utils.LogUtils;

public class AssestsManager
{
	public static final String TAG = AssestsManager.class.getName();

	private static AssestsManager sInstance;

	private Context mContext;

	public static AssestsManager getInstance()
	{
		if (sInstance == null)
		{
			synchronized (AssestsManager.class)
			{
				if (sInstance == null)
				{
					sInstance = new AssestsManager();
				}
			}
		}
		return sInstance;
	}

	private AssestsManager()
	{
		mContext = NghiemBaseApp.getInstance();
	}

	@SuppressWarnings("resource")
	public String getData(String path)
	{
		try
		{
			Scanner scanner = new Scanner(mContext.getAssets().open(path)).useDelimiter("\\A");
			return scanner.hasNext() ? scanner.next() : "";
		}
		catch (IOException e)
		{
			LogUtils.logError(TAG, "Load data from " + path + " error!", e);
			return null;
		}
	}
}
