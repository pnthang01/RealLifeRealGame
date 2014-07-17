package nghiem.app.core.application;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.UUID;

import nghiem.app.core.utils.LogUtils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class DeviceManager
{
    public static final String TAG = DeviceManager.class.getName();

    private static DeviceManager sInstance;

    private Context mContext;

    public static DeviceManager getInstance()
    {
        if (sInstance == null)
        {
            synchronized (DeviceManager.class)
            {
                if (sInstance == null)
                {
                    sInstance = new DeviceManager();
                }
            }
        }
        return sInstance;
    }

    private DeviceManager()
    {
        mContext = NghiemBaseApp.getInstance();
    }

    public String generateUuid()
    {
        String uuid = "";
        String androidId = Secure.getString(mContext.getContentResolver(),
                Secure.ANDROID_ID);

        if (TextUtils.isEmpty(androidId))
        {
            androidId = ((TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        }
        if (!TextUtils.isEmpty(androidId)
                && !"9774d56d682e549c".equals(androidId))
        {
            byte[] idEncode;
            try
            {
                idEncode = androidId.getBytes("utf8");
            } catch (UnsupportedEncodingException e)
            {
                idEncode = androidId.getBytes();
            }

            String newUuid = UUID.nameUUIDFromBytes(idEncode).toString();

            uuid = newUuid;
        }
        else if (TextUtils.isEmpty(uuid))
        {
            uuid = "FAKE" + UUID.randomUUID().toString().substring(4);
        }
        LogUtils.log(TAG, "###### Your Device UUID is " + uuid + " ######");
        return uuid;
    }

    public String getKeystoreHashkey(String packageId)
    {
        try
        {
            PackageInfo info = mContext.getPackageManager().getPackageInfo(
                    packageId, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.DEFAULT);
            }
        } catch (Exception e)
        {
            LogUtils.logException(e);
        }
        return "";
    }

    public int getScreenWidth()
    {
        DisplayMetrics displayMetrics = getCurrentDisplayMetrics();
        return Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public int getScreenHeight()
    {
        DisplayMetrics displayMetrics = getCurrentDisplayMetrics();
        return Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public float getScreenDensity()
    {
        return getCurrentDisplayMetrics().density;
    }

    private DisplayMetrics getCurrentDisplayMetrics()
    {
        WindowManager sWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = sWindowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return displayMetrics;
    }
}