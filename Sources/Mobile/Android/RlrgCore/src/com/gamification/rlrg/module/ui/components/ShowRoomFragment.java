package com.gamification.rlrg.module.ui.components;

import lvnghiem.app.core.components.ExpandCollapseView;

import com.gamification.rlrg.application.DataPreferencesManager;
import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.ui.StartActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

final class ShowRoomFragment extends Fragment implements OnClickListener
{
	private StartActivity mActivity;
	private ExpandCollapseView mViewBadges;
	private Button mBtnShow;
	private ProgressBar mPgbAngelDevil;
	private boolean mIsExpand = false;
	
	public static ShowRoomFragment newInstance()
	{
		return new ShowRoomFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container == null)
		{
			return null;
		}
		View root = inflater.inflate(R.layout.fragment_showroom, container, false);
		mBtnShow = (Button) root.findViewById(R.id.btn_show);
		mViewBadges = (ExpandCollapseView) root.findViewById(R.id.layout_expand);
		mPgbAngelDevil = (ProgressBar) root.findViewById(R.id.pgb_angel_devil);
		return root;
	}
	
	@Override
	public void onStart()
	{
	    super.onStart();
		mActivity = (StartActivity) getActivity();
		if (mActivity == null)
		{
			return;
		}
		mBtnShow.setOnClickListener(this);
		mPgbAngelDevil.setProgress(getResources().getInteger(R.integer.settings_rules_point_max) + DataPreferencesManager.getInstance().getUserPoint());
		getChildFragmentManager().beginTransaction().addToBackStack(null).add(R.id.layout_expand, BadgesFragment.newInstance()).commit();
		
	}
	
	@Override
	public void onClick(View v)
	{
		if (!mIsExpand)
		{
			mIsExpand = true;
			mBtnShow.setText(R.string.action_show_less);
			mPgbAngelDevil.setVisibility(View.GONE);
		}
		else
		{
			mIsExpand = false;
			mBtnShow.setText(R.string.action_show_more);
			mPgbAngelDevil.setVisibility(View.VISIBLE);
		}
		mViewBadges.toggle();
	}
}