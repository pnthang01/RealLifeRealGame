package nghiem.app.core.data;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;

public class MemoryCache
{
	private Map<String, SoftReference<Bitmap>> mCache = Collections.synchronizedMap(new HashMap<String, SoftReference<Bitmap>>());

	public Bitmap get(String id)
	{
		if (!mCache.containsKey(id))
		{
			return null;
		}

		return mCache.get(id).get();
	}

	public void put(String id, Bitmap bitmap)
	{
		mCache.put(id, new SoftReference<Bitmap>(bitmap));
	}

	public void clear()
	{
		mCache.clear();
	}
}