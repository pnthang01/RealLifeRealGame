package nghiem.app.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;


public class StorageUtils
{
	private StorageUtils()
    {
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
			LogUtils.logException(e);

			// Try with standard encode
			content = buffer.toString();
		}

		return content;
	}
}
