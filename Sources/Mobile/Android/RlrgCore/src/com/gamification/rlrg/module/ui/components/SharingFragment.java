package com.gamification.rlrg.module.ui.components;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.ui.StartActivity;

final class SharingFragment extends Fragment
{
	public static final Class<?> CLASS = SharingFragment.class;

	private StartActivity mActivity;

	static SharingFragment newInstance()
	{
		return new SharingFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container == null)
		{
			return null;
		}
		View root = inflater.inflate(R.layout.fragment_sharing, container, false);
		root.findViewById(R.id.btn_facebook).setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO SharingManager.getInstance().shareOnFacebook(caption,
				// text, facebookShareLink);
				mActivity.showDebugToast("shareOnFacebook");
			}
		});
		root.findViewById(R.id.btn_twitter).setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO SharingManager.getInstance().shareOnTwitter(message);
				mActivity.showDebugToast("shareOnTwitter");
			}
		});
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
		mActivity.hideActionBarButtonRight();
	}
}
