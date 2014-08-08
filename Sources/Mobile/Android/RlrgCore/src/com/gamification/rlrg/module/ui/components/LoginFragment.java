package com.gamification.rlrg.module.ui.components;

import lvnghiem.app.core.animation.SlideAnimation;
import lvnghiem.app.core.application.DeviceManager;
import lvnghiem.app.core.components.NghiemFragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gamification.rlrg.application.DataPreferencesManager;
import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.ui.StartActivity;
import com.gamification.rlrg.settings.Settings;

final class LoginFragment extends NghiemFragment implements OnClickListener, Runnable
{
	private StartActivity mActivity;
	private LinearLayout mLayoutLogin;
	private TextView mTxtLogo;
	private EditText mEdtUsername, mEdtPassword;
	private Button mBtnLogin;
	private CheckBox mCkbRemember;

	private DataPreferencesManager mPreferencesManager;

	private String mLastPassword;

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

		mPreferencesManager = DataPreferencesManager.getInstance();
		String lastUsername = mPreferencesManager.getUsername();
		mLastPassword = mPreferencesManager.getPassword();

		mActivity = (StartActivity) getActivity();
		RelativeLayout root = (RelativeLayout) inflater.inflate(R.layout.fragment_login, container, false);
		mLayoutLogin = (LinearLayout) root.findViewById(R.id.layout_login);
		mTxtLogo = (TextView) root.findViewById(R.id.logo);
		mEdtUsername = (EditText) root.findViewById(R.id.edit_username);
		mEdtPassword = (EditText) root.findViewById(R.id.edit_password);
		mBtnLogin = (Button) root.findViewById(R.id.btn_login);
		mCkbRemember = (CheckBox) root.findViewById(R.id.ckb_remember);

		mEdtUsername.setText(lastUsername);
		mEdtPassword.setText(TextUtils.isEmpty(mLastPassword) ? "" : Settings.PASSWORD_COVER);
		mBtnLogin.setOnClickListener(this);
		mCkbRemember.setChecked(mPreferencesManager.getRememberPassword());
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
		new Handler().postDelayed(this, 2000);
	}

	@Override
	public void run()
	{
		int screenHeight = DeviceManager.getInstance().getScreenHeight();
		// Animation animation =
		// AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
		// R.anim.show_login_form);
		Animation animation = new SlideAnimation(mTxtLogo, screenHeight / 4, screenHeight, 40, true, true)
		{
			@Override
			public void onAnimationEnd(Animation animation)
			{
				super.onAnimationEnd(animation);
				mLayoutLogin.setVisibility(View.VISIBLE);
			}
		};
		mTxtLogo.startAnimation(animation);
	}

	@Override
	public void onClick(View view)
	{
		String username = mEdtUsername.getText().toString();
		String password = mEdtPassword.getText().toString();
		if (password.equals(Settings.PASSWORD_COVER))
		{
			password = mLastPassword;
		}
		mPreferencesManager.saveRememberPassword(mCkbRemember.isChecked());
		login(username, password);
	}

	private void login(String username, String password)
	{
		if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password))
		{
			mActivity.onLoginSuccess();
		}
		else
		{
			mEdtUsername.setText("");
			mEdtPassword.setText("");
			mActivity.toast(R.string.message_login_failed);
		}
	}
}
