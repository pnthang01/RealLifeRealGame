package mobile.digizone.utils;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;

public class MemoryCache
{
	
	private Map<String, SoftReference<Bitmap>> cache = Collections.synchronizedMap(new HashMap<String, SoftReference<Bitmap>>());
	
	public Bitmap get(String id)
	{
		if (!cache.containsKey(id))
		{
			return null;
		}
		
		return cache.get(id).get();
	}
	
	public void put(String id, Bitmap bitmap)
	{
		cache.put(id, new SoftReference<Bitmap>(bitmap));
	}
	
	public void clear()
	{
		cache.clear();
	}
}