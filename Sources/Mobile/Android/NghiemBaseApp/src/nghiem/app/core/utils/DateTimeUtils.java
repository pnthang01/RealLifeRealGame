package nghiem.app.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import android.text.TextUtils;

public class DateTimeUtils
{
    private DateTimeUtils()
    {
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
        {
            return days + " " + (days == 1 ? "day" : "days");
        }

        return "Today";
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
                convertTimeUtcToLocal("2014-02-17T14:04:39.371Z");
            }
        }).start();
    }
}
