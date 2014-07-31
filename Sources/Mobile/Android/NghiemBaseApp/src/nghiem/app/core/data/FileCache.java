package nghiem.app.core.data;

import java.io.File;

import nghiem.app.core.application.DeviceManager;
import android.content.Context;
import android.os.Environment;

public class FileCache
{
    private File mCacheDir;

    public FileCache(Context context)
    {
        if (DeviceManager.isExternalStorageSupportWrite())
        {
            mCacheDir = new File(Environment.getExternalStorageDirectory(),
                    "temp_images");
        }
        else if (context != null)
        {
            mCacheDir = context.getCacheDir();
        }
        else
        {
            mCacheDir = null;
        }

        if (mCacheDir != null && !mCacheDir.exists())
        {
            mCacheDir.mkdirs();
        }
    }

    public File getFile(String url)
    {
        if (mCacheDir != null)
        {
            return new File(mCacheDir, String.valueOf(url.hashCode()));
        }
        else
        {
            return null;
        }
    }

    public void clear()
    {
        if (mCacheDir == null)
        {
            return;
        }

        File[] files = mCacheDir.listFiles();
        if (files == null)
        {
            return;
        }
        for (File file : files)
        {
            file.delete();
        }
    }
}