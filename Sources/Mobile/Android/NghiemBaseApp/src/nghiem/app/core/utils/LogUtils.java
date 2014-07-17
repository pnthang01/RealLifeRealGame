package nghiem.app.core.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import nghiem.app.gen.BuildConfig;
import android.os.Build;
import android.util.Log;

public class LogUtils
{
	private static final String LOG_PREFIX = "@@@@@@@@@@@@ ";

	private LogUtils()
	{
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

	public static void logError(String tag, String string, Throwable throwable)
	{
		if (BuildConfig.DEBUG)
		{
			Log.e(tag, LOG_PREFIX + string, throwable);
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
	 * Log the error or exception to the Logcat (in debug mode) and send mail to
	 * admin
	 * 
	 * @param tag
	 * @param throwable
	 */
	public static void logErrorAndSendMail(String tag, Throwable throwable)
	{
		if (BuildConfig.DEBUG)
		{
			Log.e(tag, "", throwable);
		}

		String errorReport = getErrorReport(throwable);
		EmailUtils.sendMail(throwable.getMessage() + "", errorReport);
	}

	/**
	 * Get the error as a report (for send mail,..)
	 * 
	 * @param throwable
	 *            the error or exception thrown
	 * @return text with error content and full device information
	 */
	private static String getErrorReport(Throwable throwable)
	{
		String separator = "\n";
		StringWriter stackTrace = new StringWriter();
		StringBuilder errorReport = new StringBuilder();

		if (BuildConfig.DEBUG)
		{
			throwable.printStackTrace(new PrintWriter(stackTrace));
		}

		errorReport.append("************ CAUSE OF ERROR ************");
		errorReport.append(separator);
		errorReport.append(stackTrace.toString());
		errorReport.append(separator);
		errorReport.append(separator);

		errorReport.append("************ DEVICE INFORMATION ***********");
		errorReport.append(separator);
		errorReport.append("Brand: ");
		errorReport.append(Build.BRAND);
		errorReport.append(separator);
		errorReport.append("Device: ");
		errorReport.append(Build.DEVICE);
		errorReport.append(separator);
		errorReport.append("Model: ");
		errorReport.append(Build.MODEL);
		errorReport.append(separator);
		errorReport.append("Id: ");
		errorReport.append(Build.ID);
		errorReport.append(separator);
		errorReport.append("Product: ");
		errorReport.append(Build.PRODUCT);
		errorReport.append(separator);
		errorReport.append(separator);

		errorReport.append("************ FIRMWARE ************");
		errorReport.append(separator);
		errorReport.append("SDK: ");
		errorReport.append(Build.VERSION.SDK_INT);
		errorReport.append(separator);
		errorReport.append("Release: ");
		errorReport.append(Build.VERSION.RELEASE);
		errorReport.append(separator);
		errorReport.append("Incremental: ");
		errorReport.append(Build.VERSION.INCREMENTAL);
		errorReport.append(separator);

		return errorReport.toString();
	}
}
