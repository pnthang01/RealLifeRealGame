package com.gamification.rlrg.module.ui.components;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

final class ShowRoomFragment extends Fragment
{
	public static ShowRoomFragment newInstance()
	{
		return new ShowRoomFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
	    return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onStart()
	{
	    super.onStart();
	}
}