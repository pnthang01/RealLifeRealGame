package mobile.digizone.activity;

import mobile.digizone.D;
import mobile.digizone.R;
import mobile.digizone.fragment.MyMapFragment;
import mobile.digizone.loader.ImageLoader;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

public class Detail extends Fragment
{
	
	private static Bundle args;
	private ImageLoader imageLoader;
	
	public Detail()
	{
		// Khởi tạo trình duyệt ảnh
		imageLoader = new ImageLoader(getActivity());
	}
	
	// Tạo fragment kiểu Detail (gán về kiểu cha)
	public static Fragment newInstance()
	{
		return new Detail();
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
		
		// Nếu có tham số truyền vào khi khởi tạo fragment
		if ((args = getArguments()) != null)
		{
			updateView();
		}
	}
	
	// Xóa bộ đệm của trình duyệt ảnh khi hủy fragment
	public void onStop()
	{
		super.onStop();
		imageLoader.clearCache();
	}
	
	private void updateView()
	{
		// Nếu context chưa bị hủy
		if (getActivity() != null)
		{
			// Lấy ảnh trên Internet về
			if (args.getString("img1") != null)
			{
				imageLoader.displayImage(args.getString("img1"), (ImageView) getActivity().findViewById(R.id.ivPreview1), R.drawable.no_image);
			}
			if (args.getString("img2") != null)
			{
				imageLoader.displayImage(args.getString("img2"), (ImageView) getActivity().findViewById(R.id.ivPreview2), R.drawable.no_image);
			}
			if (args.getString("img3") != null)
			{
				imageLoader.displayImage(args.getString("img3"), (ImageView) getActivity().findViewById(R.id.ivPreview3), R.drawable.no_image);
			}
			if (args.getString("img4") != null)
			{
				imageLoader.displayImage(args.getString("img4"), (ImageView) getActivity().findViewById(R.id.ivPreview4), R.drawable.no_image);
			}
			if (args.getString("img5") != null)
			{
				imageLoader.displayImage(args.getString("img5"), (ImageView) getActivity().findViewById(R.id.ivPreview5), R.drawable.no_image);
			}
			
			// Truyền dữ liệu vào giao diện
			((TextView) getActivity().findViewById(R.id.tvDetailTitle)).setText(args.getString("name"));
			((TextView) getActivity().findViewById(R.id.tvDetailAddress)).setText("Địa chỉ : " + args.getString("address"));
			((TextView) getActivity().findViewById(R.id.tvDetailEmail)).setText("Email: " + args.getString("email"));
			((TextView) getActivity().findViewById(R.id.tvDetailPhone)).setText("Điện thoại: " + args.getString("phone"));
			((TextView) getActivity().findViewById(R.id.tvDetailFanpage)).setText("Fan page: " + args.getString("facebook"));
			((TextView) getActivity().findViewById(R.id.tvDetailDescription)).setText(args.getString("short_detail"));
			((TextView) getActivity().findViewById(R.id.tvDetail)).setText(args.getString("full_detail").contains("hông biết") ? args.getString("short_detail") : args.getString("full_detail"));
			((RatingBar) getActivity().findViewById(R.id.rtbVoteDetail)).setRating(args.getInt("vote"));
		}
	}
	
	public void openMap(View v)
	{
		// Bật Map nội bộ lên nhúng vào frame layout
		getChildFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.flDetail, DetailMap.newInstance()).commit();
	}
	
	// Map nội bộ của trang Detail
	@SuppressLint("ValidFragment")
	private static class DetailMap extends MyMapFragment
	{
		
		// Tạo fragment kiểu DetailMap (gán về kiểu cha)
		private static Fragment newInstance()
		{
			return new DetailMap();
		}
		
		// Ghi đè hàm addMarkers của MyMapFragment
		protected void addMarkers()
		{
			// Chạy lại nguyên xi hàm addMarkers của MyMapFragment
			super.addMarkers();
			
			// Gắn địa điểm do Detail truyền xuống, trỏ về đó
			addMarker(args.getDouble("latitude"), args.getDouble("longitude"), args.getString("name"), args.getString("address"));
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(args.getDouble("latitude"), args.getDouble("longitude")), D.map.default_zoom));
			locationManager.removeUpdates(this);
		}
	}
}