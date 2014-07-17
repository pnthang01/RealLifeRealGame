package nghiem.app.core.manager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

import nghiem.app.core.utils.LogUtils;
import nghiem.app.gen.BuildConfig;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

public class StorageManager
{
	private static final String TAG = StorageManager.class.getName();

	public class StorageInfo
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
	public List<StorageInfo> getStorageList()
	{
		List<StorageInfo> list = new ArrayList<StorageInfo>();
		String def_path = Environment.getExternalStorageDirectory().getPath();
		boolean def_path_internal = false;
		if (Build.VERSION.SDK_INT > 8)
		{
			def_path_internal = !Environment.isExternalStorageRemovable();
		}
		String def_path_state = Environment.getExternalStorageState();
		boolean def_path_readonly = def_path_state.equals(Environment.MEDIA_MOUNTED_READ_ONLY);
		boolean def_path_available = def_path_state.equals(Environment.MEDIA_MOUNTED) || def_path_readonly;
		try (BufferedReader buf_reader = new BufferedReader(new FileReader("/proc/mounts")))
		{
			HashSet<String> paths = new HashSet<String>();
			String line;
			int cur_display_number = 1;
			Log.d(TAG, "/proc/mounts");
			while ((line = buf_reader.readLine()) != null)
			{
				Log.d(TAG, line);
				if (line.contains("vfat") || line.contains("/mnt"))
				{
					StringTokenizer tokens = new StringTokenizer(line, " ");
					String mount_point = tokens.nextToken(); // mount point
					if (paths.contains(mount_point))
					{
						continue;
					}
					List<String> flags = Arrays.asList(tokens.nextToken().split(",")); // flags
					boolean readonly = flags.contains("ro");

					if (mount_point.equals(def_path))
					{
						paths.add(def_path);
						list.add(0, new StorageInfo(def_path, def_path_internal, readonly, -1));
					}
					else if (line.contains("/dev/block/vold"))
					{
						if (!line.contains("/mnt/secure") && !line.contains("/mnt/asec") && !line.contains("/mnt/obb") && !line.contains("/dev/mapper") && !line.contains("tmpfs"))
						{
							paths.add(mount_point);
							list.add(new StorageInfo(mount_point, false, readonly, cur_display_number++));
						}
					}
				}
			}

			if (!paths.contains(def_path) && def_path_available)
			{
				list.add(0, new StorageInfo(def_path, def_path_internal, def_path_readonly, -1));
			}
		}
		catch (FileNotFoundException ex)
		{
			LogUtils.logError(TAG, ex.getMessage());
		}
		catch (IOException ex)
		{
			LogUtils.logError(TAG, ex.getMessage());
		}
		return list;
	}

	public void debugStorages()
	{
		if (BuildConfig.DEBUG)
		{
			List<StorageInfo> storageInfos = getStorageList();
			for (StorageInfo storageInfo : storageInfos)
			{
				LogUtils.log(TAG, storageInfo.getDisplayName());
			}
		}
	}
}
