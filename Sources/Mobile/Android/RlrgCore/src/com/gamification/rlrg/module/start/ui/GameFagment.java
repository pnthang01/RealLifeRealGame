package com.gamification.rlrg.module.start.ui;

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

import com.gamification.rlrg.gen.R;

public class GameFagment extends ListFragment implements LoaderManager.LoaderCallbacks<List<String>>
{
private ListViewAdapter listViewAdapter;
	
	protected List<String> list;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.list, container, false);
	}
	
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		if (getActivity() != null)
		{
			listViewAdapter = new ListViewAdapter();
			setListAdapter(listViewAdapter);
			
			getLoaderManager().initLoader(0, null, this);
		}
	}
	
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		
	}
	
	public Loader<List<String>> onCreateLoader(int arg0, Bundle arg1)
	{
		if (getActivity() != null)
		{
			return new Loader<List<String>>(getActivity());
		}
		else
		{
			return null;
		}
	}
	
	public void onLoadFinished(Loader<List<String>> arg0, List<String> data)
	{
		listViewAdapter.setData(data);
	}
	
	public void onLoaderReset(Loader<List<String>> arg0)
	{
		listViewAdapter.setData(null);
	}
	
	private class ListViewAdapter extends ArrayAdapter<String>
	{
		
		public ListViewAdapter()
		{
			super(getActivity(), android.R.layout.simple_list_item_2);
		}
		
		public void setData(List<String> spots)
		{
			clear();
			if (spots != null)
			{
				for (String spot : spots)
				{
					add(spot);
				}
			}
		}
		
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View view = convertView;
			if (view == null)
			{
				view = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.navigation_item, parent, false);
			}
			
			((TextView) view).setText(getItem(position));
			
			return view;
		}
	}
}
