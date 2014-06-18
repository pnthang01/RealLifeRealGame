package mobile.digizone.activity;

import mobile.digizone.D;
import mobile.digizone.R;
import mobile.digizone.animation.ExpandableViewController;
import mobile.digizone.fragment.MyMapFragment;
import mobile.digizone.loader.DataLoader;
import mobile.digizone.parser.ParameterParser;
import mobile.digizone.parser.SpotParser;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.gms.maps.model.LatLng;

// Trang Tìm kiếm, bên trong có nhúng 1 cái Google Maps
public class Search extends MyMapFragment
{
	
	private LinearLayout llSearch;
	private EditText etSearch;
	private ImageButton btExpand;
	private Spinner spnDistrict, spnWard, spnType;
	private boolean isBoxCollapsed = true;
	private int height;
	
	// Tạo fragment kiểu Search (gán về kiểu cha)
	public static Fragment newInstance()
	{
		return new Search();
	}
	
	// Tạo giao diện
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		// Nhúng giao diện trong res/layout/search.xml vào fragment
		return inflater.inflate(R.layout.search, container, false);
	}
	
	// Khi Activity đã tạo xong
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		
		// Nếu context chưa bị hủy
		if (getActivity() != null)
		{
			// Bắt đối tượng trong giao diện
			llSearch = (LinearLayout) getActivity().findViewById(R.id.llSearch);
			etSearch = (EditText) getActivity().findViewById(R.id.etSearch);
			btExpand = (ImageButton) getActivity().findViewById(R.id.btExpand);
			spnDistrict = (Spinner) getActivity().findViewById(R.id.spnDistrict);
			spnDistrict.setSelection(0);
			spnType = (Spinner) getActivity().findViewById(R.id.spnType);
			spnType.setSelection(0);
			height = llSearch.getLayoutParams().height;
			
			// Khi chọn quận, đổ dữ liệu vào ô chọn phường
			spnDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
			{
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
				{
					initWard();
				}
				
				public void onNothingSelected(AdapterView<?> parent)
				{
					
				}
			});
		}
	}
	
	// Nút Tìm kiếm được nhấn
	public void doSearch(View v)
	{
		// Đóng bàn phím
		((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
		
		// Xóa hết markers
		map.clear();
		
		// Ẩn khung search đi
		switchSearchBox();
		
		String data = "", type = spnType.getSelectedItem().toString(), district = spnDistrict.getSelectedItem().toString(), ward = spnWard.getSelectedItem().toString();
		try
		{
			// Nếu ô tìm kiếm có chữ
			if (etSearch.getText().toString().length() != 0)
			{
				data = new DataLoader(D.method.get_store_by_unsigned_name).execute(new ParameterParser().add(D.parameter.name, etSearch.getText().toString())).get();
				
				// Nếu Chỉ chọn loại
			}
			else if (!type.contains(D.search.no_type_selected) && district.contains(D.search.no_district_selected))
			{
				data = new DataLoader(D.method.get_store_by_catalog).execute(new ParameterParser().add(D.parameter.type, type)).get();
				
				// Nếu chỉ chọn quận
			}
			else if (type.contains(D.search.no_type_selected) && !district.contains(D.search.no_district_selected) && ward.contains(D.search.no_ward_selected))
			{
				data = new DataLoader(D.method.get_store_by_district).execute(new ParameterParser().add(D.parameter.district, district)).get();
				
				// Nếu chọn loại và quận
			}
			else if (!type.contains(D.search.no_type_selected) && !district.contains(D.search.no_district_selected) && ward.contains(D.search.no_ward_selected))
			{
				data = new DataLoader(D.method.get_store_by_district_and_catalog).execute(new ParameterParser().add(D.parameter.type, type).add(D.parameter.district, district)).get();
				
				// Nếu chỉ chọn phường
			}
			else if (type.contains(D.search.no_type_selected) && !district.contains(D.search.no_district_selected) && !ward.contains(D.search.no_ward_selected))
			{
				data = new DataLoader(D.method.get_store_by_ward).execute(new ParameterParser().add(D.parameter.district, district).add(D.parameter.ward, ward)).get();
				
				// Nếu chọn loại và phường
			}
			else if (!type.contains(D.search.no_type_selected) && !district.contains(D.search.no_district_selected) && !ward.contains(D.search.no_ward_selected))
			{
				data = new DataLoader(D.method.get_store_by_ward_and_catalog).execute(new ParameterParser().add(D.parameter.type, type).add(D.parameter.district, district).add(D.parameter.ward, ward)).get();
				
				// Không chọn gì cả
			}
			else
			{
				return;
			}
			
			// Gắn các địa điểm tìm được và trỏ vào giữa những điểm đó
			SpotParser spotParser = new SpotParser(data);
			LatLng center = spotParser.getCenter();
			if (center != null)
			{
				animateCameraTo(center.latitude, center.longitude, 12);
			}
			addMarkers(spotParser.get());
		}
		catch (Exception e)
		{
			// Nếu có lỗi trong lúc gọi web service, báo ra
			new AlertDialog.Builder(getActivity()).setTitle("Lỗi: ").setMessage(e.toString()).setPositiveButton(R.string.config, null).show();
		}
	}
	
	// Nút bật tắt khung search
	public void doExpand(View v)
	{
		switchSearchBox();
	}
	
	protected void switchSearchBox()
	{
		// Khởi động hiệu ứng bật tắt
		llSearch.startAnimation(new ExpandableViewController(llSearch, height, 1, D.animation.duration, true, isBoxCollapsed = !isBoxCollapsed));
		
		// Hẹn giờ tắt hiệu ứng
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					llSearch.clearAnimation();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}, D.animation.duration * 2);
		
		// Ẩn/hiện nút bật khung search
		btExpand.setVisibility(btExpand.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
	}
	
	private void initWard()
	{
		// Nếu context chưa bị hủy
		if (getActivity() != null)
		{
			// Chọn quận nào thì ô chọn phường của quận đó hiện ra, những ô khác
			// ẩn đi
			final Spinner spnDistrict0 = (Spinner) getActivity().findViewById(R.id.spnDistrict0), spnDistrict1 = (Spinner) getActivity().findViewById(R.id.spnDistrict1), spnDistrict2 = (Spinner) getActivity().findViewById(R.id.spnDistrict2), spnDistrict3 = (Spinner) getActivity().findViewById(R.id.spnDistrict3), spnDistrict4 = (Spinner) getActivity().findViewById(R.id.spnDistrict4), spnDistrict5 = (Spinner) getActivity().findViewById(R.id.spnDistrict5), spnDistrict6 = (Spinner) getActivity().findViewById(R.id.spnDistrict6), spnDistrict7 = (Spinner) getActivity().findViewById(R.id.spnDistrict7), spnDistrict8 = (Spinner) getActivity().findViewById(R.id.spnDistrict8), spnDistrict9 = (Spinner) getActivity().findViewById(R.id.spnDistrict9), spnDistrict10 = (Spinner) getActivity().findViewById(R.id.spnDistrict10), spnDistrict11 = (Spinner) getActivity().findViewById(R.id.spnDistrict11), spnDistrict12 = (Spinner) getActivity().findViewById(R.id.spnDistrict12), spnBinhTan = (Spinner) getActivity().findViewById(R.id.spnBinhTan), spnBinhThanh = (Spinner) getActivity().findViewById(R.id.spnBinhThanh), spnGoVap = (Spinner) getActivity().findViewById(R.id.spnGoVap), spnPhuNhuan = (Spinner) getActivity().findViewById(R.id.spnPhuNhuan), spnTanBinh = (Spinner) getActivity().findViewById(R.id.spnTanBinh), spnTanPhu = (Spinner) getActivity().findViewById(R.id.spnTanPhu), spnThuDuc = (Spinner) getActivity().findViewById(R.id.spnThuDuc);
			spnDistrict0.setVisibility(View.INVISIBLE);
			spnDistrict1.setVisibility(View.INVISIBLE);
			spnDistrict2.setVisibility(View.INVISIBLE);
			spnDistrict3.setVisibility(View.INVISIBLE);
			spnDistrict4.setVisibility(View.INVISIBLE);
			spnDistrict5.setVisibility(View.INVISIBLE);
			spnDistrict6.setVisibility(View.INVISIBLE);
			spnDistrict7.setVisibility(View.INVISIBLE);
			spnDistrict8.setVisibility(View.INVISIBLE);
			spnDistrict9.setVisibility(View.INVISIBLE);
			spnDistrict10.setVisibility(View.INVISIBLE);
			spnDistrict11.setVisibility(View.INVISIBLE);
			spnDistrict12.setVisibility(View.INVISIBLE);
			spnBinhTan.setVisibility(View.INVISIBLE);
			spnBinhThanh.setVisibility(View.INVISIBLE);
			spnGoVap.setVisibility(View.INVISIBLE);
			spnPhuNhuan.setVisibility(View.INVISIBLE);
			spnTanBinh.setVisibility(View.INVISIBLE);
			spnTanPhu.setVisibility(View.INVISIBLE);
			spnThuDuc.setVisibility(View.INVISIBLE);
			
			String selectedDistrict = String.valueOf(spnDistrict.getSelectedItem());
			if (selectedDistrict.contains("Quận 1"))
			{
				spnWard = spnDistrict1;
			}
			else if (selectedDistrict.contains("Quận 2"))
			{
				spnWard = spnDistrict2;
			}
			else if (selectedDistrict.contains("Quận 3"))
			{
				spnWard = spnDistrict3;
			}
			else if (selectedDistrict.contains("Quận 4"))
			{
				spnWard = spnDistrict4;
			}
			else if (selectedDistrict.contains("Quận 5"))
			{
				spnWard = spnDistrict5;
			}
			else if (selectedDistrict.contains("Quận 6"))
			{
				spnWard = spnDistrict6;
			}
			else if (selectedDistrict.contains("Quận 7"))
			{
				spnWard = spnDistrict7;
			}
			else if (selectedDistrict.contains("Quận 8"))
			{
				spnWard = spnDistrict8;
			}
			else if (selectedDistrict.contains("Quận 9"))
			{
				spnWard = spnDistrict9;
			}
			else if (selectedDistrict.contains("Quận 10"))
			{
				spnWard = spnDistrict10;
			}
			else if (selectedDistrict.contains("Quận 11"))
			{
				spnWard = spnDistrict11;
			}
			else if (selectedDistrict.contains("Quận 12"))
			{
				spnWard = spnDistrict12;
			}
			else if (selectedDistrict.contains("Bình Tân"))
			{
				spnWard = spnBinhTan;
			}
			else if (selectedDistrict.contains("Bình Thạnh"))
			{
				spnWard = spnBinhThanh;
			}
			else if (selectedDistrict.contains("Gò Vấp"))
			{
				spnWard = spnGoVap;
			}
			else if (selectedDistrict.contains("Phú Nhuận"))
			{
				spnWard = spnPhuNhuan;
			}
			else if (selectedDistrict.contains("Tân Bình"))
			{
				spnWard = spnTanBinh;
			}
			else if (selectedDistrict.contains("Tân Phú"))
			{
				spnWard = spnTanPhu;
			}
			else if (selectedDistrict.contains("Thủ Đức"))
			{
				spnWard = spnThuDuc;
			}
			else
			{
				spnWard = spnDistrict0;
			}
			
			spnWard.setSelection(0);
			spnWard.setVisibility(View.VISIBLE);
		}
	}
}