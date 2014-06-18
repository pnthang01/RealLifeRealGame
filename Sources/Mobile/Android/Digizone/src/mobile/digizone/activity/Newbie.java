package mobile.digizone.activity;

import java.util.List;

import mobile.digizone.fragment.MyListFragment;
import mobile.digizone.parser.SpotParser.SpotHandler;
import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;

// Trang Cafe Mới tham gia, là 1 cái list
@SuppressLint("ValidFragment")
public class Newbie extends MyListFragment
{
	
	public Newbie(List<SpotHandler> list)
	{
		try
		{
			// Đổ dữ liệu từ web service vào list từ hàm getNewbie (lấy địa điểm
			// mới tham gia)
			// list = new SpotProvider(new
			// DataLoader(D.method.get_newbie).execute().get()).get();
			this.list = list;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Fragment newInstance(List<SpotHandler> list)
	{
		return new Newbie(list);
	}
}