package nghiem.app.core.application;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;

import nghiem.app.core.settings.Settings;
import nghiem.app.core.utils.LogUtils;
import nghiem.app.gen.R;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;


public class SharingManager
{
    public static final String TAG = SharingManager.class.getName();
    
    private static SharingManager sInstance;
    
    private Context mContext;

    public static SharingManager getInstance()
    {
        if (sInstance == null)
        {
            synchronized (DeviceManager.class)
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
    
    public void shareOnFacebook(String caption, String text, String facebookShareLink)
    {
        caption = urlEncode(caption);
        text = urlEncode(text);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, caption);
        intent.putExtra(Intent.EXTRA_TITLE, text);
        intent.putExtra(Intent.EXTRA_TEXT, text + " " + facebookShareLink);

        boolean facebookAppFound = false;
        List<ResolveInfo> matches = mContext.getPackageManager().queryIntentActivities(intent, 0);

        for (ResolveInfo info : matches)
        {
            if (info.activityInfo.packageName.toLowerCase(Locale.getDefault()).startsWith("com.facebook"))
            {
                intent.setPackage(info.activityInfo.packageName);
                facebookAppFound = true;
                break;
            }
        }
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

        List<ResolveInfo> matches = mContext.getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : matches)
        {
            if (info.activityInfo.packageName.toLowerCase(Locale.getDefault()).startsWith("com.twitter"))
            {
                intent.setPackage(info.activityInfo.packageName);
                break;
            }
        }
        mContext.startActivity(intent);
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
            LogUtils.log(TAG, Settings.CHARSET + " should always be supported");
            return URLEncoder.encode(text);
        }
    }
}
