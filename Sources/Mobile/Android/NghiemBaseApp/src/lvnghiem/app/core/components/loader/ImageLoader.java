package lvnghiem.app.core.components.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lvnghiem.app.core.data.FileCache;
import lvnghiem.app.core.data.MemoryCache;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class ImageLoader
{
	private MemoryCache mMemoryCache = new MemoryCache();
	private FileCache mFileCache;
	private Map<ImageView, String> mImageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
	private ExecutorService mExecutorService;
	private int mAltImage;

	public ImageLoader(Context context)
	{
		mFileCache = new FileCache(context);
		mExecutorService = Executors.newFixedThreadPool(5);
	}

	public ImageLoader displayImage(String url, ImageView imageView, int altImage)
	{
		mAltImage = altImage;
		mImageViews.put(imageView, url);

		Bitmap bitmap = mMemoryCache.get(url);
		if (bitmap != null)
		{
			imageView.setImageBitmap(bitmap);
		}
		else
		{
			queuePhoto(url, imageView);
			imageView.setImageResource(altImage);
		}

		return this;
	}

	private void queuePhoto(String url, ImageView imageView)
	{
		mExecutorService.submit(new PhotosLoader(new Photo(url, imageView)));
	}

	private Bitmap getBitmap(String url)
	{
		if (mFileCache == null)
		{
			return null;
		}
		File file = mFileCache.getFile(url);
		Bitmap bitmap = decodeFile(file);
		if (bitmap != null)
		{
			return bitmap;
		}

		try
		{
			URL imageUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setInstanceFollowRedirects(true);

			InputStream inputStream = conn.getInputStream();
			OutputStream outputStream = new FileOutputStream(file);
			copyStream(inputStream, outputStream);
			outputStream.close();

			return decodeFile(file);
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	private void copyStream(InputStream inputStream, OutputStream outputStream)
	{
		final int buffer_size = 1024;

		try
		{
			byte[] bytes = new byte[buffer_size];

			for (;;)
			{
				int count = inputStream.read(bytes, 0, buffer_size);

				if (count == -1)
				{
					break;
				}

				outputStream.write(bytes, 0, count);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private Bitmap decodeFile(File file)
	{
		try
		{
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(file), null, options);

			final int REQUIRED_SIZE = 70;
			int width_tmp = options.outWidth, height_tmp = options.outHeight, scale = 1;
			while (true)
			{
				if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
				{
					break;
				}
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			BitmapFactory.Options options2 = new BitmapFactory.Options();
			options2.inSampleSize = scale;

			return BitmapFactory.decodeStream(new FileInputStream(file), null, options2);
		}
		catch (FileNotFoundException e)
		{
			return null;
		}
	}

	private boolean imageViewReused(Photo Photo)
	{
		String tag = mImageViews.get(Photo.imageView);
		if (tag == null || !tag.equals(Photo.url))
		{
			return true;
		}
		return false;
	}

	public ImageLoader clearCache()
	{
		mMemoryCache.clear();
		mFileCache.clear();
		return this;
	}

	private class PhotosLoader implements Runnable
	{
		Photo Photo;

		PhotosLoader(Photo Photo)
		{
			this.Photo = Photo;
		}

		public void run()
		{
			if (imageViewReused(Photo))
			{
				return;
			}

			Bitmap bmp = getBitmap(Photo.url);
			mMemoryCache.put(Photo.url, bmp);
			if (imageViewReused(Photo))
			{
				return;
			}

			((Activity) Photo.imageView.getContext()).runOnUiThread(new BitmapDisplayer(bmp, Photo));
		}
	}

	private class BitmapDisplayer implements Runnable
	{
		Bitmap bitmap;
		Photo photo;

		public BitmapDisplayer(Bitmap bitmap, Photo photo)
		{
			this.bitmap = bitmap;
			this.photo = photo;
		}

		public void run()
		{
			if (imageViewReused(photo))
			{
				return;
			}
			if (bitmap != null)
			{
				photo.imageView.setImageBitmap(bitmap);
			}
			else
			{
				photo.imageView.setImageResource(mAltImage);
			}
		}
	}

	private class Photo
	{
		public String url;
		public ImageView imageView;

		public Photo(String url, ImageView imageView)
		{
			this.url = url;
			this.imageView = imageView;
		}
	}
}