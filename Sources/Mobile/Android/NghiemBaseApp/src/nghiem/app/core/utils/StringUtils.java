package nghiem.app.core.utils;

import java.util.regex.Pattern;

import android.text.TextUtils;

public class StringUtils
{
    private static final String EMAIL_REGULAR_EXCEPTION = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGULAR_EXCEPTION);

    private StringUtils()
    {
    }

    /**
     * Validate the email
     * 
     * @param email
     */
    public static final boolean isValidEmail(String email)
    {
        if (TextUtils.isEmpty(email))
        {
            throw new NullPointerException("The string must not be null!");
        }
        if (email.length() > email.trim().length())
        {
            throw new IllegalArgumentException("The string has unexpected character!");
        }
        return EMAIL_PATTERN.matcher(email).find();
    }
    
    public static boolean isNumeric(String string)
    {
        if (string == null)
        {
            throw new NullPointerException("The string must not be null!");
        }
        final int length = string.length();
        if (length == 0)
        {
            return false;
        }
        for (int i = 0; i < length; ++i)
        {
            if (!Character.isDigit(string.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
}
