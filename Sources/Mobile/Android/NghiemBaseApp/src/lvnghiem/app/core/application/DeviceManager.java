package lvnghiem.app.core.application;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.UUID;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public final class DeviceManager extends BaseController
{
	public static final String GOOGLE_MAP_PACKAGE = "com.google.android.apps.maps";

	@SuppressWarnings("rawtypes")
	private static ThreadLocal sInitHolder = new ThreadLocal();
	private static DeviceManager sInstance;

	private Context mContext;

	@SuppressWarnings("unchecked")
	public static DeviceManager getInstance()
	{
		if (sInitHolder.get() == null)
		{
			synchronized (DeviceManager.class)
			{
				if (sInstance == null)
				{
					sInstance = new DeviceManager();
				}
				sInitHolder.set(Boolean.TRUE);
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
		String androidId = Secure.getString(mContext.getContentResolver(), Secure.ANDROID_ID);

		if (TextUtils.isEmpty(androidId))
		{
			androidId = ((TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
		}
		if (!TextUtils.isEmpty(androidId) && !"9774d56d682e549c".equals(androidId))
		{
			byte[] idEncode;
			try
			{
				idEncode = androidId.getBytes("utf8");
			}
			catch (UnsupportedEncodingException e)
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
		LOG.debug("###### Your Device UUID is " + uuid + " ######");
		return uuid;
	}

	public String getKeystoreHashkey(String packageId)
	{
		try
		{
			PackageInfo info = mContext.getPackageManager().getPackageInfo(packageId, PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures)
			{
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				return Base64.encodeToString(md.digest(), Base64.DEFAULT);
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
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

	public boolean isPackageInstalled(String packageName)
	{
		try
		{
			mContext.getPackageManager().getApplicationInfo(packageName, 0);
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
	public final boolean isNetworkConnected()
	{
		ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		boolean isConnected = networkInfo != null && networkInfo.isConnected();

		if (!isConnected)
		{
			LOG.debug("No Internet!!!!!!!!!");
		}

		return isConnected;
	}

	public boolean isGpsEnabled()
	{
		LocationManager manager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
		return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}

	public static boolean isExternalStorageSupportWrite()
	{
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
}
