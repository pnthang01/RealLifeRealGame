package nghiem.app.core.application;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;

import nghiem.app.core.components.NghiemActivity;
import nghiem.app.core.utils.LogUtils;
import nghiem.app.gen.BuildConfig;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.RenderPriority;

public class WebManager
{
	public static final String TAG = WebManager.class.getName();

	private static final String WEB_CONTENT = "<html><head><style type='text/css'>body {font-size: %dpx; text-align: left; margin: 0px 0px 0px 0px;}</style></head><body %s>%s</body></html>";
	private static final String WEB_CONTENT_FONT_FACE_APPLIED = "<html><head><style type='text/css'>@font-face {font-family: 'custom_font'; src: url('%s')} body {font-family: 'custom_font'; font-size: %dpx;text-align: left; margin: 0px 0px 0px 0px;}</style></head><body bgcolor='%s' text='%s'>%s</body></html>";
	private static final String MIN_DATA = "text/html";

	private NghiemActivity mContext;

	public WebManager(NghiemActivity context)
	{
		mContext = context;
	}

	/**
	 * Create a {@link WebView} which supports show its links by pages in own
	 * this application.
	 * 
	 * @param webContent
	 *            the content text contains HTML tags
	 * @param fontSizeDefault
	 *            the font size default, NOTE: {@link WebView} treats it in dp
	 * @param backgroundColor
	 *            the background color, let it <code>null</code> if it is
	 *            transparent.
	 * @param textColorDefault
	 *            the text color default, let it <code>null</code> if it is
	 *            transparent.
	 * @return a {@link WebView}
	 * 
	 * @see #createWebViewInApp(Context, String, int, String, String, String)
	 * 
	 * @example <code>createWebInApp (activity, webContent, 14, "#FFFFFF", "#FF0000")</code>
	 * 
	 * @Specially If the content of web page contains HTML5 controller, like
	 *            "iframe" video, should place this code in the parent activity:<br />
	 * <br />
	 * 
	 *            <code>
	 * protected void onPause() { <br />super.onPause();<br />
	 *           WebUtils.callHiddenWebViewMethod(mWebViewDescription,<br />
	 *           "onPause"); <br />}<br /><br />
	 *           
	 * protected void onResume() {<br /> super.onResume();<br />
	 *           WebUtils.callHiddenWebViewMethod(mWebViewDescription,<br />
	 *           "onResume"); <br />} </code>
	 */
	public WebView createWebViewInApp(String webContent, int fontSizeDefault, String backgroundColor, String textColorDefault)
	{
		return createWebViewInApp(webContent, fontSizeDefault, backgroundColor, textColorDefault, null);
	}

	/**
	 * Create a {@link WebView} which supports show its links by pages in own
	 * this application. And this {@link WebView} is applied the new font
	 * 
	 * @param webContent
	 *            the content text contains HTML tags
	 * @param fontSizeDefault
	 *            the font size default, NOTE: {@link WebView} treats it in DP.
	 * @param backgroundColor
	 *            the background color, let it <code>null</code> if it is
	 *            transparent.
	 * @param textColorDefault
	 *            the text color default, let it <code>null</code> if it is
	 *            transparent.
	 * @param fontAssetPath
	 *            the asset path of the font, let it <code>null</code> if it is
	 *            not specified.
	 * @return a {@link WebView}
	 * 
	 * @see #createWebViewInApp(Context, String, int, String, String)
	 * @see {@link AssetFont} for detail of font path.
	 * 
	 * @example <code>createWebInApp (activity, webContent, 14, "#FFFFFF", "#FF0000", "fonts/ProximaNovaLight.ttf")</code>
	 */
	public WebView createWebViewInApp(String webContent, int fontSizeDefault, String backgroundColor, String textColorDefault, String fontAssetPath)
	{
		// Process color
		String bgColor = "";
		if (!TextUtils.isEmpty(backgroundColor))
		{
			bgColor = "bgcolor='" + backgroundColor + "'";
		}
		String textColor = "";
		if (!TextUtils.isEmpty(textColorDefault))
		{
			textColor = "text='" + textColorDefault + "'";
		}

		// Process the content
		String contentShown = "";
		if (TextUtils.isEmpty(fontAssetPath))
		{
			contentShown = String.format(Locale.getDefault(), WEB_CONTENT, fontSizeDefault, bgColor + textColor, webContent);
		}
		else
		{
			contentShown = String.format(Locale.getDefault(), WEB_CONTENT_FONT_FACE_APPLIED, fontAssetPath, fontSizeDefault, bgColor + textColor, webContent);
		}

		// Create WebView
		WebView webView = new WebView(mContext);
		configure(webView, new WebChromeClient());

		// Add web client
		webView.setWebViewClient(new WebViewClient());

		webView.loadDataWithBaseURL(null, contentShown, MIN_DATA, "UTF-8", null);

		return webView;
	}

	@SuppressWarnings("deprecation")
	@SuppressLint({ "SetJavaScriptEnabled", "NewApi" })
	public void configure(WebView webView, WebChromeClient chromeClient)
	{
		webView.getSettings().setJavaScriptEnabled(true);

		// Support HTML5 IFRAME (Video, ...)
		webView.setWebChromeClient(chromeClient);

		if (Build.VERSION.SDK_INT > 10)
		{
			// For more smooth, since the WebView does not have good performance
			// with hardware acceleration (manifest). Bug, WebView loads blank
			// page
			// webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}

		if (Build.VERSION.SDK_INT < 18)
		{
			// Nothing changes
			webView.getSettings().setRenderPriority(RenderPriority.HIGH);
		}

		// webView.getSettings().setPluginState(PluginState.ON);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setDatabaseEnabled(true);
		webView.getSettings().setDomStorageEnabled(true);
		webView.getSettings().setAppCacheEnabled(true);
		webView.getSettings().setLoadsImagesAutomatically(true);
		// webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
	}

	/**
	 * To call the none-public methods of {@link WebView}
	 * 
	 * @param webView
	 *            if null, this method is canceled
	 * @param method
	 *            if null, this method is canceled
	 */
	private void callHiddenWebViewMethod(WebView webView, String methodName)
	{
		if (webView != null && !TextUtils.isEmpty(methodName))
		{
			try
			{
				Method method = WebView.class.getMethod(methodName);
				method.invoke(webView);
			}
			catch (Exception e)
			{
				if (BuildConfig.DEBUG)
				{
					LogUtils.logLongText(TAG, Arrays.toString(e.getStackTrace()));
				}
			}
		}
	}

	public void resumeWeb(WebView mWebView)
	{
		callHiddenWebViewMethod(mWebView, "onResume");
	}

	/**
	 * @since For fix video is still playing when exit application.
	 * 
	 *        TODO still bug: press home, video can not restart
	 */
	public void pauseWeb(WebView mWebView)
	{
		callHiddenWebViewMethod(mWebView, "onPause");
	}
}