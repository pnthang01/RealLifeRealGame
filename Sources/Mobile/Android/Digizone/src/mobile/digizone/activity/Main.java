package mobile.digizone.activity;

import mobile.digizone.D;
import mobile.digizone.R;
import mobile.digizone.adapter.ViewPagerAdapter;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

// Activity duy nhất của app, ứng dụng công nghệ Fragment
// Lưu ý: Chỉ có thể nhúng fragment vào FrameLayout, các loại ViewGroup khác
// không nhúng được
public class Main extends FragmentActivity
{
	
	private ViewPager viewPager;
	private ViewPagerAdapter viewPagerAdapter;
	
	// Bắt đầu tạo Activity
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		// Gắn giao diện trong res/layout/main.xml vào activity
		setContentView(R.layout.main);
		
		// Ẩn cover của app sau thời gian đã định
		// ((RelativeLayout) findViewById(R.id.cover)).startAnimation(new
		// Cover());
		findViewById(R.id.cover).setVisibility(View.INVISIBLE);
		
		// Kiểm tra xem có Internet ko, nếu không bật Wifi setting lên
		isNetworkEnabled();
	}
	
	public void setContentView(int layoutResID)
	{
		super.setContentView(layoutResID);
		
		// Nhúng layout trong header.xml vào llHeader
		((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.header, (LinearLayout) findViewById(R.id.llHeader));
		
		// Bắt đối tượng View Pager và gắn adapter
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPagerAdapter = new ViewPagerAdapter(getApplicationContext(), getSupportFragmentManager());
		viewPager.setAdapter(viewPagerAdapter);
		
		// Tùy chỉnh style cho View Pager
		viewPager.setPageMargin(D.pager.page_margin);
		viewPager.setPageMarginDrawable(R.color.black);
		viewPager.setCurrentItem(0);
	}
	
	// Reset lại activity khi có kết quả trả về từ màn hình wifi
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		
		// Nếu nhận được kết quả từ Wifi
		if (requestCode == 0 && resultCode == RESULT_CANCELED)
		{
			// Reset activity
			startActivity(getIntent());
			finish();
		}
	}
	
	// Nút back
	public void onBackPressed()
	{
		// Nếu có fragment trong stack
		if (getSupportFragmentManager().getBackStackEntryCount() != 0)
		{
			// Back lại
			super.onBackPressed();
			// Nếu không
		}
		else
		{
			// Thoát luôn
			quit();
		}
	}
	
	// Khởi tạo menu
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		menu.add(0, Menu.FIRST, 0, getString(R.string.main_page));
		menu.add(0, Menu.FIRST + 2, 0, getString(R.string.search));
		menu.add(0, Menu.FIRST + 4, 0, getString(R.string.quit));
		return true;
	}
	
	// Xử lý menu
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == Menu.FIRST)
		{
			toHomePage();
		}
		if (item.getItemId() == Menu.FIRST + 2)
		{
			toSearchPage();
		}
		if (item.getItemId() == Menu.FIRST + 4)
		{
			quit();
		}
		return super.onOptionsItemSelected(item);
	}
	
	// Xử lý khi bấm nút logo
	public void onLogoClick(View v)
	{
		toHomePage();
	}
	
	// Xử lý khi bấm nút search
	public void onSearchClick(View v)
	{
		toSearchPage();
	}
	
	private void isNetworkEnabled()
	{
		// Nếu dịch vụ net = null
		if (((ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo() == null)
		{
			// Tạo dialog
			new AlertDialog.Builder(Main.this).setTitle(R.string.internet_disabled).setMessage(R.string.internet_needed).setPositiveButton(R.string.config, new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					// Bật cấu hình wifi lên
					startActivityForResult(new Intent(Settings.ACTION_WIFI_SETTINGS), 0);
				}
			}).show();
		}
	}
	
	private void toHomePage()
	{
		// Nếu không đứng tại màn hình chủ
		if (getSupportFragmentManager().getBackStackEntryCount() != 0)
		{
			// Hủy hết fragment
			getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}
	}
	
	private void toSearchPage()
	{
		viewPager.setCurrentItem(0);
		
		// Tạo search fragment
		getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.mainview, Search.newInstance()).commit();
	}
	
	private void quit()
	{
		// Hỏi xem có muốn tắt ko
		new AlertDialog.Builder(Main.this).setTitle(R.string.quit).setMessage(R.string.quit_message).setNegativeButton(R.string.no, null).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				// Nếu có thì kill luôn process
				System.exit(0);
			}
		}).show();
	}
	
	// Xử lý hiệu ứng cho trang bìa
	private class Cover extends Animation implements AnimationListener
	{
		
		public Cover()
		{
			// Lặp lại 100 lần (để câu giờ)
			setRepeatCount(D.animation.cover_timeout);
			
			setDuration(1);
			setFillAfter(false);
			setInterpolator(new AccelerateInterpolator());
			setAnimationListener(this);
		}
		
		// Bắt đầu... đéo làm gì hết
		public void onAnimationStart(Animation anim)
		{
			
		}
		
		// Lặp lại... tiếp tục câu giờ
		public void onAnimationRepeat(Animation anim)
		{
			
		}
		
		// Kết thúc
		public void onAnimationEnd(Animation animation)
		{
			// Ẩn trang bìa đi sau khi animation timeout
			findViewById(R.id.cover).setVisibility(View.INVISIBLE);
			
			// Tắt animation
			cancel();
		}
	}
}