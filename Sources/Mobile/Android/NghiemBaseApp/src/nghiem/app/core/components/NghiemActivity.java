package nghiem.app.core.components;

import org.slf4j.LoggerFactory;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import nghiem.app.gen.BuildConfig;
import nghiem.app.gen.R;
import nghiem.app.core.utils.LogUtils;

public class NghiemActivity extends FragmentActivity
{
	protected Class<?> CLASS = getClass();

	protected FragmentManager mFragmentManager;

	private Handler mHandler;

	public Handler getHandler()
	{
		if (mHandler == null)
		{
			mHandler = new Handler(Looper.getMainLooper())
			{
				@Override
				public void handleMessage(Message msg)
				{
					super.handleMessage(msg);
					NghiemActivity.this.handleMessage(msg);
				}
			};
		}
		return mHandler;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mFragmentManager = getSupportFragmentManager();
        LoggerFactory.getLogger(NghiemActivity.class).debug("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        LoggerFactory.getLogger(NghiemActivity.class).error("bbbbbbbbbbbbbbbbbbbbbbbbbb");
	}

	protected View inflate(int layout)
	{
		LogUtils.debug(CLASS, "inflate " + layout);
		return inflate(layout, new FrameLayout(this));
	}

	protected View inflate(int layout, ViewGroup root)
	{
		LogUtils.debug(CLASS, "inflate " + layout + " to " + root.toString());
		return getLayoutInflater().inflate(layout, root);
	}

	protected View inflate(int layout, ViewGroup root, boolean attachToRoot)
	{
		LogUtils.debug(CLASS, "inflate " + layout + " no attach to " + root.toString());
		return getLayoutInflater().inflate(layout, root, attachToRoot);
	}

	public void addFragment(int container, Fragment child)
	{
		LogUtils.debug(CLASS, "add fragment " + child.toString() + " to " + container);
		mFragmentManager.beginTransaction().addToBackStack(null).add(container, child).commit();
	}

	public void replaceFragment(int container, Fragment child)
	{
		LogUtils.debug(CLASS, "replace fragment " + child.toString() + " to " + container);
		mFragmentManager.popBackStack();
		mFragmentManager.beginTransaction().addToBackStack(null).replace(container, child).commit();
	}

	public void showDialog(String type, boolean isCancelable)
	{
		final Dialog dialog = onCreateDialog(type);
		if (dialog == null)
		{
			LogUtils.error(CLASS, "cannot create dialog type " + type);
			return;
		}

		FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
		Fragment oldFragment = mFragmentManager.findFragmentByTag(type);
		if (oldFragment != null)
		{
			fragmentTransaction.remove(oldFragment);
			fragmentTransaction.commitAllowingStateLoss();
		}

		DialogFragment baseDialogFragment = new DialogFragment()
		{
			@Override
			public Dialog onCreateDialog(Bundle savedInstanceState)
			{
				return dialog;
			}
		};
		baseDialogFragment.setCancelable(isCancelable);
		baseDialogFragment.show(mFragmentManager, type);
	}

	public Dialog onCreateDialog(String type)
	{
		if (type == null)
		{
			LogUtils.error(CLASS, "type is null");
			return null;
		}
		return null;
	}

	public void toast(CharSequence msg)
	{
		LogUtils.debug(CLASS, "toast: " + msg);
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	public void toast(int msg)
	{
		String text = getResources().getString(msg);
		LogUtils.debug(CLASS, "toast id: " + text);
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	public void alert(CharSequence title, CharSequence message)
	{
		LogUtils.debug(CLASS, "alert " + title + " width message: " + message);
		new AlertDialog.Builder(this).setTitle(title).setMessage(message).setPositiveButton(R.string.action_ok, null).show();
	}

	public void alert(CharSequence message)
	{
		LogUtils.debug(CLASS, "warning: " + message);
		alert("Warning", message);
	}

	public void alert(int title, int message)
	{
		String textTitle = getResources().getString(title);
		String textMessage = getResources().getString(message);
		alert(textTitle, textMessage);
	}

	public void alert(int message)
	{
		String textMessage = getResources().getString(message);
		alert(textMessage);
	}

	public void showToast(String message)
	{
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	public void showDebugToast(String message)
	{
		if (BuildConfig.DEBUG)
		{
			showToast(message);
			LogUtils.debug(CLASS, message);
		}
	}

	/**
	 * Handling the message which sent by {@link #sendMessage(int, int)}
	 * 
	 * @param message
	 */
	public void handleMessage(Message message)
	{
	}
}
