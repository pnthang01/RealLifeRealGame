package nghiem.app.core.utils;


public class ImageUtils
{
    private ImageUtils()
    {
    }

    /**
     * Get the color value from the string of RGB.
     * 
     * @param colorString
     *            Example: "200200200"
     * @return color code integer
     */
    public static int getColorFromString(String colorString)
    {
        int color = 0xFF;
        int elementCode;
        String elementStr;
        try
        {
            elementStr = colorString.substring(0, 3);
            elementCode = Integer.parseInt(elementStr);
            color = elementCode * 16 * 16 * 16 * 16;

            elementStr = colorString.substring(3, 6);
            elementCode = Integer.parseInt(elementStr);
            color += elementCode * 16 * 16;

            elementStr = colorString.substring(6, 9);
            elementCode = Integer.parseInt(elementStr);
            color += elementCode;
        }
        catch (NumberFormatException e)
        {
        }
        return color;
    }
}
