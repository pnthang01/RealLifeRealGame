package mobile.digizone.animation;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

// Bộ điều khiển thu phóng cho đối tượng giao diện
public class ExpandableViewController extends Animation implements AnimationListener
{
	
	protected View view;
	protected int from, to, duration;
	protected boolean isExpanded, isVertical;
	
	public ExpandableViewController(View view, int from, int to, int duration, boolean isVertical, boolean isExpand)
	{
		this.isExpanded = isExpand;
		this.isVertical = isVertical;
		this.view = view;
		this.from = from;
		this.to = to;
		this.duration = duration;
		
		setDuration(1);
		setRepeatCount(duration);
		setFillAfter(false);
		setInterpolator(new AccelerateInterpolator());
		setAnimationListener(this);
	}
	
	// Bắt đầu hiệu ứng
	public void onAnimationStart(Animation anim)
	{
		LayoutParams lyp = view.getLayoutParams();
		if (isVertical)
		{
			lyp.height = isExpanded ? to : from;
		}
		else
		{
			lyp.width = isExpanded ? to : from;
		}
		view.setLayoutParams(lyp);
		lyp = null;
	}
	
	// Thu/phóng dần dần
	public void onAnimationRepeat(Animation anim)
	{
		LayoutParams lyp = view.getLayoutParams();
		if (isVertical)
		{
			lyp.height += isExpanded ? (from - to) / duration : (to - from) / duration;
		}
		else
		{
			lyp.width += isExpanded ? (from - to) / duration : (to - from) / duration;
		}
		view.setLayoutParams(lyp);
		lyp = null;
	}
	
	// Kết thúc hiệu ứng
	public void onAnimationEnd(Animation animation)
	{
		LayoutParams lyp = view.getLayoutParams();
		if (isVertical)
		{
			lyp.height = isExpanded ? from : to;
		}
		else
		{
			lyp.width = isExpanded ? from : to;
		}
		view.setLayoutParams(lyp);
		
		// Hủy dữ liệu, trả ram
		view = null;
		lyp = null;
		System.gc();
		cancel();
	}
}