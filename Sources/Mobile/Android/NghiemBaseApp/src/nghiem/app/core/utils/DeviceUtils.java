package nghiem.app.core.utils;

import nghiem.app.core.application.NghiemBaseApp;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class DeviceUtils
{
    public static final String TAG = DeviceUtils.class.getName();
    
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

    /**
     * Check network is connected
     */
    public static final boolean isNetworkConnected()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) NghiemBaseApp.getInstance().getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnected();

        if (!isConnected)
        {
            LogUtils.logError(TAG, "No Internet!!!!!!!!!");
        }

        return isConnected;
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
