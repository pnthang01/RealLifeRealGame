package mobile.digizone.activity;

import java.util.List;

import mobile.digizone.R;
import mobile.digizone.loader.ImageLoader;
import mobile.digizone.parser.SpotParser.SpotHandler;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("ValidFragment")
public class Master extends Fragment
{
	
	private ImageLoader imageLoader;
	private List<SpotHandler> list;
	
	public Master(List<SpotHandler> list)
	{
		// Khởi tạo trình duyệt ảnh
		imageLoader = new ImageLoader(getActivity());
		this.list = list;
	}
	
	// Tạo fragment kiểu Master (gán về kiểu cha)
	public static Fragment newInstance(List<SpotHandler> list)
	{
		return new Master(list);
	}
	
	// Tạo giao diện
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Nhúng giao diện trong res/layout/detail.xml vào fragment
		return inflater.inflate(R.layout.detail, container, false);
	}
	
	// Bắt đầu
	public void onStart()
	{
		super.onStart();
		/*
		 * imageLoader.displayImage(list.get(1).getImg1(), (ImageButton)
		 * getActivity().findViewById(R.id.btHighlight), R.drawable.no_image);
		 * imageLoader.displayImage(list.get(2).getImg1(), (ImageButton)
		 * getActivity().findViewById(R.id.btNewbie1), R.drawable.no_image);
		 * imageLoader.displayImage(list.get(3).getImg1(), (ImageButton)
		 * getActivity().findViewById(R.id.btNewbie2), R.drawable.no_image);
		 * imageLoader.displayImage(list.get(4).getImg1(), (ImageButton)
		 * getActivity().findViewById(R.id.btNewbie3), R.drawable.no_image);
		 * imageLoader.displayImage(list.get(5).getImg1(), (ImageButton)
		 * getActivity().findViewById(R.id.btNewbie4), R.drawable.no_image);
		 * imageLoader.displayImage(list.get(6).getImg1(), (ImageButton)
		 * getActivity().findViewById(R.id.btNewbie5), R.drawable.no_image);
		 * imageLoader.displayImage(list.get(7).getImg1(), (ImageButton)
		 * getActivity().findViewById(R.id.btTopRated1), R.drawable.no_image);
		 * imageLoader.displayImage(list.get(8).getImg1(), (ImageButton)
		 * getActivity().findViewById(R.id.btTopRated2), R.drawable.no_image);
		 * imageLoader.displayImage(list.get(9).getImg1(), (ImageButton)
		 * getActivity().findViewById(R.id.btTopRated3), R.drawable.no_image);
		 * imageLoader.displayImage(list.get(10).getImg1(), (ImageButton)
		 * getActivity().findViewById(R.id.btTopRated4), R.drawable.no_image);
		 * imageLoader.displayImage(list.get(11).getImg1(), (ImageButton)
		 * getActivity().findViewById(R.id.ivRecomend1), R.drawable.no_image);
		 * imageLoader.displayImage(list.get(12).getImg1(), (ImageButton)
		 * getActivity().findViewById(R.id.ivRecomend2), R.drawable.no_image);
		 * 
		 * ((TextView)
		 * getActivity().findViewById(R.id.tvTitleRecomend1)).setText
		 * (list.get(11).getName()); ((TextView)
		 * getActivity().findViewById(R.id.
		 * tvTitleRecomend2)).setText(list.get(12).getName()); ((TextView)
		 * getActivity
		 * ().findViewById(R.id.tvAddressRecomend1)).setText(list.get(
		 * 11).getAddress()); ((TextView)
		 * getActivity().findViewById(R.id.tvAddressRecomend2
		 * )).setText(list.get(12).getAddress());
		 */
	}
	
	// Xóa bộ đệm của trình duyệt ảnh khi hủy fragment
	public void onStop()
	{
		super.onStop();
		imageLoader.clearCache();
	}
	
	public void onHighlightClick(View v)
	{
		openDetail(list.get(1));
	}
	
	public void onNewbie1Click(View v)
	{
		openDetail(list.get(2));
	}
	
	public void onNewbie2Click(View v)
	{
		openDetail(list.get(3));
	}
	
	public void onNewbie3Click(View v)
	{
		openDetail(list.get(4));
	}
	
	public void onNewbie4Click(View v)
	{
		openDetail(list.get(5));
	}
	
	public void onNewbie5Click(View v)
	{
		openDetail(list.get(6));
	}
	
	public void onTopRated1Click(View v)
	{
		openDetail(list.get(7));
	}
	
	public void onTopRated2Click(View v)
	{
		openDetail(list.get(8));
	}
	
	public void onTopRated3Click(View v)
	{
		openDetail(list.get(9));
	}
	
	public void onTopRated4Click(View v)
	{
		openDetail(list.get(10));
	}
	
	public void onRecomend1Click(View v)
	{
		openDetail(list.get(11));
	}
	
	public void onRecomend2Click(View v)
	{
		openDetail(list.get(12));
	}
	
	private void openDetail(SpotHandler item)
	{
		Bundle args = new Bundle();
		args.putString("img1", item.getImg1());
		args.putString("img2", item.getImg2());
		args.putString("img3", item.getImg3());
		args.putString("img4", item.getImg4());
		args.putString("img5", item.getImg5());
		args.putString("name", item.getName());
		args.putInt("vote", item.getVote());
		args.putString("address", item.getAddress());
		args.putString("short_detail", item.getShortDetail());
		args.putString("full_detail", item.getFullDetail());
		args.putString("email", item.getEmail());
		args.putString("phone", item.getPhone());
		args.putString("facebook", item.getFacebook());
		args.putDouble("latitude", item.getLatitude());
		args.putDouble("longitude", item.getLongitude());
		
		Fragment detail = Detail.newInstance();
		detail.setArguments(args);
		getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.mainview, detail).commit();
	}
}