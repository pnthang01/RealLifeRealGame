package nghiem.app.core.components;

import java.util.Arrays;
import java.util.List;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import nghiem.app.gen.R;

public class ViewPagerFragment extends Fragment implements OnPageChangeListener
{
	public static final int TYPE_NORMAL = 0;
	public static final int TYPE_TITLE_STRIP = 1;
	public static final int TYPE_TAB_STRIP = 2;

	private static class ParallaxTransformer implements ViewPager.PageTransformer
	{
		/**
		 * Both sides, each side = 0.17
		 */
		private static final float IMAGE_SIZE_WIDTH_OUT_VIEW_PERCENT = 0.34f;

		private int mImageViewResId = -1;
		private ImageView mImageView = null;

		public ParallaxTransformer(int imageViewResId)
		{
			super();
			mImageViewResId = imageViewResId;
		}

		public ParallaxTransformer(ImageView imageView)
		{
			super();
			mImageView = imageView;
		}

		@Override
		public void transformPage(View root, float position)
		{
			if (mImageView == null)
			{
				mImageView = (ImageView) root.findViewById(mImageViewResId);
			}

			Matrix matrix = new Matrix();
			matrix.reset();

			float viewWidth = mImageView.getWidth();
			float viewHeight = mImageView.getHeight();

			float imageWidth = mImageView.getDrawable().getIntrinsicWidth();
			float imageHeight = mImageView.getDrawable().getIntrinsicHeight();

			float imageWidthInsideView = imageWidth * (1 - IMAGE_SIZE_WIDTH_OUT_VIEW_PERCENT);
			float newImageWidth = viewWidth / (1 - IMAGE_SIZE_WIDTH_OUT_VIEW_PERCENT);

			float scaleX = viewWidth / imageWidthInsideView;
			float scaleY = viewHeight / imageHeight;

			if (scaleX < scaleY)
			{
				matrix.setScale(scaleY, scaleY);
				newImageWidth = imageWidth * scaleY;
			}
			else
			{
				matrix.setScale(scaleX, scaleX);
			}

			float dx = (position + 1) * newImageWidth * IMAGE_SIZE_WIDTH_OUT_VIEW_PERCENT / -2;

			// Keep the height
			matrix.preTranslate(dx, 0);

			mImageView.setScaleType(ImageView.ScaleType.MATRIX);
			mImageView.setImageMatrix(matrix);
		}
	}

	private class Adapter extends FragmentStatePagerAdapter
	{
		public Adapter(FragmentManager fragmentManager)
		{
			super(fragmentManager);
		}

		@Override
		public int getCount()
		{
			return mPages.size();
		}

		@Override
		public CharSequence getPageTitle(int position)
		{
			return mTitles.get(position);
		}

		@Override
		public Fragment getItem(int position)
		{
			return mPages.get(position);
		}
	}

	protected List<Fragment> mPages;
	protected List<CharSequence> mTitles;

	private ViewPager mViewPager;
	private Adapter mAdapter;
	private int mType = 0;
	
	public ViewPagerFragment()
    {
        super();
    }

	public ViewPagerFragment(List<Fragment> pages, List<CharSequence> titles, int type)
	{
		super();
		mPages = pages;
		mTitles = titles;
		mType = type;
	}

	public ViewPagerFragment(List<Fragment> pages, List<CharSequence> titles)
	{
		this(pages, titles, TYPE_NORMAL);
	}

	public ViewPagerFragment(Fragment[] pages, CharSequence[] titles)
	{
		this(Arrays.asList(pages), Arrays.asList(titles));
	}

	public ViewPagerFragment(Fragment[] pages, List<CharSequence> titles)
	{
		this(Arrays.asList(pages), titles);
	}

	public ViewPagerFragment(List<Fragment> pages, CharSequence[] titles)
	{
		this(pages, Arrays.asList(titles));
	}

	public ViewPagerFragment(Fragment[] pages, CharSequence[] titles, int type)
	{
		this(Arrays.asList(pages), Arrays.asList(titles), type);
	}

	public ViewPagerFragment(Fragment[] pages, List<CharSequence> titles, int type)
	{
		this(Arrays.asList(pages), titles, type);
	}

	public ViewPagerFragment(List<Fragment> pages, CharSequence[] titles, int type)
	{
		this(pages, Arrays.asList(titles), type);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container == null)
		{
			return null;
		}

		int layoutResId;
		switch (mType)
		{
			case TYPE_TITLE_STRIP:
				layoutResId = R.layout.fragment_view_pager_with_tab_strip;
				break;
			case TYPE_TAB_STRIP:
				layoutResId = R.layout.fragment_view_pager_with_tab_strip;
				break;
			default:
				layoutResId = R.layout.fragment_view_pager;
				break;
		}

		mAdapter = new Adapter(getActivity().getSupportFragmentManager());
		mViewPager = (ViewPager) inflater.inflate(layoutResId, container, false);
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOnPageChangeListener(this);
		mViewPager.setCurrentItem(0);
		return mViewPager;
	}

	public void setCurrentPage(int position)
	{
		mViewPager.setCurrentItem(position);
	}

	public void setParallax(int backgroundResId)
	{
		mViewPager.setPageTransformer(false, new ParallaxTransformer(backgroundResId));
	}

	public void setParallax(ImageView background)
	{
		mViewPager.setPageTransformer(false, new ParallaxTransformer(background));
	}

	@Override
	public void onPageScrollStateChanged(int state)
	{
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
	{
	}

	@Override
	public void onPageSelected(int postition)
	{
	}
}
