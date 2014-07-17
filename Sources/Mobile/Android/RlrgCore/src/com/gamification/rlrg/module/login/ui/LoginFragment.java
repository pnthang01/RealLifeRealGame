package com.gamification.rlrg.module.login.ui;

import nghiem.app.core.application.DeviceManager;
import nghiem.app.core.utils.LogUtils;

import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.start.ui.StartActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoginFragment extends Fragment implements AnimationListener, OnClickListener
{
	private static final String TAG = LoginFragment.class.getName();

	private StartActivity mActivity;
	private LinearLayout mLayoutLogin;
	private TextView mTxtLogo;
	private EditText mEdtUsername, mEdtPassword;
	private Button mBtnLogin;
	private LayoutParams mLogoParams;

	private int mScreenHeight = 0;

	public static Fragment newInstance()
	{
		return new LoginFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container == null)
		{
			return null;
		}

		mActivity = (StartActivity) getActivity();
		RelativeLayout root = (RelativeLayout) inflater.inflate(R.layout.fragment_login, container, false);
		mLayoutLogin = (LinearLayout) root.findViewById(R.id.layout_login);
		mTxtLogo = (TextView) root.findViewById(R.id.logo);
		mEdtUsername = (EditText) root.findViewById(R.id.edit_username);
		mEdtPassword = (EditText) root.findViewById(R.id.edit_password);
		mBtnLogin = (Button) root.findViewById(R.id.btn_login);

		mScreenHeight = DeviceManager.getInstance().getScreenHeight();
		mLogoParams = mTxtLogo.getLayoutParams();
		mBtnLogin.setOnClickListener(this);
		return root;
	}

	public void onStart()
	{
		super.onStart();

		if (getActivity() != null)
		{
			showLoginForm();
		}
	}

	private void showLoginForm()
	{
		Animation animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.show_login_form);
		animation.setAnimationListener(this);
		mTxtLogo.startAnimation(animation);
	}

	@Override
	public void onAnimationStart(Animation animation)
	{
	}

	@Override
	public void onAnimationRepeat(Animation animation)
	{
		double step = (0.75 / animation.getRepeatCount()) * mScreenHeight;
		mLogoParams.height -= step;
		mTxtLogo.setLayoutParams(mLogoParams);
		LogUtils.log(TAG, "Height: " + mLogoParams.height);
	}

	@Override
	public void onAnimationEnd(Animation animation)
	{
		mLogoParams.height = (int) (mScreenHeight * 0.25f);
		mTxtLogo.setLayoutParams(mLogoParams);
		mLayoutLogin.setVisibility(View.VISIBLE);
	}

	@Override
	public void onClick(View view)
	{
		if (mEdtUsername.getText().toString().equals("") && mEdtPassword.getText().toString().equals(""))
		{
			mActivity.onBtnLoginClick(view);
		}
		else
		{
			mEdtUsername.setText("");
			mEdtPassword.setText("");
			mActivity.toast(R.string.login_failed_message);
		}
	}
}
