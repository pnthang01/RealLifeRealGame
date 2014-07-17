package nghiem.app.core.utils;

public class NumberUtils
{
    private NumberUtils()
    {
    }
    
    public static String intToHexString(int number)
    {
        return Integer.toHexString(number);
    }

    /**
     * Full 6 number in the string
     * 
     * @param number
     * @return example: 0023FF
     */
    public static String intToHexString6(int number)
    {
        String hex = Integer.toHexString(number);

        for (int index = hex.length(); index < 6; index++)
        {
            hex = "0" + hex;
        }

        return hex;
    }

    public static int hexStringToInt(String hexString)
    {
        return (int) Long.parseLong(hexString, 16);
    }
}
