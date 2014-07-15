package nghiem.app.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import nghiem.app.gen.BuildConfig;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;


public class Utils
{
	private static final String LOG_PREFIX = "@@@@@@@@@@@@ ";

	private Utils()
    {
    }
	
	public static String intToHexString(int number)
    {
        return Integer.toHexString(number);
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

	public static void showToast(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showDebugToast(Context context, String message)
    {
        if (BuildConfig.DEBUG)
        {
            showToast(context, message);
            log(context.getClass().toString(), message);
        }
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
