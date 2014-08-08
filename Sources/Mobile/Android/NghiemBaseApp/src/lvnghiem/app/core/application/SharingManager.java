package lvnghiem.app.core.application;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;

import lvnghiem.app.core.settings.Settings;

import nghiem.app.gen.R;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;

public class SharingManager extends BaseController
{
	@SuppressWarnings("rawtypes")
	private static ThreadLocal sInitHolder = new ThreadLocal();
	private static SharingManager sInstance;

	private Context mContext;

	@SuppressWarnings("unchecked")
	public static SharingManager getInstance()
	{
		if (sInitHolder.get() == null)
		{
			synchronized (DeviceManager.class)
			{
				if (sInstance == null)
				{
					sInstance = new SharingManager();
				}
				sInitHolder.set(Boolean.TRUE);
			}
		}
		return sInstance;
	}

	private SharingManager()
	{
		mContext = NghiemBaseApp.getInstance();
	}

	public void shareOnFacebook(String caption, String text, String facebookShareLink)
	{
		caption = urlEncode(caption);
		text = urlEncode(text);

		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, caption);
		intent.putExtra(Intent.EXTRA_TITLE, text);
		intent.putExtra(Intent.EXTRA_TEXT, text + " " + facebookShareLink);

		boolean facebookAppFound = processIntent(intent, "com.facebook");
		if (!facebookAppFound)
		{
			String sharerUrl = mContext.getString(R.string.facebook_link) + "?u=" + facebookShareLink;
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
		}
		mContext.startActivity(intent);
	}

	public void shareOnTwitter(String message)
	{
		String tweetUrl = String.format(mContext.getString(R.string.twitter_link) + "?text=%s", message);
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));
		processIntent(intent, "com.twitter");
		mContext.startActivity(intent);
	}

	private boolean processIntent(Intent intent, String packageName)
	{
		List<ResolveInfo> matches = mContext.getPackageManager().queryIntentActivities(intent, 0);
		for (ResolveInfo info : matches)
		{
			if (info.activityInfo.packageName.toLowerCase(Locale.getDefault()).startsWith(packageName))
			{
				intent.setPackage(info.activityInfo.packageName);
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	private String urlEncode(String text)
	{
		try
		{
			return URLEncoder.encode(text, Settings.CHARSET);
		}
		catch (UnsupportedEncodingException e)
		{
			LOG.debug(Settings.CHARSET + " should always be supported");
			return URLEncoder.encode(text);
		}
	}
}
