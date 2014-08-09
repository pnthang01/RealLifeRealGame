package lvnghiem.app.core.components;

import nghiem.app.gen.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ExpandCollapseView extends View
{
    public ExpandCollapseView(Context context)
    {
        super(context);
        init(context);
    }

    public ExpandCollapseView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public ExpandCollapseView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context)
    {
        LayoutInflater.from(context).inflate(R.layout.expand_collapse, (ViewGroup) getRootView());
    }
}
