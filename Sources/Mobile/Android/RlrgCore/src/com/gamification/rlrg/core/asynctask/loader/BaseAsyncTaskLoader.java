package com.gamification.rlrg.core.asynctask.loader;

import java.util.List;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class BaseAsyncTaskLoader<T> extends AsyncTaskLoader<List<T>>
{
	private List<T> list;
	
	public BaseAsyncTaskLoader(Context ctx, List<T> list)
	{
		super(ctx);
		this.list = list;
	}
	
	public List<T> loadInBackground()
	{
		return list;
	}
	
	public void deliverResult(List<T> list)
	{
		if (isReset() && list != null)
		{
			onReleaseResources(list);
		}
		
		List<T> oldList = list;
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
	
	protected void onStopLoading()
	{
		cancelLoad();
	}
	
	public void onCanceled(List<T> list)
	{
		super.onCanceled(list);
		onReleaseResources(list);
	}
	
	protected void onReset()
	{
		super.onReset();
		onStopLoading();
		if (list != null)
		{
			onReleaseResources(list);
		}
	}
	
	protected void onReleaseResources(List<T> list)
	{
		list = null;
		System.gc();
	}
}