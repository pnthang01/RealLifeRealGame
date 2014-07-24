package nghiem.app.core.components.loader;

import java.util.List;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class AsyncLoader<Data> extends AsyncTaskLoader<List<Data>>
{
	private List<Data> list;

	public AsyncLoader(Context ctx, List<Data> list)
	{
		super(ctx);
		this.list = list;
	}

	@Override
	public List<Data> loadInBackground()
	{
		return list;
	}

	public void deliverResult(List<Data> list)
	{
		if (isReset() && list != null)
		{
			onReleaseResources(list);
		}

		List<Data> oldList = list;
		this.list = list;
		if (isStarted())
		{
			super.deliverResult(list);
		}
		if (oldList != null)
		{
			onReleaseResources(oldList);
		}
	}

	@Override
	protected void onStartLoading()
	{
		if (list != null)
		{
			deliverResult(list);
		}
		if (takeContentChanged() || list == null)
		{
			forceLoad();
		}
	}

	@Override
	protected void onStopLoading()
	{
		cancelLoad();
	}

	@Override
	public void onCanceled(List<Data> list)
	{
		super.onCanceled(list);
		onReleaseResources(list);
	}

	@Override
	protected void onReset()
	{
		super.onReset();
		onStopLoading();
		if (list != null)
		{
			onReleaseResources(list);
		}
	}

	protected void onReleaseResources(List<Data> list)
	{
		if (list != null)
		{
			list = null;
		}
	}
}
