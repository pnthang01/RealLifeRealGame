package lvnghiem.app.core.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import nghiem.app.gen.BuildConfig;

import org.slf4j.LoggerFactory;

import android.os.Build;

public class Logger
{
    private static final String SERPERATOR = "\n";

	private org.slf4j.Logger mLogger;

	public Logger(Class<?> clazz)
	{
		mLogger = LoggerFactory.getLogger(clazz);
	}

	public void debug(String string)
    {
        if (BuildConfig.DEBUG)
        {
            mLogger.debug(string);
        }
    }

    public void debug(Throwable throwable)
    {
        if (BuildConfig.DEBUG)
        {
            mLogger.debug(throwable.toString());
        }
    }

    public void debug(String string, Throwable throwable)
    {
        if (BuildConfig.DEBUG)
        {
            mLogger.debug(string, throwable);
        }
    }

    public void error(Throwable throwable)
    {
        if (BuildConfig.DEBUG)
        {
            mLogger.debug(throwable.toString());
        }
        else
        {
            String errorReport = getErrorReport(throwable);
            mLogger.error(errorReport);
        }
    }

    public void error(String string, Throwable throwable)
    {
        if (BuildConfig.DEBUG)
        {
            mLogger.debug(string, throwable);
        }
        else
        {
            String errorReport = getErrorReport(throwable);
            mLogger.error(string + SERPERATOR + errorReport);
        }
    }

	public String getErrorReport(Throwable throwable)
	{
		StringWriter stackTrace = new StringWriter();
		StringBuilder errorReport = new StringBuilder();

		if (BuildConfig.DEBUG)
		{
			throwable.printStackTrace(new PrintWriter(stackTrace));
		}

		errorReport.append("************ CAUSE OF ERROR ************");
		errorReport.append(SERPERATOR);
		errorReport.append(stackTrace.toString());
		errorReport.append(SERPERATOR);
		errorReport.append(SERPERATOR);

		errorReport.append("************ DEVICE INFORMATION ***********");
		errorReport.append(SERPERATOR);
		errorReport.append("Brand: ");
		errorReport.append(Build.BRAND);
		errorReport.append(SERPERATOR);
		errorReport.append("Device: ");
		errorReport.append(Build.DEVICE);
		errorReport.append(SERPERATOR);
		errorReport.append("Model: ");
		errorReport.append(Build.MODEL);
		errorReport.append(SERPERATOR);
		errorReport.append("Id: ");
		errorReport.append(Build.ID);
		errorReport.append(SERPERATOR);
		errorReport.append("Product: ");
		errorReport.append(Build.PRODUCT);
		errorReport.append(SERPERATOR);
		errorReport.append(SERPERATOR);

		errorReport.append("************ FIRMWARE ************");
		errorReport.append(SERPERATOR);
		errorReport.append("SDK: ");
		errorReport.append(Build.VERSION.SDK_INT);
		errorReport.append(SERPERATOR);
		errorReport.append("Release: ");
		errorReport.append(Build.VERSION.RELEASE);
		errorReport.append(SERPERATOR);
		errorReport.append("Incremental: ");
		errorReport.append(Build.VERSION.INCREMENTAL);
		errorReport.append(SERPERATOR);

		return errorReport.toString();
	}
}
