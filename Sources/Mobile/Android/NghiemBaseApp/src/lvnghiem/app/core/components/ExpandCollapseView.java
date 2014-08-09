package lvnghiem.app.core.components;

import lvnghiem.app.core.animation.SlideAnimation;
import nghiem.app.gen.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class ExpandCollapseView extends FrameLayout
{
	private int mDuration, mMin, mMax;
	private boolean mVertical, mIsExpanded = false;

	public int getDuration()
	{
		return mDuration;
	}

	public void setDuration(int duration)
	{
		mDuration = duration;
	}

	public int getMin()
	{
		return mMin;
	}

	public void setMin(int min)
	{
		mMin = min;
	}

	public int getMax()
	{
		return mMax;
	}

	public void setMax(int max)
	{
		mMax = max;
	}

	public boolean isExpanded()
	{
		return mIsExpanded;
	}

	public boolean isVertical()
	{
		return mVertical;
	}

	public void setVertical(boolean vertical)
	{
		mVertical = vertical;
	}

	public ExpandCollapseView(Context context)
	{
		super(context);
		init(context);
	}

	public ExpandCollapseView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context.obtainStyledAttributes(attrs, R.styleable.expand_collapse));
		init(context);

	}

	public ExpandCollapseView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		init(context.obtainStyledAttributes(attrs, R.styleable.expand_collapse, defStyleAttr, 0));
		init(context);
	}

	private void init(Context context)
	{

	}

	private void init(TypedArray typedArray)
	{
		mDuration = typedArray.getInt(R.styleable.expand_collapse_duration, 1);
		mMin = typedArray.getDimensionPixelSize(R.styleable.expand_collapse_min, 0);
		mMax = typedArray.getDimensionPixelSize(R.styleable.expand_collapse_max, 0);
		mVertical = typedArray.getBoolean(R.styleable.expand_collapse_isVertical, false);
	}

	public void toggle()
	{
		clearAnimation();
		startAnimation(new SlideAnimation(this, mMin, mMax, mDuration, mVertical, mIsExpanded));
		mIsExpanded = !mIsExpanded;
	}
}
