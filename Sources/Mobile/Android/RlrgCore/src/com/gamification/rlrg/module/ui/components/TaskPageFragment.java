package com.gamification.rlrg.module.ui.components;

import java.util.ArrayList;
import java.util.List;

import lvnghiem.app.core.components.ListViewFragment;

import org.joda.time.DateTime;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.gamification.rlrg.application.RlrgApp;
import com.gamification.rlrg.data.Tasks;
import com.gamification.rlrg.data.entity.Task;
import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.ui.StartActivity;
import com.gamification.rlrg.module.ui.components.FragmentFactory.Type;

class TaskPageFragment extends ListViewFragment<Task>
{
	private StartActivity mActivity;

	static TaskPageFragment newInstance()
	{
		return new TaskPageFragment(R.layout.list_view_badges, R.layout.list_item_game);
	}

	private TaskPageFragment(int listLayout, int itemLayout)
	{
		super(listLayout, itemLayout);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		String time = "";
		List<Task> data = new ArrayList<Task>();

		if (mArguments != null)
		{
			time = mArguments.getString("time");
		}
		mActivity = (StartActivity) getActivity();
		if (mActivity != null)
		{
			Tasks tasks = mActivity.getCoreApp().getTasks();
			if (time.equalsIgnoreCase("Today"))
			{
				data = tasks.getTodayTasks();
			}
			if (time.equalsIgnoreCase("Tomorrow"))
			{
				data = tasks.getTomorrowTasks();
			}
			if (time.equalsIgnoreCase("Week"))
			{
				data = tasks.getWeekTasks();
			}
			if (time.equalsIgnoreCase("Outdate"))
			{
				data = tasks.getOutdateTasks();
			}

			if (tasks.isSuccessful())
			{
				setData(data);
			}
			else
			{
				LOG.debug(tasks.getMessage());
			}
		}
	}

	@Override
	@SuppressLint("SimpleDateFormat")
	protected View getListItemView(ListView parent, View view, int position)
	{
		Task item = (Task) getListAdapter().getItem(position);

		String start = new DateTime(item.getStartTime()).toString(RlrgApp.getInstance().getString(R.string.settings_date_time_format));
		String complete = new DateTime(item.getCompleteTime()).toString(RlrgApp.getInstance().getString(R.string.settings_date_time_format));
        String message = "Category: %s\nName: %s\nStart Time: %s\nComplete Time: %s\nDifficulty Level: %s\nStatus: %s";
		String print = String.format(message, item.getCategory().getName(), item.getName(), start, complete, item.getDifficultyLevel(), item.getStatus());

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