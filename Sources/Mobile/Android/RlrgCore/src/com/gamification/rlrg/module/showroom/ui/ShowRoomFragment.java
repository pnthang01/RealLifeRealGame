package com.gamification.rlrg.module.showroom.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gamification.rlrg.gen.R;

public class ShowRoomFragment extends Fragment
{
	public static Fragment newInstance()
	{
		return new ShowRoomFragment();
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_showroom, container, false);
	}
	
	public void onStart()
	{
		super.onStart();
		
		if (getActivity() != null)
		{
			// TODO show medal list
		}
	}
}
