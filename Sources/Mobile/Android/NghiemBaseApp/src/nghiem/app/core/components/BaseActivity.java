package nghiem.app.core.components;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import nghiem.app.gen.R;
import nghiem.app.core.utils.Utils;

public class BaseActivity extends FragmentActivity
{
    public static final String DIALOG_NETWORK_NOT_AVAILABLE = "NETWORK_NOT_AVAILABLE";
	public static final String DIALOG_SEARCH = "DIALOG_SEARCH";
	public static final String DIALOG_EXIT = "DIALOG_EXIT";
	
	protected String TAG = getClass().getName();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}
	
	protected View inflate(int layout)
	{
	    Utils.log(TAG, "inflate " + layout);
		return inflate(layout, new FrameLayout(this));
	}
	
	protected View inflate(int layout, ViewGroup root)
	{
	    Utils.log(TAG, "inflate " + layout + " to " + root.toString());
		return getLayoutInflater().inflate(layout, root);
	}
	
	protected View inflate(int layout, ViewGroup root, boolean attachToRoot)
	{
	    Utils.log(TAG, "inflate " + layout + " no attach to " + root.toString());
		return getLayoutInflater().inflate(layout, root, attachToRoot);
	}
	
	protected void addFragment(int container, Fragment child)
	{
	    Utils.log(TAG, "add fragment " + child.toString() + " to " + container);
		getSupportFragmentManager().beginTransaction().addToBackStack(null).add(container, child).commit();
	}
	
	protected void replaceFragment(int container, Fragment child)
	{
	    Utils.log(TAG, "replace fragment " + child.toString() + " to " + container);
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.popBackStack();
		fragmentManager.beginTransaction().addToBackStack(null).replace(container, child).commit();
	}
	
	protected void showDialog(String type, boolean isCancelable)
	{
		final Dialog dialog = onCreateDialog(type);
		if (dialog == null)
		{
		    Utils.logError(TAG, "cannot create dialog type " + type);
			return;
		}
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		Fragment oldFragment = fragmentManager.findFragmentByTag(type);
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
		baseDialogFragment.show(fragmentManager, type);
	}
	
	protected Dialog onCreateDialog(String type)
	{
		if (type == null)
		{
		    Utils.logError(TAG, "type is null");
			return null;
		}
		return null;
	}
	
	public void toast(CharSequence msg)
	{
		Utils.log(TAG, "toast: " + msg);
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
	public void toast(int msg)
	{
		String text = getResources().getString(msg);
		Utils.log(TAG, "toast id: " + text);
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
	
	public void alert(CharSequence title, CharSequence message)
	{
	    Utils.log(TAG, "alert " + title + " width message: " + message);
		new AlertDialog.Builder(this).setTitle(title).setMessage(message).setPositiveButton(R.string.ok, null).show();
	}
	
	public void alert(CharSequence message)
	{
	    Utils.log(TAG, "warning: " + message);
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
}
