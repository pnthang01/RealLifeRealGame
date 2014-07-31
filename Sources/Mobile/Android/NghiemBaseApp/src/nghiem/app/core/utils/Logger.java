package nghiem.app.core.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import nghiem.app.gen.BuildConfig;

import org.slf4j.LoggerFactory;

import android.os.Build;

public class Logger
{
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

    public void error(String string)
    {
        if (BuildConfig.DEBUG)
        {
            if (Thread.currentThread().getStackTrace().length < 5)
            {
                mLogger.debug(string);
                return;
            }
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
            String fullClassName = stackTraceElement.getClassName();
            String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
            String methodName = stackTraceElement.getMethodName();
            int lineNumber = stackTraceElement.getLineNumber();

            mLogger.debug(string + " (" + className + "." + methodName + "():"
                    + lineNumber + ")");
        }
    }

    public void error(String string, Exception e)
    {
        if (BuildConfig.DEBUG)
        {
            mLogger.debug(string, e);
        }
    }

    public void error(Exception e)
    {
        if (BuildConfig.DEBUG)
        {
            mLogger.debug("", e);
        }
    }

    public void error(String string, Throwable throwable)
    {
        if (BuildConfig.DEBUG)
        {
            mLogger.debug(string, throwable);
        }
    }

    /**
     * Log the error or exception to the Logcat (in debug mode) and send mail to
     * admin
     * 
     * @param throwable
     */
    public void errorAndSendMail(Throwable throwable)
    {
        if (BuildConfig.DEBUG)
        {
            mLogger.debug("", throwable);
        }
        else
        {
            String errorReport = getErrorReport(throwable);
            mLogger.error(errorReport);
        }
    }

    /**
     * Get the error as a report (for send mail,..)
     * 
     * @param throwable
     *            the error or exception thrown
     * @return text with error content and full device information
     */
    public String getErrorReport(Throwable throwable)
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
