package com.gamification.rlrg.core.components;

import java.util.ArrayList;
import java.util.List;

import com.gamification.rlrg.core.asynctask.loader.BaseAsyncTaskLoader;

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

public class ListViewFragment<Data> extends ListFragment implements LoaderManager.LoaderCallbacks<List<Data>>
{
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
				view = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(mItemLayout, parent, false);
			}
			return getListItemView((ListView) parent, view, position);
		}
	}
	
	protected Adapter mAdapter;
	protected Bundle mArguments;
	protected int mListLayout, mItemLayout;
	
	private List<Data> mList = new ArrayList<Data>();
	
	public ListViewFragment(int listLayout, int itemLayout)
	{
		super();
		mListLayout = listLayout;
		mItemLayout = itemLayout;
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
		if (getActivity() != null)
		{
			return new BaseAsyncTaskLoader<Data>(getActivity(), mList);
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public void onLoadFinished(Loader<List<Data>> arg0, List<Data> data)
	{
		mAdapter.setData(data);
	}
	
	@Override
	public void onLoaderReset(Loader<List<Data>> arg0)
	{
		mAdapter.setData(null);
	}
}