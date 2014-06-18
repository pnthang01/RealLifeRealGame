package mobile.digizone.parser;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import android.text.Html;

import com.google.android.gms.maps.model.LatLng;

import mobile.digizone.D;

// Bộ phân phối đia điểm lấy từ web service
public class SpotParser
{
	private ArrayList<SpotHandler> list = new ArrayList<SpotHandler>();
	
	public SpotParser(String data) throws UnsupportedEncodingException
	{
		String[] array = data.split(";;");
		int rows = Integer.parseInt(array[0]), columns = Integer.parseInt(array[1]);
		for (int i = 0; i < rows; i++)
		{
			list.add(new SpotHandler(array[(i * columns) + 2], array[(i * columns) + 3], array[(i * columns) + 4], array[(i * columns) + 5], array[(i * columns) + 6], array[(i * columns) + 7], array[(i * columns) + 8], array[(i * columns) + 9], array[(i * columns) + 10], array[(i * columns) + 11], array[(i * columns) + 12], array[(i * columns) + 13], array[(i * columns) + 14], array[(i * columns) + 15], array[(i * columns) + 16], array[(i * columns) + 17]));
		}
	}
	
	public ArrayList<SpotHandler> get()
	{
		return list;
	}
	
	// Lấy điểm trung tâm của khu vực được bao quanh bới những điểm trong list
	public LatLng getCenter()
	{
		double minLatitude = 500, minLongitude = 500, maxLatitude = 0, maxLongitude = 0;
		if (list.size() != 0)
		{
			for (SpotHandler spot : list)
			{
				if (spot.getLatitude() > maxLatitude)
				{
					maxLatitude = spot.getLatitude();
				}
				if (spot.getLatitude() < minLatitude)
				{
					minLatitude = spot.getLatitude();
				}
				if (spot.getLongitude() > maxLongitude)
				{
					maxLongitude = spot.getLongitude();
				}
				if (spot.getLongitude() < minLongitude)
				{
					minLongitude = spot.getLongitude();
				}
			}
			return new LatLng((minLatitude + maxLatitude) / 2, (minLongitude + maxLongitude) / 2);
		}
		else
		{
			return null;
		}
	}
	
	public class SpotHandler
	{
		
		private int id, vote;
		private double latitude, longitude;
		private String name, address, shortDetail, fullDetail, email, phone, facebook, img1, img2, img3, img4, img5;
		private static final String NULL = "Chưa cập nhật", ERROR = "Lỗi";
		
		public SpotHandler(String id, String name, String address, String vote, String email, String phone, String lagitude, String longitude, String facebook, String img1, String img2, String img3, String img4, String img5, String fullDetail, String shortDetail) throws UnsupportedEncodingException
		{
			this.id = Integer.parseInt(id);
			this.vote = Integer.parseInt(vote);
			this.latitude = Double.parseDouble(lagitude);
			this.longitude = Double.parseDouble(longitude);
			this.name = name.contains("NULL") ? NULL : URLDecoder.decode(name, "UTF-8");
			this.address = address.contains("NULL") ? NULL : URLDecoder.decode(address, "UTF-8");
			this.shortDetail = shortDetail.contains("NULL") ? NULL : "" + Html.fromHtml(URLDecoder.decode(shortDetail, "UTF-8"));
			this.fullDetail = fullDetail.contains("NULL") ? NULL : "" + Html.fromHtml(URLDecoder.decode(fullDetail, "UTF-8"));
			this.email = email.contains("NULL") ? NULL : email.contains("@") ? URLDecoder.decode(email, "UTF-8") : ERROR;
			this.phone = phone.contains("NULL") ? NULL : URLDecoder.decode(phone, "UTF-8");
			this.facebook = facebook.contains("NULL") ? NULL : facebook.contains("facebook") ? URLDecoder.decode(facebook, "UTF-8") : ERROR;
			this.img1 = img1.contains("NULL") ? null : URLDecoder.decode(D.service.image_host + img1, "UTF-8");
			this.img2 = img2.contains("NULL") ? null : URLDecoder.decode(D.service.image_host + img2, "UTF-8");
			this.img3 = img3.contains("NULL") ? null : URLDecoder.decode(D.service.image_host + img3, "UTF-8");
			this.img4 = img4.contains("NULL") ? null : URLDecoder.decode(D.service.image_host + img4, "UTF-8");
			this.img5 = img5.contains("NULL") ? null : URLDecoder.decode(D.service.image_host + img5, "UTF-8");
		}
		
		public int getId()
		{
			return id;
		}
		
		public int getVote()
		{
			return vote;
		}
		
		public double getLatitude()
		{
			return latitude;
		}
		
		public double getLongitude()
		{
			return longitude;
		}
		
		public String getName()
		{
			return name;
		}
		
		public String getAddress()
		{
			return address;
		}
		
		public String getShortDetail()
		{
			return shortDetail;
		}
		
		public String getFullDetail()
		{
			return fullDetail;
		}
		
		public String getEmail()
		{
			return email;
		}
		
		public String getPhone()
		{
			return phone;
		}
		
		public String getFacebook()
		{
			return facebook;
		}
		
		public String getImg1()
		{
			return img1;
		}
		
		public String getImg2()
		{
			return img2;
		}
		
		public String getImg3()
		{
			return img3;
		}
		
		public String getImg4()
		{
			return img4;
		}
		
		public String getImg5()
		{
			return img5;
		}
	}
}