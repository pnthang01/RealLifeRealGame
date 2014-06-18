package mobile.digizone.utils;

import java.io.File;

import android.content.Context;

public class FileCache
{
	
	private File cacheDir;
	
	public FileCache(Context context)
	{
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
		{
			cacheDir = new File(android.os.Environment.getExternalStorageDirectory(), "TempImages");
		}
		else if (context != null)
		{
			cacheDir = context.getCacheDir();
		}
		else
		{
			cacheDir = null;
		}
		
		if (cacheDir != null && !cacheDir.exists())
		{
			cacheDir.mkdirs();
		}
	}
	
	public File getFile(String url)
	{
		if (cacheDir != null)
		{
			return new File(cacheDir, String.valueOf(url.hashCode()));
		}
		else
		{
			return null;
		}
	}
	
	public void clear()
	{
		if (cacheDir == null)
		{
			return;
		}
		
		File[] files = cacheDir.listFiles();
		if (files == null)
		{
			return;
		}
		for (File file : files)
		{
			file.delete();
		}
	}
}