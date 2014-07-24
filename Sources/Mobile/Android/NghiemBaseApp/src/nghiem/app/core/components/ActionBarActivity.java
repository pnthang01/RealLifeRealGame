package nghiem.app.core.components;

import nghiem.app.gen.R;
import nghiem.app.core.components.ListViewFragment.OnScrollUpDownListener;
import nghiem.app.core.utils.LogUtils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class ActionBarActivity extends NghiemActivity implements OnScrollUpDownListener
{
	private LinearLayout mActionBar;
	private FrameLayout mMainView;
	private TextView mActionBarTitle;
	private Button mBtnActionBarRightText;
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
		mBtnActionBarRightText = (Button) findViewById(R.id.actionbar_btn_right_text);

		setActionBarOverLay(false);
	}

	@Override
	public void setContentView(int layout)
	{
		LogUtils.log(TAG, "set layout " + layout + " to main view");
		inflate(layout, mMainView);
	}

	public void showActionBar()
	{
		LogUtils.log(TAG, "show action bar");
		if (mActionBar.getVisibility() == View.VISIBLE)
		{
			return;
		}
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.show_down);
		animation.setAnimationListener(new AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation)
			{
			}

			@Override
			public void onAnimationRepeat(Animation animation)
			{
			}

			@Override
			public void onAnimationEnd(Animation animation)
			{
				mActionBar.setVisibility(View.VISIBLE);
			}
		});
		mActionBar.startAnimation(animation);
	}

	public void hideActionBar()
	{
		LogUtils.log(TAG, "hide action bar");
		if (mActionBar.getVisibility() == View.GONE)
		{
			return;
		}
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.hide_up);
		animation.setAnimationListener(new AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation)
			{
			}

			@Override
			public void onAnimationRepeat(Animation animation)
			{
			}

			@Override
			public void onAnimationEnd(Animation animation)
			{
				mActionBar.setVisibility(View.GONE);
			}
		});
		mActionBar.startAnimation(animation);
	}

	@Override
	public void onScrollUp()
	{
		showActionBar();
	}

	@Override
	public void onScrollDown()
	{
		hideActionBar();
	}

	public void setActionBarOverLay(boolean isOverlay)
	{
		LogUtils.log(TAG, "set action bar overlay: " + isOverlay);
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

	public void setActionBarTitle(int text)
	{
		LogUtils.log(TAG, "set action bar title id: " + text);
		mActionBarTitle.setText(text);
	}

	public void setActionBarTitle(CharSequence text)
	{
		LogUtils.log(TAG, "set action bar title: " + text);
		mActionBarTitle.setText(text);
	}

	public void setBtnActionBarLeft(Drawable icon, View.OnClickListener callback)
	{
		setActionBarButton(mBtnActionBarLeft, icon, callback);
	}

	public void setBtnActionBarRightOne(Drawable icon, View.OnClickListener callback)
	{
		setActionBarButton(mBtnActionBarRightOne, icon, callback);
		if (mBtnActionBarRightText.getVisibility() == View.VISIBLE)
		{
			mBtnActionBarRightText.setVisibility(View.GONE);
		}
	}

	public void setBtnActionBarRightTwo(Drawable icon, View.OnClickListener callback)
	{
		setActionBarButton(mBtnActionBarRightTwo, icon, callback);
		if (mBtnActionBarRightText.getVisibility() == View.VISIBLE)
		{
			mBtnActionBarRightText.setVisibility(View.GONE);
		}
	}

	public void setBtnActionBarRightText(int title, View.OnClickListener callback)
	{
		if (title > 0)
		{
			setBtnActionBarRightText(getString(title), callback);
		}
	}

	public void setBtnActionBarRightText(CharSequence title, View.OnClickListener callback)
	{
		if (title != null)
		{
			mBtnActionBarRightText.setText(title);
			mBtnActionBarRightText.setVisibility(View.VISIBLE);
			mBtnActionBarRightText.setOnClickListener(callback);
			LogUtils.log(TAG, "set action bar button " + mBtnActionBarRightText.toString() + "with title " + title);
		}
		if (mBtnActionBarRightOne.getVisibility() == View.VISIBLE)
		{
			mBtnActionBarRightOne.setVisibility(View.GONE);
		}
		if (mBtnActionBarRightTwo.getVisibility() == View.VISIBLE)
		{
			mBtnActionBarRightTwo.setVisibility(View.GONE);
		}
	}

	public void setBtnActionBarLeft(int icon, View.OnClickListener callback)
	{
		setBtnActionBarLeft(getResources().getDrawable(icon), callback);
	}

	public void setBtnActionBarRightOne(int icon, View.OnClickListener callback)
	{
		setBtnActionBarRightOne(getResources().getDrawable(icon), callback);
	}

	public void setBtnActionBarRightTwo(int icon, View.OnClickListener callback)
	{
		setBtnActionBarRightTwo(getResources().getDrawable(icon), callback);
	}

	public void setBtnActionBarLeft(Bitmap icon, View.OnClickListener callback)
	{
		setBtnActionBarLeft(new BitmapDrawable(getResources(), icon), callback);
	}

	public void setBtnActionBarRightOne(Bitmap icon, View.OnClickListener callback)
	{
		setBtnActionBarRightOne(new BitmapDrawable(getResources(), icon), callback);
	}

	public void setBtnActionBarRightTwo(Bitmap icon, View.OnClickListener callback)
	{
		setBtnActionBarRightTwo(new BitmapDrawable(getResources(), icon), callback);
	}

	private void setActionBarButton(ImageButton view, Drawable icon, View.OnClickListener callback)
	{
		if (icon != null)
		{
			view.setImageDrawable(icon);
			view.setVisibility(View.VISIBLE);
			view.setOnClickListener(callback);
			LogUtils.log(TAG, "set action bar button " + view.toString() + "with icon " + icon.toString());
		}
	}

	public void hideActionBarButtonRight()
	{
		mBtnActionBarRightOne.setVisibility(View.GONE);
		mBtnActionBarRightTwo.setVisibility(View.GONE);
		mBtnActionBarRightText.setVisibility(View.GONE);
	}
}
