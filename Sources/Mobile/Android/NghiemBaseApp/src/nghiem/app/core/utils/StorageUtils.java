package nghiem.app.core.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

import nghiem.app.gen.BuildConfig;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Environment;

public class StorageUtils
{
	private static final Class<?> CLASS = StorageUtils.class;

	public static class StorageInfo
	{
		public final String path;
		public final boolean internal;
		public final boolean readonly;
		public final int display_number;

		StorageInfo(String path, boolean internal, boolean readonly, int display_number)
		{
			this.path = path;
			this.internal = internal;
			this.readonly = readonly;
			this.display_number = display_number;
		}

		public String getDisplayName()
		{
			StringBuilder res = new StringBuilder();
			if (internal)
			{
				res.append("Internal SD card");
			}
			else if (display_number > 1)
			{
				res.append("SD card " + display_number);
			}
			else
			{
				res.append("SD card");
			}
			if (readonly)
			{
				res.append(" (Read only)");
			}
			return res.toString();
		}
	}

	@SuppressLint("NewApi")
	public static List<StorageInfo> getStorageList()
	{
		List<StorageInfo> list = new ArrayList<StorageInfo>();
		String path = Environment.getExternalStorageDirectory().getPath();
		boolean isInternal = false;
		if (Build.VERSION.SDK_INT > 8)
		{
			isInternal = !Environment.isExternalStorageRemovable();
		}
		String state = Environment.getExternalStorageState();
		boolean isReadonly = state.equals(Environment.MEDIA_MOUNTED_READ_ONLY);
		boolean isAvailable = state.equals(Environment.MEDIA_MOUNTED) || isReadonly;
		try (BufferedReader reader = new BufferedReader(new FileReader("/proc/mounts")))
		{
			HashSet<String> paths = new HashSet<String>();
			String line;
			int currentDisplayNumber = 1;
			LogUtils.debug(CLASS, "/proc/mounts");
			while ((line = reader.readLine()) != null)
			{
			    LogUtils.debug(CLASS, line);
				if (line.contains("vfat") || line.contains("/mnt"))
				{
					StringTokenizer tokens = new StringTokenizer(line, " ");
					String mountPoint = tokens.nextToken(); // mount point
					if (paths.contains(mountPoint))
					{
						continue;
					}
					List<String> flags = Arrays.asList(tokens.nextToken().split(",")); // flags
					boolean readonly = flags.contains("ro");

					if (mountPoint.equals(path))
					{
						paths.add(path);
						list.add(0, new StorageInfo(path, isInternal, readonly, -1));
					}
					else if (line.contains("/dev/block/vold"))
					{
						if (!line.contains("/mnt/secure") && !line.contains("/mnt/asec") && !line.contains("/mnt/obb") && !line.contains("/dev/mapper") && !line.contains("tmpfs"))
						{
							paths.add(mountPoint);
							list.add(new StorageInfo(mountPoint, false, readonly, currentDisplayNumber++));
						}
					}
				}
			}

			if (!paths.contains(path) && isAvailable)
			{
				list.add(0, new StorageInfo(path, isInternal, isReadonly, -1));
			}
		}
		catch (FileNotFoundException ex)
		{
			LogUtils.error(CLASS, ex.getMessage());
		}
		catch (IOException ex)
		{
			LogUtils.error(CLASS, ex.getMessage());
		}
		return list;
	}

	public static void debugStorages()
	{
		if (BuildConfig.DEBUG)
		{
			List<StorageInfo> storageInfos = getStorageList();
			for (StorageInfo storageInfo : storageInfos)
			{
				LogUtils.debug(CLASS, storageInfo.getDisplayName());
			}
		}
	}

	/**
	 * Get the content from a byte buffer
	 * 
	 * @param buffer
	 * @return content as String
	 */
	public static String getContentFromBuffer(ByteArrayOutputStream buffer, String charset)
	{
		String content = null;
		try
		{
			content = buffer.toString(charset);
		}
		catch (UnsupportedEncodingException e)
		{
			LogUtils.error(CLASS, e);

			// Try with standard encode
			content = buffer.toString();
		}

		return content;
	}
}
