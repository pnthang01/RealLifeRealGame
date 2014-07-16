package com.gamification.rlrg.module.task.ui;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import nghiem.app.core.components.ListViewFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.gamification.rlrg.data.Tasks;
import com.gamification.rlrg.data.entity.Task;
import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.start.ui.StartActivity;

public class YearFragment extends ListViewFragment<Task>
{
	public static final String TAG = YearFragment.class.getName();

	public static YearFragment newInstance()
	{
		return new YearFragment(R.layout.list_view, R.layout.list_item_game);
	}

	public YearFragment(int listLayout, int itemLayout)
	{
		super(listLayout, itemLayout);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (mArguments != null)
		{
			// String title = mArguments.getString("title");
		}
		StartActivity activity = (StartActivity) getActivity();
		if (activity != null)
		{
			Tasks tasks = activity.getCoreApp().getTasks();
			if (tasks.isSuccessful())
			{
				setData(tasks.getData().getElements());
			}
			else
			{
				Log.d(TAG, tasks.getMessage());
			}
		}
	}

	@Override
	@SuppressLint("SimpleDateFormat")
	protected View getListItemView(ListView parent, View view, int position)
	{
		Task item = (Task) getListAdapter().getItem(position);

		Date date = new Date(item.getCompleteTime());
		Format format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		String message = "Category: %s\nName: %s\nComplete Time: %s\nDifficulty Level: %s\nStatus: %s\nPoint: %s";
		String print = String.format(message, item.getCategory().getName(), item.getName(), format.format(date), item.getDifficultyLevel(), item.getStatus(), item.getPoint());

		((TextView) view).setText(print);
		return view;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id)
	{
	}
}