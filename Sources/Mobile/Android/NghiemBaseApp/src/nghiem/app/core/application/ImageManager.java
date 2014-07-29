package nghiem.app.core.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import nghiem.app.core.utils.LogUtils;
import nghiem.app.core.utils.NumberUtils;
import nghiem.app.gen.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Environment;
import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

public class ImageManager
{
	private static final int MAX_CACHE_SIZE = 5 * 1024 * 1024;

	private static final LruCache<String, Bitmap> sBitmapCacher = new LruCache<String, Bitmap>(MAX_CACHE_SIZE);

	public static final Class<?> CLASS = ImageManager.class;
	public static final String COLOR_PREFIX = "?RGB=";
	public static final String FOLDER_DATA = "/data";

	private String mAppName;

	public ImageManager(Context context)
	{
		mAppName = context.getString(R.string.app_name);
	}

	/**
	 * Load the drawable to the image view
	 * 
	 * @param imageView
	 * @param link
	 *            with format: http_url_?RGB=color_code
	 * @return true if the link is applied (both null)
	 */
	public boolean loadDrawable(View imageView, String link, int viewWidth, int viewHeight, boolean useBackgroundTransitionAnimation)
	{
		if (TextUtils.isEmpty(link))
		{
			LogUtils.error(CLASS, "Load drawble from link --- null image URL!!!");
			return true;
		}

		String colorString = "";
		if (link.contains(COLOR_PREFIX))
		{
			int indexPrefix = link.indexOf(COLOR_PREFIX);
			colorString = link.substring(indexPrefix + COLOR_PREFIX.length());
			link = link.substring(0, indexPrefix);
		}

		Bitmap bitmap = sBitmapCacher.get(link);
		if (bitmap != null)
		{
			applyDrawableToViewWithAnimation(imageView, bitmap, useBackgroundTransitionAnimation);
			LogUtils.debug(CLASS, "Loaded bitmap from cach with id = " + link);
			return true;
		}

		bitmap = loadFromSdcard(link, viewWidth, viewHeight);
		if (bitmap != null)
		{
			applyDrawableToViewWithAnimation(imageView, bitmap, useBackgroundTransitionAnimation);
			LogUtils.debug(CLASS, "Loaded local image!");
			saveBitmap(link, bitmap);

			return true;
		}

		if (!TextUtils.isEmpty(colorString))
		{
			int color = getColorFromString(colorString);
			String hexString = "#" + NumberUtils.intToHexString6(color);
			LogUtils.debug(CLASS, "Load drawble from link --- Image Color = " + colorString + " => " + hexString);
			try
			{
				color = Color.parseColor(hexString);
			}
			catch (IllegalArgumentException exception)
			{
				color = Color.parseColor("#0000FF");
			}

			if (imageView instanceof ImageView)
			{
				((ImageView) imageView).setImageDrawable(new ColorDrawable(color));
			}
			else
			{
				imageView.setBackgroundColor(color);
			}
		}

		if (link == null || link.length() < 8)
		{
			// At least link = http://
			LogUtils.error(CLASS, "Load drawble from link --- Wrong URL!!! URL = " + link);
			return true;
		}

		return false;
	}

	public void clearCache()
	{
		sBitmapCacher.evictAll();
	}

	public void applyDrawableToView(View view, Drawable drawable)
	{
		if (view == null || drawable == null)
			return;

		if (view instanceof ImageView)
		{
			ImageView imageView = (ImageView) view;
			imageView.setImageDrawable(drawable);
			imageView.setVisibility(View.VISIBLE);
		}
		else
		{
			setBackground(view, drawable);
		}
	}

	public void applyDrawableToViewWithAnimation(View view, Bitmap bitmap, boolean useBackgroundTransitionAnimation)
	{
		if (view == null || bitmap == null)
			return;

		if (!useBackgroundTransitionAnimation)
		{
			applyDrawableToView(view, new BitmapDrawable(view.getResources(), bitmap));
			return;
		}

		Drawable[] drawables = { new ColorDrawable(android.R.color.transparent), new BitmapDrawable(view.getResources(), bitmap) };
		TransitionDrawable transitionDrawable = new TransitionDrawable(drawables);

		if (view instanceof ImageView)
		{
			ImageView imageView = (ImageView) view;
			imageView.setImageDrawable(transitionDrawable);
			imageView.setVisibility(View.VISIBLE);
		}
		else
		{
			setBackground(view, transitionDrawable);

		}
		transitionDrawable.startTransition(100);
	}

