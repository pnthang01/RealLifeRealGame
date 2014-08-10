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
	private static SharingManager sInstance;

	private Context mContext;

	public static SharingManager getInstance()
	{
		if (sInstance == null)
		{
			synchronized (SharingManager.class)
			{
				if (sInstance == null)
				{
					sInstance = new SharingManager();
				}
			}
		}
		return sInstance;
	}

	private SharingManager()
	{
		mContext = NghiemBaseApp.getInstance();
	}

	public void shareOnFacebook(String subject, String text)
	{
		subject = urlEncode(subject);
		text = urlEncode(text);
		String facebookShareLink = NghiemBaseApp.getInstance().getString(R.string.app_url);

		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		intent.putExtra(Intent.EXTRA_TITLE, NghiemBaseApp.getInstance().getString(R.string.app_name));
		intent.putExtra(Intent.EXTRA_TEXT, text + " " + facebookShareLink);

		boolean facebookAppFound = processIntent(intent, "com.facebook");
		if (!facebookAppFound)
		{
			String sharerUrl = mContext.getString(R.string.settings_facebook_link) + "?u=" + facebookShareLink;
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
		}
		mContext.startActivity(intent);
	}

	public void shareOnTwitter(String message)
	{
		String tweetUrl = String.format(mContext.getString(R.string.settings_twitter_link) + "?text=%s", message);
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
