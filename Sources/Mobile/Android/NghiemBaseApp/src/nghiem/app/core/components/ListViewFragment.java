package nghiem.app.core.components;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewFragment<Data> extends ListFragment implements LoaderCallbacks<List<Data>>
{
	private class AsyncLoader extends AsyncTaskLoader<List<Data>>
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
			list = null;
		}
	}

	private class Adapter extends ArrayAdapter<Data>
	{
		public Adapter()
		{
			super(getActivity(), mListLayout);
		}

		public void setData(List<Data> list)
		{
			clear();
			if (list != null)
			{
				for (Data item : list)
				{
					add(item);
				}
			}
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View view = convertView;
			if (view == null)
			{
				int layoutResId = mIsMultipleItemLayout ? mItemLayouts[position] : mItemLayout;
				view = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(layoutResId, parent, false);
			}
			return getListItemView((ListView) parent, view, position);
		}
	}

	protected Adapter mAdapter;
	protected Bundle mArguments;
	protected int mListLayout, mItemLayout;
	protected int[] mItemLayouts;

	private boolean mIsMultipleItemLayout = false;

	private List<Data> mList = new ArrayList<Data>();

	public ListViewFragment(int listLayout, int itemLayout)
	{
		super();
		mListLayout = listLayout;
		mItemLayout = itemLayout;

	}

	public ListViewFragment(int listLayout, int[] itemLayouts)
	{
		super();
		mListLayout = listLayout;
		mItemLayouts = itemLayouts;
		mIsMultipleItemLayout = true;
	}

	public void setData(List<Data> list)
	{
		mList = list;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mArguments = getArguments();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(mListLayout, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		if (getActivity() != null)
		{
			mAdapter = new Adapter();
			setListAdapter(mAdapter);
			getLoaderManager().initLoader(0, null, this);
		}
	}

	protected View getListItemView(ListView parent, View view, int position)
	{
		return view;
	}

	@Override
	public Loader<List<Data>> onCreateLoader(int id, Bundle args)
	{
		if (getActivity() == null)
		{
			return null;
		}
		return new AsyncLoader(getActivity(), mList);
	}

	@Override
	public void onLoadFinished(Loader<List<Data>> loader, List<Data> data)
	{
		mAdapter.setData(data);
	}

	@Override
	public void onLoaderReset(Loader<List<Data>> loader)
	{
		mAdapter.setData(null);
	}
}