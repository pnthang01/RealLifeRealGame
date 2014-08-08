package lvnghiem.app.core.application;

import java.io.IOException;
import java.util.Scanner;

import android.content.Context;

public class AssestsManager extends BaseController
{
	@SuppressWarnings("rawtypes")
	private static ThreadLocal sInitHolder = new ThreadLocal();
	private static AssestsManager sInstance;

	private Context mContext;

	@SuppressWarnings("unchecked")
	public static AssestsManager getInstance()
	{
		if (sInitHolder.get() == null)
		{
			synchronized (AssestsManager.class)
			{
				if (sInstance == null)
				{
					sInstance = new AssestsManager();
				}
				sInitHolder.set(Boolean.TRUE);
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
			LOG.error("Load data from " + path + " error!", e);
			return null;
		}
	}
}
