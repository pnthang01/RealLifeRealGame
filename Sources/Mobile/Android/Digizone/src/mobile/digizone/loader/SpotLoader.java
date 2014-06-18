package mobile.digizone.loader;

import java.util.List;

import mobile.digizone.parser.SpotParser.SpotHandler;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

// Bộ tải spot list vào spot adapter
public class SpotLoader extends AsyncTaskLoader<List<SpotHandler>>
{
	
	private List<SpotHandler> spotList;
	
	public SpotLoader(Context ctx, List<SpotHandler> spots)
	{
		super(ctx);
		this.spotList = spots;
	}
	
	public List<SpotHandler> loadInBackground()
	{
		return spotList;
	}
	
	public void deliverResult(List<SpotHandler> spots)
	{
		if (isReset() && spots != null)
		{
			onReleaseResources(spots);
		}
		
		List<SpotHandler> oldspots = spots;
		spotList = spots;
		if (isStarted())
		{
			super.deliverResult(spots);
		}
		if (oldspots != null)
		{
			onReleaseResources(oldspots);
		}
	}
	
	protected void onStartLoading()
	{
		if (spotList != null)
		{
			deliverResult(spotList);
		}
		if (takeContentChanged() || spotList == null)
		{
			forceLoad();
		}
	}
	
	protected void onStopLoading()
	{
		cancelLoad();
	}
	
	public void onCanceled(List<SpotHandler> spots)
	{
		super.onCanceled(spots);
		onReleaseResources(spots);
	}
	
	protected void onReset()
	{
		super.onReset();
		onStopLoading();
		if (spotList != null)
		{
			onReleaseResources(spotList);
		}
	}
	
	protected void onReleaseResources(List<SpotHandler> spots)
	{
		spots = null;
		System.gc();
	}
}