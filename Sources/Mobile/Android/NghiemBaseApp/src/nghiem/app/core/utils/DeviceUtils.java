package nghiem.app.core.utils;

import android.content.Context;
import android.location.LocationManager;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class DeviceUtils
{
    private DeviceUtils()
    {
    }

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

    public static boolean isGpsEnabled(Context context)
    {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static final boolean isExternalStorageSupportWrite()
    {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
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
}
