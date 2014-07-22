package com.gamification.rlrg.module.ui.components;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import nghiem.app.core.components.ListViewFragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.gamification.rlrg.data.Tasks;
import com.gamification.rlrg.data.entity.Task;
import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.ui.StartActivity;
import com.gamification.rlrg.module.ui.components.FragmentFactory.Type;
import com.gamification.rlrg.settings.Settings;

class TaskPageFragment extends ListViewFragment<Task>
{
	public static final String TAG = TaskPageFragment.class.getName();

	@SuppressLint("SimpleDateFormat")
    private Format sFormat = new SimpleDateFormat(Settings.DATETIME_FORMAT);

	private StartActivity mActivity;

	static TaskPageFragment newInstance()
	{
		return new TaskPageFragment(R.layout.list_view, R.layout.list_item_game);
	}

	private TaskPageFragment(int listLayout, int itemLayout)
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
		mActivity = (StartActivity) getActivity();
		if (mActivity != null)
		{
			Tasks tasks = mActivity.getCoreApp().getTasks();
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

		String message = "Category: %s\nName: %s\nComplete Time: %s\nDifficulty Level: %s\nStatus: %s\nPoint: %s";
		String print = String.format(message, item.getCategory().getName(), item.getName(), sFormat.format(date), item.getDifficultyLevel(), item.getStatus(), item.getPoint());

		((TextView) view).setText(print);
		return view;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id)
	{
		Bundle args = new Bundle();
		args.putInt("position", position);
		Fragment fragment = FragmentFactory.create(Type.TASK_DETAIL);
		fragment.setArguments(args);
		mActivity.replaceFragment(fragment);
	}
}