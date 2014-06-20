package com.gamification.rlrg.core.fragment;

import java.util.List;

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
import android.widget.TextView;

import com.gamification.rlrg.core.asynctask.GameAsynctaskLoader;
import com.gamification.rlrg.core.data.GameData;
import com.gamification.rlrg.gen.R;

public class GameListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<GameData>>
{	
	private class Adapter extends ArrayAdapter<GameData>
	{
		public Adapter()
		{
			super(getActivity(), android.R.layout.simple_list_item_2);
		}
		
		public void setData(List<GameData> spots)
		{
			clear();
			if (spots != null)
			{
				for (GameData spot : spots)
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
				view = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_item_navigation, parent, false);
			}
			
			GameData item = getItem(position);
			
			((TextView) view).setText(item.getTitle());
			
			return view;
		}
	}
	
	private Adapter mAdapter;
	
	protected List<GameData> list;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.list_view, container, false);
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
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		//GameData item = (GameData) getListAdapter().getItem(position);
	}
	
	@Override
	public Loader<List<GameData>> onCreateLoader(int arg0, Bundle arg1)
	{
		if (getActivity() != null)
		{
			return new GameAsynctaskLoader(getActivity(), list);
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public void onLoadFinished(Loader<List<GameData>> arg0, List<GameData> data)
	{
		mAdapter.setData(data);
	}
	
	@Override
	public void onLoaderReset(Loader<List<GameData>> arg0)
	{
		mAdapter.setData(null);
	}
}