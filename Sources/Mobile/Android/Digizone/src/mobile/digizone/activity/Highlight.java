package mobile.digizone.activity;

import java.util.List;

import mobile.digizone.fragment.MyListFragment;
import mobile.digizone.parser.SpotParser.SpotHandler;
import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;

// Trang Cafe Nổi bật, là 1 cái list
@SuppressLint("ValidFragment")
public class Highlight extends MyListFragment
{
	
	public Highlight(List<SpotHandler> list)
	{
		// Đổ dữ liệu từ web service vào list từ hàm getHighlight (lấy địa điểm
		// nổi bật)
		try
		{
			// list = new SpotProvider(new
			// DataLoader(D.method.get_highlight).execute().get()).get();
			this.list = list;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Fragment newInstance(List<SpotHandler> list)
	{
		return new Highlight(list);
	}
}