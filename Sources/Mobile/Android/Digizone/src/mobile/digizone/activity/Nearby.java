package mobile.digizone.activity;

import mobile.digizone.D;
import mobile.digizone.fragment.MyMapFragment;
import mobile.digizone.loader.DataLoader;
import mobile.digizone.parser.ParameterParser;
import mobile.digizone.parser.SpotParser;

import android.location.Location;
import android.support.v4.app.Fragment;

public class Nearby extends MyMapFragment
{
	
	public static Fragment newInstance()
	{
		return new Nearby();
	}
	
	// Ghi đè hàm onLocationChanged của MyMapActivity
	public void onLocationChanged(Location location)
	{
		// Chạy lại y xì những gì onLocationChanged của MyMapActivity làm
		super.onLocationChanged(location);
		
		// Gắn địa điểm dựa trên những gì web service trả về khi gọi hàm
		// getNearby (lấy những địa điểm gần đây)
		try
		{
			addMarkers(new SpotParser(new DataLoader(D.method.get_nearby).execute(new ParameterParser().add(D.parameter.latitude, String.valueOf(location.getLatitude())).add(D.parameter.longitude, String.valueOf(location.getLongitude()))).get()).get());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}