package nghiem.app.core.settings;

public class Settings
{
	public static final String CHARSET = "UTF-8";

	public class Assets
	{
		public class ScreenResolution
		{
			/** Indicate that screen width <= 320 */
			public static final String RESOLUTION_LOW = "sw320";

			/** Indicate that 320 < screen width <= 480 */
			public static final String RESOLUTION_NORMAL = "sw480";

			/** Indicate that 480 < screen width <= 640 */
			public static final String RESOLUTION_HIGH = "sw640";

			/** Indicate that 640 < screen width <= 800 */
			public static final String RESOLUTION_XHIGH = "sw800";

			/** Indicate that 800 < screen width */
			public static final String RESOLUTION_XXHIGH = "sw1024";
		}
	}
}
