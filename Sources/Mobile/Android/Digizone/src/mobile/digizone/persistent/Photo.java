package mobile.digizone.persistent;

import android.widget.ImageView;

public class Photo
{
	
	public String url;
	public ImageView imageView;
	
	public Photo(String url, ImageView imageView)
	{
		this.url = url;
		this.imageView = imageView;
	}
}