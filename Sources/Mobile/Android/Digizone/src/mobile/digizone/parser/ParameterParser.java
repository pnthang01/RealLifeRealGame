package mobile.digizone.parser;

import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;

// Bộ phân phối tham số cho web service
public class ParameterParser
{
	
	private ArrayList<PropertyInfo> list;
	
	public ParameterParser()
	{
		list = new ArrayList<PropertyInfo>();
	}
	
	public ArrayList<PropertyInfo> getParameters()
	{
		return list;
	}
	
	public ParameterParser add(String name, Object value)
	{
		PropertyInfo p = new PropertyInfo();
		p.setName(name);
		p.setValue(value);
		list.add(p);
		return this;
	}
}