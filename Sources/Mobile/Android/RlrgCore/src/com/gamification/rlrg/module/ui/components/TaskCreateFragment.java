package com.gamification.rlrg.module.ui.components;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.gamification.rlrg.application.RlrgApp;
import com.gamification.rlrg.data.Tasks;
import com.gamification.rlrg.data.entity.Category;
import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.ui.StartActivity;
import com.gamification.rlrg.module.ui.components.FragmentFactory.Type;
import com.gamification.rlrg.settings.Settings;

final class TaskCreateFragment extends Fragment implements OnClickListener, OnDateSetListener
{
	public static final String TAG = TaskCreateFragment.class.getName();

	@SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat sFormat = new SimpleDateFormat(Settings.DATETIME_FORMAT);

	private class CategoryAdapter extends ArrayAdapter<Category>
	{
		public CategoryAdapter(Context context, int resource)
		{
			super(context, resource);
		}

		public int getCount()
		{
			return mCategories.size();
		}

		public Category getItem(int position)
		{
			return mCategories.get(position);
		}

		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			TextView label = new TextView(mActivity);
			label.setTextColor(Color.BLACK);
			label.setText(mCategories.get(position).getName());
			return label;
		}
	}

	private StartActivity mActivity;
	private EditText mEdtName, mEdtComplete, mEdtPoint;
	private Spinner mSpnCategory, mSpnDifficulty;
	private Calendar mCalendar = Calendar.getInstance();

	private List<Category> mCategories;
	private Tasks mTasks;

	static TaskCreateFragment newInstance()
	{
		return new TaskCreateFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container == null)
		{
			return null;
		}
		View root = inflater.inflate(R.layout.fragment_create_task, container, false);
		mEdtName = (EditText) root.findViewById(R.id.edit_name);
		mEdtComplete = (EditText) root.findViewById(R.id.edit_complete);
		mEdtPoint = (EditText) root.findViewById(R.id.edit_point);
		mSpnDifficulty = (Spinner) root.findViewById(R.id.spinner_difficulty);
		mSpnCategory = (Spinner) root.findViewById(R.id.spinner_category);
		return root;
	}

	@Override
	public void onStart()
	{
		super.onStart();
		mActivity = (StartActivity) getActivity();
		if (mActivity == null)
		{
			return;
		}
		mEdtComplete.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				new DatePickerDialog(mActivity, TaskCreateFragment.this, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		mActivity.setBtnActionBarRightText(R.string.action_create_task, this);
		mCategories = RlrgApp.getInstance().getCategories().getData().getElements();
		mTasks = RlrgApp.getInstance().getTasks();
		mSpnCategory.setAdapter(new CategoryAdapter(mActivity, android.R.layout.simple_spinner_dropdown_item));
	}

	@Override
	public void onClick(View view)
	{
		mTasks.addTask
		(
		        ((Category) mSpnCategory.getSelectedItem()).getName(),
		        mCalendar.getTime().getTime(),
		        (String) mSpnDifficulty.getSelectedItem(),
		        mEdtName.getText().toString(),
		        mEdtPoint.getText().toString(),
		        null
		);
        RlrgApp.getInstance().checkAchievemnt();
		mActivity.replaceFragment(FragmentFactory.create(Type.SHOWROOM));
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int day)
	{
		mCalendar.set(Calendar.YEAR, year);
		mCalendar.set(Calendar.MONTH, month);
		mCalendar.set(Calendar.DAY_OF_MONTH, day);
		mEdtComplete.setText(sFormat.format(mCalendar.getTime()));
	}
}
