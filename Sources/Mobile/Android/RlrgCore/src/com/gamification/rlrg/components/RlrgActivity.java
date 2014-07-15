package com.gamification.rlrg.components;

import nghiem.app.core.components.NghiemActivity;
import nghiem.app.core.utils.Utils;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

import com.gamification.rlrg.gen.R;

public class RlrgActivity extends NghiemActivity
{
    protected Dialog onCreateDialog(String type)
	{
        super.onCreateDialog(type);
		Utils.log(TAG, "alert type " + type);
		if (type.equals(DIALOG_NETWORK_NOT_AVAILABLE))
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.no_internet_connection);
			builder.setMessage(R.string.message_no_internet_connection);
			builder.setPositiveButton(R.string.ok, null);
			return builder.create();
		}
		if (type.equals(DIALOG_SEARCH))
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.action_search);
			builder.setView(inflate(R.layout.dialog_search));
			builder.setPositiveButton(R.string.ok, null);
			builder.setNegativeButton(R.string.cancel, null);
			
			return builder.create();
		}
		if (type.equals(DIALOG_EXIT))
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.quit_app);
			builder.setMessage(R.string.quit_app_message);
			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					finish();
				}
			});
			builder.setNegativeButton(R.string.cancel, null);
			
			return builder.create();
		}
		return null;
	}
}
