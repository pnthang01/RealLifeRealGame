package com.gamification.rlrg.core.components;

import java.util.ArrayList;
import java.util.List;

import com.gamification.rlrg.core.asynctask.loader.BaseAsyncTaskLoader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class ListViewFragment<T> extends ListFragment implements LoaderManager.LoaderCallbacks<List<T>>
{
	private class Adapter extends ArrayAdapter<T>
	{
		public Adapter()
		{
			super(getActivity(), mListLayout);
		}
		
		public void setData(List<T> spots)
		{
			clear();
			if (spots != null)
			{
				for (T spot : spots)
				{
					add(spot);
				}
			}
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View view = convertView;
			if (view == null)
			{
				view = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(mItemLayout, parent, false);
			}
			return getListItemView((ListView) parent, view, position);
		}
	}
	
	protected Adapter mAdapter;
	protected List<T> mList;
	protected Bundle mArguments;
	protected int mListLayout, mItemLayout;
	
	public ListViewFragment(int listLayout, int itemLayout)
	{
		super();
		mListLayout = listLayout;
		mItemLayout = itemLayout;
	}
	
	public void setData(List<T> list)
	{
		mList = list;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mList = new ArrayList<T>();
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
	public Loader<List<T>> onCreateLoader(int id, Bundle args)
	{
		if (getActivity() != null)
		{
			return new BaseAsyncTaskLoader<T>(getActivity(), mList);
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public void onLoadFinished(Loader<List<T>> arg0, List<T> data)
	{
		mAdapter.setData(data);
	}
	
	@Override
	public void onLoaderReset(Loader<List<T>> arg0)
	{
		mAdapter.setData(null);
	}
}