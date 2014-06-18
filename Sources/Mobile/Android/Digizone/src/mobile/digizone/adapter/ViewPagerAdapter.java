package mobile.digizone.adapter;

import java.util.ArrayList;
import java.util.List;

import mobile.digizone.D;
import mobile.digizone.activity.Highlight;
import mobile.digizone.activity.Nearby;
import mobile.digizone.activity.Newbie;
import mobile.digizone.loader.DataLoader;
import mobile.digizone.parser.SpotParser;
import mobile.digizone.parser.SpotParser.SpotHandler;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

// Bộ chuyển đổi cho View pager
public class ViewPagerAdapter extends FragmentStatePagerAdapter
{
	
	public ViewPagerAdapter(Context ctx, FragmentManager fm)
	{
		super(fm);
	}
	
	// View pager có bao nhiêu tab
	public int getCount()
	{
		return 4;
	}
	
	// Gắn tựa đề cho từng tab
	public CharSequence getPageTitle(int position)
	{
		String title = "";
		switch (position)
		{
			case 0:
				title = "QUÁN CAFE NỔI BẬT";
				break;
			case 1:
				title = "QUÁN CAFE MỚI THAM GIA";
				break;
			case 2:
				title = "QUÁN CAFE GẦN ĐÂY";
				break;
			default:
				return "TRANG RỖNG";
		}
		return title;
	}
	
	// Nhúng fragment vào từng tab
	public Fragment getItem(int position)
	{
		
		List<SpotHandler> highlight, newbie, toprated, recommend, master = new ArrayList<SpotHandler>();
		try
		{
			highlight = new SpotParser(new DataLoader(D.method.get_highlight).execute().get()).get();
			
			/*
			 * String data = new DataParser(D.method.get_highlight).execute(//
			 * Parameter nếu có).get(); List<SpotHandler> highlight = new
			 * SpotHandler(data).get();
			 */
			newbie = new SpotParser(new DataLoader(D.method.get_newbie).execute().get()).get();
			recommend = new SpotParser(new DataLoader(D.method.get_random).execute().get()).get();
			toprated = new SpotParser(new DataLoader(D.method.get_toprated).execute().get()).get();
			
			master.add(highlight.get(1));
			
			for (int i = 1; i <= 5; i++)
			{
				master.add(newbie.get(i));
			}
			
			for (SpotHandler spot : toprated)
			{
				master.add(spot);
			}
			
			master.add(recommend.get(1));
			master.add(recommend.get(2));
		}
		catch (Exception e)
		{
			highlight = newbie = new ArrayList<SpotHandler>();
		}
		
		switch (position)
		{
			case 0:
				return Highlight.newInstance(highlight);
			case 1:
				return Newbie.newInstance(newbie);
			case 2:
				return Nearby.newInstance();
			default:
				return new Fragment();
		}
	}
}