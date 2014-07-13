package com.gamification.rlrg.core.components;

import java.util.Arrays;
import java.util.List;

import com.gamification.rlrg.gen.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerFragment extends Fragment implements OnPageChangeListener
{
	public static final int TYPE_NORMAL = 0;
	public static final int TYPE_TITLE_STRIP = 1;
	public static final int TYPE_TAB_STRIP = 2;

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
		int layoutResId = mType == 0 ? R.layout.fragment_view_pager : R.layout.fragment_view_pager_with_tab_strip;
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
