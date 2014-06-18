package mobile.digizone.fragment;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import mobile.digizone.D;
import mobile.digizone.R;
import mobile.digizone.activity.Detail;
import mobile.digizone.parser.SpotParser.SpotHandler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

// Định nghĩa lại 1 Map Fragment theo ý mình
public class MyMapFragment extends Fragment implements LocationListener, GpsStatus.Listener
{
	
	private Timer timer;
	private SupportMapFragment mapFragment;
	
	protected GoogleMap map;
	protected LocationManager locationManager;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.map, container, false);
	}
	
	public void onStart()
	{
		super.onStart();
		// Nếu context chưa bị hủy
		if (getActivity() != null)
		{
			// Kiểm tra Google Play Services đã cài hay chưa
			int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
			if (status != ConnectionResult.SUCCESS)
			{
				GooglePlayServicesUtil.getErrorDialog(status, getActivity(), D.map.request_code).show();
			}
			else
			{
				// Nếu rồi thì tạo 1 cái map fragment, nhúng vào frame layout
				mapFragment = SupportMapFragment.newInstance();
				getChildFragmentManager().beginTransaction().add(R.id.map, mapFragment).commit();
				
				// Khởi tạo bộ quản lý địa điểm
				locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
				
				// Xử lý callback, map fragment yêu cầu location manager tìm vị
				// trí hiện tại, khi tìm ra, location manager lại hỏi map
				// fragment xem cần làm gì với cái vị trí đó
				// location manager gọi location listener trong khi map fragment
				// thực thi nó
				locationManager.requestLocationUpdates(locationManager.getBestProvider(new Criteria(), true), D.map.min_time, D.map.min_distance, this);
				
				locationManager.addGpsStatusListener(this);
				// Nếu GPS chưa bật, yêu cầu bật
				if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
				{
					new AlertDialog.Builder(getActivity()).setTitle(R.string.gps_disabled).setMessage(R.string.gps_needed).setPositiveButton(R.string.config, new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
						}
					}).show();
				}
			}
		}
	}
	
	public void onStop()
	{
		super.onStop();
		map = null;
	}
	
	public void onLocationChanged(Location location)
	{
		// Nếu fragment bị hủy khi listener vẫn đang chạy
		if (mapFragment == null)
		{
			return;
		}
		if (map == null)
		{
			setUpMapIfNeeded();
		}
		
		// trỏ về vị trí hiện tại
		if (location != null)
		{
			animateCameraTo(location.getLatitude(), location.getLongitude(), D.map.default_zoom);
		}
	}
	
	// Thông báo khi trạng thái thay đổi
	public void onProviderDisabled(String provider)
	{
		if (getActivity() != null)
		{
			Toast.makeText(getActivity(), provider + " đã tắt", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void onProviderEnabled(String provider)
	{
		if (getActivity() != null)
		{
			Toast.makeText(getActivity(), provider + " đã bật", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void onStatusChanged(String provider, int status, Bundle extras)
	{
		if (getActivity() != null)
		{
			Toast.makeText(getActivity(), "Trạng thái của " + provider + " đã đổi thành " + status, Toast.LENGTH_SHORT).show();
		}
	}
	
	public void onGpsStatusChanged(int event)
	{
		switch (event)
		{
			case GpsStatus.GPS_EVENT_STARTED:
				if (getActivity() != null)
				{
					Toast.makeText(getActivity(), "GPS đã bật", Toast.LENGTH_SHORT).show();
				}
				break;
			case GpsStatus.GPS_EVENT_STOPPED:
				if (getActivity() != null)
				{
					Toast.makeText(getActivity(), "GPS đã tắt", Toast.LENGTH_SHORT).show();
				}
				break;
		}
	}
	
	// Gắn địa điểm theo yêu cầu
	protected void addMarkers(final List<SpotHandler> markers)
	{
		for (SpotHandler spot : markers)
		{
			addMarker(spot.getLatitude(), spot.getLongitude(), spot.getName(), spot.getAddress());
		}
		
		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener()
		{
			@Override
			public void onInfoWindowClick(Marker marker)
			{
				Fragment detail = Detail.newInstance();
				Bundle dataBundle = new Bundle();
				for (SpotHandler spot : markers)
				{
					if (spot.getName().contains(marker.getTitle()))
					{
						dataBundle.putString("img1", spot.getImg1());
						dataBundle.putString("img2", spot.getImg2());
						dataBundle.putString("img3", spot.getImg3());
						dataBundle.putString("img4", spot.getImg4());
						dataBundle.putString("img5", spot.getImg5());
						dataBundle.putString("name", spot.getName());
						dataBundle.putInt("vote", spot.getVote());
						dataBundle.putString("address", spot.getAddress());
						dataBundle.putString("short_detail", spot.getShortDetail());
						dataBundle.putString("full_detail", spot.getFullDetail());
						dataBundle.putString("email", spot.getEmail());
						dataBundle.putString("phone", spot.getPhone());
						dataBundle.putString("facebook", spot.getFacebook());
						dataBundle.putDouble("latitude", spot.getLatitude());
						dataBundle.putDouble("longitude", spot.getLongitude());
						break;
					}
				}
				detail.setArguments(dataBundle);
				getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.mainview, detail).commit();
			}
		});
	}
	
	// Thiết đặt cho google map
	private void setUpMapIfNeeded()
	{
		map = mapFragment.getMap();
		timer = new Timer();
		timer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				if (mapFragment != null && mapFragment.isVisible())
				{
					timer.cancel();
					if (getActivity() != null)
					{
						getActivity().runOnUiThread(new Runnable()
						{
							@Override
							public void run()
							{
								if (map != null)
								{
									map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
									map.setMyLocationEnabled(true);
									map.getUiSettings().setAllGesturesEnabled(true);
									addMarkers();
								}
							}
						});
					}
				}
			}
		}, 0, 200);
	}
	
	protected void addMarker(double latitude, double longitude, String title, String description)
	{
		if (map == null)
		{
			return;
		}
		map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(title).snippet(description).icon(BitmapDescriptorFactory.fromResource(R.drawable.point)));
	}
	
	// Hàm gắn địa điểm mặc định, không gắn địa điểm nào ở lớp dưới, phải ghi đè
	// ở lớp trên nếu muốn gắn địa điểm mặc định
	protected void addMarkers()
	{
		if (map == null)
		{
			return;
		}
		map.setOnMarkerClickListener(new OnMarkerClickListener()
		{
			@Override
			public boolean onMarkerClick(Marker marker)
			{
				Toast.makeText(getActivity(), "Tên:" + marker.getTitle() + "\nĐịa chỉ:" + marker.getSnippet(), Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}
	
	// Trỏ màn hình về vị trí chỉ định
	protected void animateCameraTo(double latitude, double longitude, int zoom)
	{
		if (map == null)
		{
			return;
		}
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), zoom));
		locationManager.removeUpdates(this);
	}
}