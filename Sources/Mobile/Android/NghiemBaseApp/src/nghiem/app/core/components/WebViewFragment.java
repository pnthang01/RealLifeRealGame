package nghiem.app.core.components;

import nghiem.app.core.utils.StringUtils;
import nghiem.app.gen.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class WebViewFragment extends Fragment
{
    public static final String EXTRA_LINK = "EXTRA_LINK";
    public static final String EXTRA_BG = "BG_RD";

    private NghiemActivity mActivity;
    private RelativeLayout mRoot;
    private WebView mWebView;
    private ProgressBar mProgressBar;

    private String mLink = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        if (container == null)
        {
            return null;
        }
        mRoot = (RelativeLayout) inflater.inflate(R.layout.fragment_web,
                container, false);
        mWebView = (WebView) mRoot.findViewById(R.id.webview);
        mProgressBar = (ProgressBar) mRoot.findViewById(R.id.progress_bar);
        return mRoot;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        mActivity = (NghiemActivity) getActivity();
        if (mActivity == null)
        {
            mActivity.showDebugToast("Activity is null!");
        }
        
        mLink = mActivity.getIntent().getStringExtra(EXTRA_LINK);
        if (StringUtils.isValidURL(mLink))
        {
            mActivity.showDebugToast(mLink);
        }
        else
        {
            mActivity.showDebugToast("The link is wrong format: " + mLink);
        }
    }
}
