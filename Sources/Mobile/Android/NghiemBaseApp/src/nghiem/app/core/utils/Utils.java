package nghiem.app.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import nghiem.app.gen.BuildConfig;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


public class Utils
{
	private static final String LOG_PREFIX = "@@@@@@@@@@@@ ";
	
	public static boolean isGoogleMapsInstalled(Context context)
	{
		try
		{
			context.getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public static final boolean isExternalStorageSupportWrite()
	{
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
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

	public static long convertTimeUtcToLocal(String timeStr)
	{
		DateTime dateTimeUtc = new DateTime(timeStr);
		DateTime dateTimeLocal = dateTimeUtc.withZone(DateTimeZone.getDefault());
		return dateTimeLocal.getMillis();
	}

	public static String convertTimeLocalToUtc(long time)
	{
		DateTime dateTimeLocal = new DateTime(time);
		DateTime dateTimeUtc = dateTimeLocal.withZone(DateTimeZone.UTC);
		return dateTimeUtc.toString();
	}

	public static String getTimeHour(String time)
	{
		if (TextUtils.isEmpty(time))
		{
			return "";
		}

		long millis = convertTimeUtcToLocal(time);
		Date date = new Date(millis);
		return new SimpleDateFormat("dd MMM yyyy, hh:mma", Locale.getDefault()).format(date);
	}

	public static String getTimeDifference(long time)
	{
		long currentTime = System.currentTimeMillis();
		if (currentTime <= time)
		{
			return "";
		}

		int seconds = (int) ((currentTime - time) / 1000);
		int minutes = seconds / 60;
		int hours = minutes / 60;
		int days = hours / 24;

		if (days > 0)
			return days + " " + (days == 1 ? "day" : "days");

		return "Today";
	}

	public static String intToHexString(int number)
	{
		return Integer.toHexString(number);
	}

	public static void showDebugToast(Context context, String message)
	{
		if (BuildConfig.DEBUG)
		{
			showToast(context, message);
			log(context.getClass().toString(), message);
		}
	}

	public static void showToast(Context context, String message)
	{
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	/**
	 * Full 6 number in the string
	 * 
	 * @param number
	 * @return example: 0023FF
	 */
	public static String intToHexString6(int number)
	{
		String hex = Integer.toHexString(number);

		for (int index = hex.length(); index < 6; index++)
		{
			hex = "0" + hex;
		}

		return hex;
	}

	public static int hexStringToInt(String hexString)
	{
		return (int) Long.parseLong(hexString, 16);
	}

	public static void showKeyboard(Context context, View view, boolean isShowed)
	{
		InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (isShowed)
		{
			inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
		}
		else
		{
			inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	public static Bitmap fastBlur(Bitmap sentBitmap, int radius)
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

	public static void log(String tag, String string)
	{
		if (BuildConfig.DEBUG)
		{
			Log.d(tag, LOG_PREFIX + string);
		}
	}

	public static void logError(String tag, String string)
	{
		if (BuildConfig.DEBUG)
		{
			if (Thread.currentThread().getStackTrace().length < 5)
			{
				Log.e(tag, LOG_PREFIX + string);
				return;
			}
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
			String fullClassName = stackTraceElement.getClassName();
			String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
			String methodName = stackTraceElement.getMethodName();
			int lineNumber = stackTraceElement.getLineNumber();

			Log.e(tag, LOG_PREFIX + string + " (" + className + "." + methodName + "():" + lineNumber + ")");
		}
	}

	public static void logError(String tag, String string, Exception e)
	{
		if (BuildConfig.DEBUG)
		{
			Log.e(tag, LOG_PREFIX + string, e);
		}
	}

	public static void logException(Exception e)
	{
		if (BuildConfig.DEBUG)
		{
			e.printStackTrace();
		}
	}

	/**
	 * The log-cat string length is only about 3300 characters. When the logged
	 * text exceeds this length, it will be split to print.
	 * 
	 * @param tag
	 * @param longText
	 */
	public static void logLongText(String tag, String longText)
	{
		String textPrint = "";
		String textRemain = longText;

		// The log-cat string length is only about 3300 characters.
		final int LOG_CAT_LENGTH_ALLOWED = 3000;
		while (textRemain.length() > LOG_CAT_LENGTH_ALLOWED)
		{
			textPrint = textRemain.substring(0, LOG_CAT_LENGTH_ALLOWED);
			log(tag, "Json get = " + textPrint);

			textRemain = textRemain.substring(LOG_CAT_LENGTH_ALLOWED);
		}
		log(tag, "Json get = " + textRemain);
	}

	/**
	 * Loading Joda library for fix slowly process
	 */
	public static void fixJodaLoadSlowly()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				Utils.convertTimeUtcToLocal("2014-02-17T14:04:39.371Z");
			}
		}).start();
	}

	public static Bitmap rotateBitmap(Bitmap source, float angle)
	{
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
	}

	/**
	 * Get the content from a byte buffer
	 * 
	 * @param buffer
	 * @return content as String
	 */
	public static String getContentFromBuffer(ByteArrayOutputStream buffer, String charset)
	{
		String content = null;
		try
		{
			content = buffer.toString(charset);
		}
		catch (UnsupportedEncodingException e)
		{
			Utils.logException(e);

			// Try with standard encode
			content = buffer.toString();
		}

		return content;
	}
}
