package nghiem.app.core.components;

import nghiem.app.core.application.DeviceManager;
import nghiem.app.core.application.WebManager;
import nghiem.app.core.utils.StringUtils;
import nghiem.app.gen.R;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class WebViewFragment extends NghiemFragment
{
	public static final String EXTRA_LINK = "EXTRA_LINK";
	public static final String EXTRA_BG = "BG_RD";

	protected Class<?> CLASS = getClass();

	private class ChromeBrowser extends WebChromeClient
	{
		@Override
		public void onReceivedTitle(WebView view, String title)
		{
			super.onReceivedTitle(view, title);

			// TODO:
		}
	}

	private class Browser extends WebViewClient
	{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url)
		{
			// Make the URL open in parent view
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon)
		{
			mWebView.setVisibility(View.GONE);
			mProgressBar.setVisibility(View.VISIBLE);
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public void onPageFinished(WebView view, String url)
		{
			mWebView.setVisibility(View.VISIBLE);
			mProgressBar.setVisibility(View.GONE);
			super.onPageFinished(view, url);
			mActivity.setTitle(view.getTitle());
		}
	}

	private NghiemActivity mActivity;
	private RelativeLayout mRoot;
	private WebView mWebView;
	private WebManager mFactory;
	private ProgressBar mProgressBar;

	private String mLink = "";

	public WebViewFragment()
	{
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container == null)
		{
			return null;
		}
		mRoot = (RelativeLayout) inflater.inflate(R.layout.fragment_web, container, false);
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
			LOG.error("Activity is null!");
		}

		mFactory = new WebManager(mActivity);
		mLink = mActivity.getIntent().getStringExtra(EXTRA_LINK);
		if (StringUtils.isValidURL(mLink))
		{
			mActivity.showDebugToast(mLink);
		}
		else
		{
			mActivity.showDebugToast("The link is wrong format: " + mLink);
		}

		if (DeviceManager.getInstance().isNetworkConnected())
		{
			mWebView.setWebViewClient(new Browser());
			mFactory.configure(mWebView, new ChromeBrowser());
			mWebView.loadUrl(mLink);
		}
		else
		{
			mProgressBar.setVisibility(View.GONE);
		}
	}

	@Override
	public void onResume()
	{
		super.onResume();
		mFactory.resumeWeb(mWebView);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		mFactory.pauseWeb(mWebView);
	}
}
