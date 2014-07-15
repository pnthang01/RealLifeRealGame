package nghiem.app.core.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import nghiem.app.core.mail.Mail;
import nghiem.app.core.utils.Utils;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

/**
 * The handler of exceptions, and process sending mail to developer's mail
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler
{
    private Context mContext;

    public ExceptionHandler(Context context)
    {
        mContext = context;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable)
    {
        String separator = "\n";

        StringWriter stackTrace = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stackTrace));
        StringBuilder errorReport = new StringBuilder();
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

        Utils.logError(getClass().getName(), errorReport.toString());
        sendMail(errorReport.toString());

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }

    private void sendMail(String message)
    {
        Mail mail = new Mail("le.vinhnghiem@gmail.com", "Nghiem");

        String[] toArr = { "le.vinhnghiem@gmail.com" };
        mail.setTos(toArr);
        mail.setSubject("Application Crash");
        mail.setBody(message);

        try
        {
            if (mail.send())
            {
                Toast.makeText(mContext, "Email was sent successfully.",
                        Toast.LENGTH_LONG).show();
                Utils.logError(getClass().getName(),
                        "Email was sent successfully.");
            } else
            {
                Toast.makeText(mContext, "Email was not sent.",
                        Toast.LENGTH_LONG).show();
                Utils.logError(getClass().getName(), "Email was not sent!");
            }
        } catch (Exception e)
        {
            Utils.logError("MailApp", "Could not send email " + e.getMessage());
        }
    }

}
