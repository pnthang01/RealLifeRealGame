package mobile.digizone.parser;

import java.util.ArrayList;

import mobile.digizone.persistent.Ward;

// Bộ phân phối phường
public class WardParser
{
	
	private ArrayList<Ward> list = new ArrayList<Ward>();
	
	public WardParser init(String data)
	{
		String[] array = data.split(";;");
		int rows = Integer.parseInt(array[0]), columns = Integer.parseInt(array[1]);
		for (int i = 0; i < rows; i++)
		{
			list.add(new Ward(array[(i * columns) + 2], array[(i * columns) + 3]));
		}
		return this;
	}
	
	public ArrayList<Ward> get()
	{
		return list;
	}
	
	public int getId(String code)
	{
		for (Ward ward : list)
		{
			if (ward.getCode().contains(code))
			{
				return ward.getId();
			}
		}
		return 0;
	}
}