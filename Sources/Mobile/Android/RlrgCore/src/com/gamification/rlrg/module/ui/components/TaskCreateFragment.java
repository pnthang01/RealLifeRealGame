package com.gamification.rlrg.module.ui.components;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import nghiem.app.core.components.NghiemFragment;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
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

final class TaskCreateFragment extends NghiemFragment implements OnClickListener,
        OnDateSetListener
{
    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat sFormat = new SimpleDateFormat(
            Settings.DATETIME_FORMAT);

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
    private EditText mEdtName, mEdtComplete;
    private Spinner mSpnCategory, mSpnDifficulty;
    private Calendar mCalendar = Calendar.getInstance();

    private List<Category> mCategories;
    private Tasks mTasks;

    static TaskCreateFragment newInstance()
    {
        return new TaskCreateFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        if (container == null)
        {
            return null;
        }
        View root = inflater.inflate(R.layout.fragment_create_task, container,
                false);
        mEdtName = (EditText) root.findViewById(R.id.edit_name);
        mEdtComplete = (EditText) root.findViewById(R.id.edit_complete);
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
        mEdtComplete.setOnFocusChangeListener(new OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    return;
                }
                int year = mCalendar.get(Calendar.YEAR);
                int month = mCalendar.get(Calendar.MONTH);
                int day = mCalendar.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(mActivity, TaskCreateFragment.this, year,
                        month, day).show();
            }
        });
        mActivity.setBtnActionBarRightText(R.string.action_create_task, this);
        mCategories = RlrgApp.getInstance().getCategories().getData().getElements();
        mTasks = RlrgApp.getInstance().getTasks();
        mSpnCategory.setAdapter(new CategoryAdapter(mActivity,
                android.R.layout.simple_spinner_dropdown_item));
    }

    @Override
    public void onClick(View view)
    {
        String category = ((Category) mSpnCategory.getSelectedItem()).getName();
        long completeTime = mCalendar.getTime().getTime();
        String difficultyLevel = (String) mSpnDifficulty.getSelectedItem();
        String name = mEdtName.getText().toString();

        mTasks.addTask(category, completeTime, difficultyLevel, name, "10",
                null);
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