	public void saveToSdcard(String link, Bitmap bitmap, boolean useHighQuality)
	{
		if (TextUtils.isEmpty(link))
		{
			LogUtils.error(CLASS, "Save image to SDCard: cancel because URL is null");
			return;
		}
		if (bitmap == null)
		{
			LogUtils.error(CLASS, "Save image to SDCard: cancel because bitmap is null (link: " + link + ")");
			return;
		}
		if (!DeviceManager.isExternalStorageSupportWrite())
		{
			LogUtils.error(CLASS, "Save image to SDCard: cancel, there is no SDCard or it is read only");
			return;
		}

		File directory = new File(getImageFolderName());
		if (!directory.exists())
		{
			directory.mkdir();
		}

		String fileName = getFileName(link);

		File file = new File(directory.getAbsoluteFile(), fileName);
		if (!file.exists())
		{
			try
			{
				file.createNewFile();
			}
			catch (IOException e)
			{
				LogUtils.error(CLASS, "Save image to SDCard: error =  " + e.getMessage());
				return;
			}
		}
		else
		{
			LogUtils.debug(CLASS, "Save image to SDCard --- cancel - File is exists! " + link);
			LogUtils.debug(CLASS, "Save image to SDCard --- delete local file");
			file.delete();
		}

		LogUtils.debug(CLASS, "Save image to SDCard --- Save with new file");
		FileOutputStream fileOutputStream = null;
		try
		{
			fileOutputStream = new FileOutputStream(file);

			CompressFormat compressFormat = CompressFormat.JPEG;
			if (fileName.toUpperCase(Locale.getDefault()).contains("PNG"))
			{
				compressFormat = CompressFormat.PNG;
			}

			int quality = 100;
			if (useHighQuality)
			{
				quality = 75;
			}
			bitmap.compress(compressFormat, quality, fileOutputStream);

			fileOutputStream.flush();
		}
		catch (FileNotFoundException e)
		{
			LogUtils.error(CLASS, "Save image to SDCard: error =  " + e.getMessage());
			return;
		}
		catch (IOException e)
		{
			LogUtils.error(CLASS, "Save image to SDCard: error =  " + e.getMessage());
			file.delete();
			return;
		}
		finally
		{
			if (fileOutputStream != null)
			{
				try
				{
					fileOutputStream.close();
				}
				catch (IOException e)
				{
					LogUtils.error(CLASS, "Save image to SDCard: error =  " + e.getMessage());
				}
			}
		}
		LogUtils.debug(CLASS, "Saved image to SDCard Successfully! (link: " + link + ")");
	}

	/** Get image folder name on the SDCard */
	public String getImageFolderName()
	{
		return Environment.getExternalStorageDirectory().getAbsolutePath() + FOLDER_DATA + "/" + mAppName;
	}

	public void deleleFromSdcard(String linkWithColor)
	{
		if (TextUtils.isEmpty(linkWithColor))
		{
			LogUtils.error(CLASS, "Delete image from SDCard: cancel - Null link");
			return;
		}
		if (!DeviceManager.isExternalStorageSupportWrite())
		{
			LogUtils.error(CLASS, "Delete image from SDCard: cancel - There is no SDCard or it is read only");
			return;
		}

		String link = "";
		if (linkWithColor.contains(COLOR_PREFIX))
		{
			link = linkWithColor.substring(0, linkWithColor.indexOf(COLOR_PREFIX));
			if (link == null || link.length() < 8)
			{
				// At least link = http://
				LogUtils.error(CLASS, "Delete image from SDCard: cancel - wrong link: " + link);
				return;
			}
		}
		else
		{
			link = linkWithColor;
		}

		String fileName = getFileName(link);
		File file = new File(getImageFolderName(), fileName);
		if (file.exists())
		{
			file.delete();
			LogUtils.debug(CLASS, "Delete image from SDCard Successfully!!! (link: " + link + ")");
		}
		else
		{
			LogUtils.error(CLASS, "Delete image from SDCard: link is not cached yet!!! (link: " + link + ")");
		}
	}

	public Bitmap loadFromSdcard(String link, int viewWidth, int viewHeight)
	{
		if (TextUtils.isEmpty(link))
		{
			LogUtils.error(CLASS, "Load image from SDCard: cancel - Null link");
			return null;
		}
		if (!DeviceManager.isExternalStorageSupportWrite())
		{
			LogUtils.error(CLASS, "Load image from SDCard: cancel - There is no SDCard or it is read only");
			return null;
		}

		File file = new File(getImageFolderName(), getFileName(link));
		if (!file.exists())
		{
			LogUtils.error(CLASS, "Load image from SDCard: cancel - This was not saved to SDCard yet");
			return null;
		}

		try
		{
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;

			BitmapFactory.decodeFile(file.getAbsolutePath(), options);

			options.inSampleSize = calculateInSampleSize(options, viewWidth, viewHeight);
			options.inJustDecodeBounds = false;

			return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
		}
		catch (Throwable throwable)
		{
			LogUtils.error(CLASS, throwable.getMessage());
			System.gc();
		}
		return null;
	}

	/**
	 * Get file name from a link
	 * 
	 * @param link
	 * @return 
	 *         http://assets-cms-fiatchrysler-com-au.s3.amazonaws.com/apps/jeep/sponsor
	 *         /profile/steve-lee.png
	 *         <p>
	 *         => sponsor_profile_steve-lee.png
	 */
	private String getFileName(String link)
	{
		String linkParts[] = link.split("/");
		int len = linkParts.length;
		if (len < 3)
		{
			return "";
		}
		return linkParts[len - 3] + "_" + linkParts[len - 2] + "_" + linkParts[len - 1];
	}

