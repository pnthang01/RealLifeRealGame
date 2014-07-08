package com.gamification.rlrg.core.components;

import com.gamification.rlrg.gen.R;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class ActionBarActivity extends BaseActivity
{
	private LinearLayout mActionBar;
	private FrameLayout mMainView;
	private TextView mActionBarTitle;
	private ImageButton mBtnActionBarLeft, mBtnActionBarRightOne, mBtnActionBarRightTwo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_with_actionbar);
		
		mActionBar = (LinearLayout) findViewById(R.id.actionbar);
		mMainView = (FrameLayout) findViewById(R.id.main_view);
		mActionBarTitle = (TextView) findViewById(R.id.actionbar_title);
		mBtnActionBarLeft = (ImageButton) findViewById(R.id.actionbar_btn_left);
		mBtnActionBarRightOne = (ImageButton) findViewById(R.id.actionbar_btn_right_1);
		mBtnActionBarRightTwo = (ImageButton) findViewById(R.id.actionbar_btn_right_2);
		
		setActionBarOverLay(false);
	}
	
	@Override
	public void setContentView(int layout)
	{
		log("set layout " + layout + " to main view");
		inflate(layout, mMainView);
	}
	
	protected void showActionBar()
	{
		log("show action bar");
		mActionBar.setVisibility(View.VISIBLE);
	}
	
	protected void hideActionBar()
	{
		log("hide action bar");
		mActionBar.setVisibility(View.GONE);
	}
	
	protected void setActionBarOverLay(boolean isOverlay)
	{
		log("set action bar overlay: " + isOverlay);
		LayoutParams params = (LayoutParams) mMainView.getLayoutParams();
		if (isOverlay)
		{
			params.topMargin = 0;
			mActionBar.setBackgroundColor(getResources().getColor(R.color.background_transparent));
		}
		else
		{
			params.topMargin = getResources().getDimensionPixelSize(R.dimen.actionbar_height);
			mActionBar.setBackgroundColor(getResources().getColor(R.color.background_solid));
		}
		mMainView.setLayoutParams(params);
	}
	
	protected void setActionBarTitle(int text)
	{
		log("set action bar title id: " + text);
		mActionBarTitle.setText(text);
	}
	
	protected void setActionBarTitle(CharSequence text)
	{
		log("set action bar title: " + text);
		mActionBarTitle.setText(text);
	}
	
	protected void setBtnActionBarLeft(Drawable icon, View.OnClickListener callback)
	{
		setActionBarButton(mBtnActionBarLeft, icon, callback);
	}
	
	protected void setBtnActionBarRightOne(Drawable icon, View.OnClickListener callback)
	{
		setActionBarButton(mBtnActionBarRightOne, icon, callback);
	}
	
	protected void setBtnActionBarRightTwo(Drawable icon, View.OnClickListener callback)
	{
		setActionBarButton(mBtnActionBarRightTwo, icon, callback);
	}
	
	protected void setBtnActionBarLeft(int icon, View.OnClickListener callback)
	{
		setBtnActionBarLeft(getResources().getDrawable(icon), callback);
	}
	
	protected void setBtnActionBarRightOne(int icon, View.OnClickListener callback)
	{
		setBtnActionBarRightOne(getResources().getDrawable(icon), callback);
	}
	
	protected void setBtnActionBarRightTwo(int icon, View.OnClickListener callback)
	{
		setBtnActionBarRightTwo(getResources().getDrawable(icon), callback);
	}
	
	protected void setBtnActionBarLeft(Bitmap icon, View.OnClickListener callback)
	{
		setBtnActionBarLeft(new BitmapDrawable(getResources(), icon), callback);
	}
	
	protected void setBtnActionBarRightOne(Bitmap icon, View.OnClickListener callback)
	{
		setBtnActionBarRightOne(new BitmapDrawable(getResources(), icon), callback);
	}
	
	protected void setBtnActionBarRightTwo(Bitmap icon, View.OnClickListener callback)
	{
		setBtnActionBarRightTwo(new BitmapDrawable(getResources(), icon), callback);
	}
	
	private void setActionBarButton(ImageButton view, Drawable icon, View.OnClickListener callback)
	{
		log("set action bar button " + view.toString() + "with icon " + icon.toString());
		if (icon != null)
		{
			view.setImageDrawable(icon);
			view.setVisibility(View.VISIBLE);
			view.setOnClickListener(callback);
		}
	}
}