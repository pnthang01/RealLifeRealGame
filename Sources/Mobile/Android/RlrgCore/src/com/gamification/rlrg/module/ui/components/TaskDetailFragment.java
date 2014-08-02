package com.gamification.rlrg.module.ui.components;

import java.text.SimpleDateFormat;
import java.util.Date;

import nghiem.app.core.components.NghiemFragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gamification.rlrg.application.DataPreferencesManager;
import com.gamification.rlrg.application.RlrgApp;
import com.gamification.rlrg.data.Tasks;
import com.gamification.rlrg.data.entity.Task;
import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.ui.StartActivity;
import com.gamification.rlrg.module.ui.components.FragmentFactory.Type;
import com.gamification.rlrg.settings.Settings;
import com.google.gson.Gson;

final class TaskDetailFragment extends NghiemFragment implements OnClickListener
{
	private StartActivity mActivity;
	private TextView mTxtName, mTxtCategory, mTxtDifficulty, mTxtComplete, mTxtPoint, mTxtStatus;
	private Task mTask;

	private String[] mStatus;

	private Tasks mTasks;

	static TaskDetailFragment newInstance()
	{
		return new TaskDetailFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container == null)
		{
			return null;
		}
		View root = inflater.inflate(R.layout.fragment_task_detail, container, false);
		mTxtName = (TextView) root.findViewById(R.id.text_name);
		mTxtCategory = (TextView) root.findViewById(R.id.text_category);
		mTxtDifficulty = (TextView) root.findViewById(R.id.text_difficulty);
		mTxtComplete = (TextView) root.findViewById(R.id.text_complete);
		mTxtPoint = (TextView) root.findViewById(R.id.text_point);
		mTxtStatus = (TextView) root.findViewById(R.id.text_status);
		return root;
	}

	@Override
	@SuppressLint("SimpleDateFormat")
	public void onStart()
	{
		super.onStart();
		mActivity = (StartActivity) getActivity();
		if (mActivity == null)
		{
			return;
		}
		mStatus = mActivity.getResources().getStringArray(R.array.task_status);
		SimpleDateFormat format = new SimpleDateFormat(Settings.DATE_FORMAT);
		Bundle args = getArguments();
		if (args == null)
		{
			return;
		}
		mTasks = RlrgApp.getInstance().getTasks();
		mTask = mTasks.getData().getElements().get(args.getInt("position"));

		mTxtName.setText(mTask.getName());
		mTxtCategory.setText(mTask.getCategory().getName());
		mTxtDifficulty.setText(mTask.getDifficultyLevel());
		mTxtComplete.setText(format.format(new Date(mTask.getCompleteTime())));
		mTxtPoint.setText(mTask.getPoint());
		mTxtStatus.setText(mTask.getStatus());

		mActivity.setBtnActionBarRightText(mTask.getStatus().equals(mStatus[0]) ? R.string.action_mark_as_complete : R.string.action_mark_as_uncomplete, this);
	}

	@Override
	public void onClick(View view)
	{
		mTask.setStatus(mTask.getStatus().equals(mStatus[0]) ? mStatus[1] : mStatus[0]);
		DataPreferencesManager.getInstance().saveJsonTasks(new Gson().toJson(mTasks, Tasks.class));
		mActivity.replaceFragment(FragmentFactory.create(Type.SHOWROOM));
	}
}