	public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
	{
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth)
		{
			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth)
			{
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

	public void saveBitmap(String mLink, Bitmap bitmapTemp)
	{
		if (bitmapTemp == null || TextUtils.isEmpty(mLink))
		{
			return;
		}

		sBitmapCacher.put(mLink, bitmapTemp);
	}

	public Bitmap getBitmap(String link)
	{
		return sBitmapCacher.get(link);
	}

	public Bitmap fastBlur(Bitmap sentBitmap, int radius)
	{
		Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

		if (radius < 1)
		{
			return (null);
		}

		int w = bitmap.getWidth();
		int h = bitmap.getHeight();

		int[] pix = new int[w * h];
		bitmap.getPixels(pix, 0, w, 0, 0, w, h);

		int wm = w - 1;
		int hm = h - 1;
		int wh = w * h;
		int div = radius + radius + 1;

		int r[] = new int[wh];
		int g[] = new int[wh];
		int b[] = new int[wh];
		int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
		int vmin[] = new int[Math.max(w, h)];

		int divsum = (div + 1) >> 1;
		divsum *= divsum;
		int dv[] = new int[256 * divsum];
		for (i = 0; i < 256 * divsum; i++)
		{
			dv[i] = (i / divsum);
		}

		yw = yi = 0;

		int[][] stack = new int[div][3];
		int stackpointer;
		int stackstart;
		int[] sir;
		int rbs;
		int r1 = radius + 1;
		int routsum, goutsum, boutsum;
		int rinsum, ginsum, binsum;

		for (y = 0; y < h; y++)
		{
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			for (i = -radius; i <= radius; i++)
			{
				p = pix[yi + Math.min(wm, Math.max(i, 0))];
				sir = stack[i + radius];
				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);
				rbs = r1 - Math.abs(i);
				rsum += sir[0] * rbs;
				gsum += sir[1] * rbs;
				bsum += sir[2] * rbs;
				if (i > 0)
				{
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				}
				else
				{
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}
			}
			stackpointer = radius;

			for (x = 0; x < w; x++)
			{

				r[yi] = dv[rsum];
				g[yi] = dv[gsum];
				b[yi] = dv[bsum];

				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;

				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];

				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];

				if (y == 0)
				{
					vmin[x] = Math.min(x + radius + 1, wm);
				}
				p = pix[yw + vmin[x]];

				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);

				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];

				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;

				stackpointer = (stackpointer + 1) % div;
				sir = stack[(stackpointer) % div];

				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];

				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];

				yi++;
			}
			yw += w;
		}
		for (x = 0; x < w; x++)
		{
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			yp = -radius * w;
			for (i = -radius; i <= radius; i++)
			{
				yi = Math.max(0, yp) + x;

				sir = stack[i + radius];

				sir[0] = r[yi];
				sir[1] = g[yi];
				sir[2] = b[yi];

				rbs = r1 - Math.abs(i);

				rsum += r[yi] * rbs;
				gsum += g[yi] * rbs;
				bsum += b[yi] * rbs;

				if (i > 0)
				{
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				}
				else
				{
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}

				if (i < hm)
				{
					yp += w;
				}
			}
			yi = x;
			stackpointer = radius;
			for (y = 0; y < h; y++)
			{
				// Preserve alpha channel: ( 0xff000000 & pix[yi] )
				pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;

				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];

				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];

				if (x == 0)
				{
					vmin[y] = Math.min(y + r1, hm) * w;
				}
				p = x + vmin[y];

				sir[0] = r[p];
				sir[1] = g[p];
				sir[2] = b[p];

				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];

				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;

				stackpointer = (stackpointer + 1) % div;
				sir = stack[stackpointer];

				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];

				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];

				yi += w;
			}
		}
		bitmap.setPixels(pix, 0, w, 0, 0, w, h);

		return (bitmap);
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	public void setBackground(View view, Drawable drawable)
	{
		// bitmap = Utils.fastBlur(bitmap, 10);
		// actionBarBackground.mutate().setAlpha(Config.ALPHA_ACTION_BAR);

		if (Build.VERSION.SDK_INT < 16)
		{
			view.setBackgroundDrawable(drawable);
		}
		else
		{
			view.setBackground(drawable);
		}
	}

	public Bitmap rotateBitmap(Bitmap source, float angle)
	{
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
	}

	/**
	 * Get the color value from the string of RGB.
	 * 
	 * @param colorString
	 *            Example: "200200200"
	 * @return color code integer
	 */
	public static int getColorFromString(String colorString)
	{
		int color = 0xFF;
		int elementCode;
		String elementStr;
		try
		{
			elementStr = colorString.substring(0, 3);
			elementCode = Integer.parseInt(elementStr);
			color = elementCode * 16 * 16 * 16 * 16;

			elementStr = colorString.substring(3, 6);
			elementCode = Integer.parseInt(elementStr);
			color += elementCode * 16 * 16;

			elementStr = colorString.substring(6, 9);
			elementCode = Integer.parseInt(elementStr);
			color += elementCode;
		}
		catch (NumberFormatException e)
		{
		}
		return color;
	}
}
