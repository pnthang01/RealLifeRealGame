package com.gamification.rlrg.module.ui.components;

import lvnghiem.app.core.application.SharingManager;
import lvnghiem.app.core.components.NghiemFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.gamification.rlrg.application.RlrgApp;
import com.gamification.rlrg.data.entity.Achievement;
import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.ui.StartActivity;

final class SharingFragment extends NghiemFragment
{
	private StartActivity mActivity;
	private String mBadgeNames = "";

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
				String subject = RlrgApp.getInstance().getString(R.string.title_share_facebook);
				String message = String.format(RlrgApp.getInstance().getString(R.string.message_share_facebook), mBadgeNames);
				SharingManager.getInstance().shareOnFacebook(subject, message);
			}
		});
		root.findViewById(R.id.btn_twitter).setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SharingManager.getInstance().shareOnTwitter(String.format(RlrgApp.getInstance().getString(R.string.message_share_twitter), mBadgeNames));
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
		for (Achievement achievement : RlrgApp.getInstance().getAchievements().getData().getElements())
		{
			mBadgeNames += ", " + achievement.getBadge().getName();
		}
		mBadgeNames.replaceFirst(", ", "");
	}
}
