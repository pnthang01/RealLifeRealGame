package nghiem.app.core.components;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

public class HiperLink extends ClickableSpan
{
    private int mColor;
    private Typeface mTypeface;
    private boolean mUnderline;

    public HiperLink()
    {
        mColor = Color.BLUE;
        mUnderline = true;
        mTypeface = null;
    }

    @Override
    public void updateDrawState(TextPaint textPaint)
    {
        textPaint.setColor(mColor);
        textPaint.setUnderlineText(mUnderline);
        if (mTypeface != null)
        {
            textPaint.setTypeface(mTypeface);
        }
    }

    @Override
    public void onClick(View widget)
    {
    }

    public int getColor()
    {
        return mColor;
    }

    public void setColor(int mColor)
    {
        this.mColor = mColor;
    }

    public Typeface getTypeface()
    {
        return mTypeface;
    }

    public void setTypeface(Typeface mTypeface)
    {
        this.mTypeface = mTypeface;
    }

    public boolean getUnderline()
    {
        return mUnderline;
    }

    public void setUnderline(boolean mUnderline)
    {
        this.mUnderline = mUnderline;
    }
}
