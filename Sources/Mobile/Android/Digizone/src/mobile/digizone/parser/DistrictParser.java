package mobile.digizone.parser;

import java.util.ArrayList;

import mobile.digizone.persistent.District;

// Bộ phân phối quận
public class DistrictParser
{
	
	private ArrayList<District> list = new ArrayList<District>();
	
	public DistrictParser init(String data)
	{
		// Phân tách dữ liệu và gắn vào list
		String[] array = data.split(";;");
		int rows = Integer.parseInt(array[0]), columns = Integer.parseInt(array[1]);
		for (int i = 0; i < rows; i++)
		{
			list.add(new District(array[(i * columns) + 2], array[(i * columns) + 3]));
		}
		return this;
	}
	
	// Lấy List ra
	public ArrayList<District> get()
	{
		return list;
	}
	
	// Lấy id quận theo mã
	public int getId(String code)
	{
		for (District district : list)
		{
			if (district.getCode().contains(code))
			{
				return district.getId();
			}
		}
		return 0;
	}
}