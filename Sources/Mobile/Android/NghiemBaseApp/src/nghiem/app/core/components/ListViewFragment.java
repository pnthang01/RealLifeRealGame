package nghiem.app.core.components;

import java.util.ArrayList;
import java.util.List;

import nghiem.app.core.components.loader.AsyncLoader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewFragment<Data> extends ListFragment implements LoaderCallbacks<List<Data>>, OnScrollListener
{
    public interface OnScrollUpDownListener
    {
        public void onScrollUp();
        
        public void onScrollDown();
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

	protected ListView mListView;
	protected Adapter mAdapter;
	protected Bundle mArguments;
	
	protected int mListLayout, mItemLayout;
	protected int[] mItemLayouts;
	
	private boolean mIsMultipleItemLayout = false;
	private int mPosition = 0, mOffset = 0;
	
	private List<Data> mList = new ArrayList<Data>();
	
	public ListViewFragment()
    {
	    super();
    }

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
		mListView = getListView();
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
			mListView.setOnScrollListener(this);
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
		return new AsyncLoader<Data>(getActivity(), mList);
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

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState)
    {
    }

    @Override
    public void onScroll(AbsListView listView, int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {
        if (!(getActivity() instanceof OnScrollUpDownListener))
        {
            return;
        }
        
        View view = mListView.getChildAt(0);
        int offset = (view == null) ? 0 : view.getTop();

        if (mPosition == firstVisibleItem && mOffset == offset)
        {
            return;
        }

        if (mPosition < firstVisibleItem && mOffset != offset || (mPosition == firstVisibleItem && mOffset > offset))
        {
            ((OnScrollUpDownListener) getActivity()).onScrollDown();
        }
        else
        {
            ((OnScrollUpDownListener) getActivity()).onScrollUp();
        }
        mPosition = firstVisibleItem;
        mOffset = offset;
    }
}