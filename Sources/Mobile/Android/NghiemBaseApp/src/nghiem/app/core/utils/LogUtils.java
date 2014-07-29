package nghiem.app.core.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import nghiem.app.gen.BuildConfig;

import org.slf4j.LoggerFactory;

import android.os.Build;

public class LogUtils
{
	private LogUtils()
	{
	}

	public static void debug(Class<?> clazz, String string)
	{
		if (BuildConfig.DEBUG)
		{
		    LoggerFactory.getLogger(clazz).debug(string);
		}
	}

	public static void error(Class<?> clazz, String string)
	{
		if (BuildConfig.DEBUG)
		{
			if (Thread.currentThread().getStackTrace().length < 5)
			{
			    LoggerFactory.getLogger(clazz).debug(string);
				return;
			}
			StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
			String fullClassName = stackTraceElement.getClassName();
			String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
			String methodName = stackTraceElement.getMethodName();
			int lineNumber = stackTraceElement.getLineNumber();

			LoggerFactory.getLogger(clazz).debug(string + " (" + className + "." + methodName + "():" + lineNumber + ")");
		}
	}

    public static void error(Class<?> clazz, String string, Exception e)
    {
        if (BuildConfig.DEBUG)
        {
            LoggerFactory.getLogger(clazz).debug(string, e);
        }
    }

    public static void error(Class<?> clazz, Exception e)
    {
        if (BuildConfig.DEBUG)
        {
            LoggerFactory.getLogger(clazz).debug("", e);
        }
    }

    public static void error(Class<?> clazz, String string, Throwable throwable)
    {
        if (BuildConfig.DEBUG)
        {
            LoggerFactory.getLogger(clazz).debug(string, throwable);
        }
    }

	/**
	 * Log the error or exception to the Logcat (in debug mode) and send mail to
	 * admin
	 * 
	 * @param tag
	 * @param throwable
	 */
	public static void errorAndSendMail(Class<?> clazz, Throwable throwable)
	{
		if (BuildConfig.DEBUG)
		{
		    LoggerFactory.getLogger(clazz).debug("", throwable);
		}
		else
		{
			String errorReport = getErrorReport(throwable);
			LoggerFactory.getLogger(clazz).error(errorReport);
		}
	}

	/**
	 * Get the error as a report (for send mail,..)
	 * 
	 * @param throwable
	 *            the error or exception thrown
	 * @return text with error content and full device information
	 */
	public static String getErrorReport(Throwable throwable)
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
