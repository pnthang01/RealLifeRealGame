package lvnghiem.app.core.animation;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class SlideAnimation extends Animation implements AnimationListener
{
	protected View mView;
	protected LayoutParams mParams;
	protected int mMin, mMax, mRepeat;
	protected boolean mIsExpanded, mIsVertical;

	public SlideAnimation(View view, int min, int max, int reapeat, boolean isVertical, boolean isExpand)
	{
		mView = view;
		mParams = view.getLayoutParams();

		mMin = min;
		mMax = max;

		mIsExpanded = isExpand;
		mIsVertical = isVertical;

		mRepeat = reapeat;

		setDuration(1);
		setRepeatCount(reapeat);

		setFillAfter(false);
		setInterpolator(new AccelerateInterpolator());
		setAnimationListener(this);
	}

	@Override
	public void onAnimationStart(Animation anim)
	{
		if (mIsVertical)
		{
			mParams.height = mIsExpanded ? mMax : mMin;
		}
		else
		{
			mParams.width = mIsExpanded ? mMax : mMin;
		}
		mView.setLayoutParams(mParams);
	}

	@Override
	public void onAnimationRepeat(Animation anim)
	{
		if (mIsVertical)
		{
			mParams.height += mIsExpanded ? (mMin - mMax) / mRepeat : (mMax - mMin) / mRepeat;
		}
		else
		{
			mParams.width += mIsExpanded ? (mMin - mMax) / mRepeat : (mMax - mMin) / mRepeat;
		}
		mView.setLayoutParams(mParams);
	}

	@Override
	public void onAnimationEnd(Animation animation)
	{
		if (mIsVertical)
		{
			mParams.height = mIsExpanded ? mMin : mMax;
		}
		else
		{
			mParams.width = mIsExpanded ? mMin : mMax;
		}
		mView.setLayoutParams(mParams);
		cancel();
	}
}