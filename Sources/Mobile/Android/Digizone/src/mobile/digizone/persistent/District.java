package mobile.digizone.persistent;

public class District
{
	
	private int id;
	private String code;
	
	public District(String id, String code)
	{
		this.id = Integer.parseInt(id);
		this.code = code;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getCode()
	{
		return code;
	}
}