package com.gamification.rlrg.core.asynctask;

import java.util.List;

import com.gamification.rlrg.core.data.GameData;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class GameAsynctaskLoader extends AsyncTaskLoader<List<GameData>>
{
	private List<GameData> mGames;
	
	public GameAsynctaskLoader(Context ctx, List<GameData> games)
	{
		super(ctx);
		this.mGames = games;
	}
	
	public List<GameData> loadInBackground()
	{
		return mGames;
	}
	
	public void deliverResult(List<GameData> games)
	{
		if (isReset() && games != null)
		{
			onReleaseResources(games);
		}
		
		List<GameData> oldgames = games;
		mGames = games;
		if (isStarted())
		{
			super.deliverResult(games);
		}
		if (oldgames != null)
		{
			onReleaseResources(oldgames);
		}
	}
	
	protected void onStartLoading()
	{
		if (mGames != null)
		{
			deliverResult(mGames);
		}
		if (takeContentChanged() || mGames == null)
		{
			forceLoad();
		}
	}
	
	protected void onStopLoading()
	{
		cancelLoad();
	}
	
	public void onCanceled(List<GameData> games)
	{
		super.onCanceled(games);
		onReleaseResources(games);
	}
	
	protected void onReset()
	{
		super.onReset();
		onStopLoading();
		if (mGames != null)
		{
			onReleaseResources(mGames);
		}
	}
	
	protected void onReleaseResources(List<GameData> games)
	{
		games = null;
		System.gc();
	}
}