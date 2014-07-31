package com.gamification.rlrg.module.ui.components;

import nghiem.app.core.components.ListViewFragment;

import org.joda.time.DateTime;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.gamification.rlrg.data.Achievements;
import com.gamification.rlrg.data.entity.Achievement;
import com.gamification.rlrg.gen.R;
import com.gamification.rlrg.module.ui.StartActivity;
import com.gamification.rlrg.settings.Settings;

final class ShowRoomFragment extends ListViewFragment<Achievement>
{
    public static ShowRoomFragment newInstance()
    {
        return new ShowRoomFragment(R.layout.list_view, R.layout.list_item_game);
    }

    public ShowRoomFragment(int listLayout, int itemLayout)
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
            activity.hideActionBarButtonRight();
            Achievements achievements = activity.getCoreApp().getAchievements();
            if (achievements.isSuccessful())
            {
                setData(achievements.getData().getElements());
            }
            else
            {
                LOG.debug(achievements.getMessage());
            }
        }
    }

    @Override
    @SuppressLint("SimpleDateFormat")
    protected View getListItemView(ListView parent, View view, int position)
    {
        Achievement item = (Achievement) getListAdapter().getItem(position);

        DateTime date = new DateTime(item.getAchievedTime());

        String message = "AchievedTime: %s\nBadge: %s";

        ((TextView) view).setText(String.format(message,
                date.toString(Settings.DATETIME_FORMAT),
                item.getBadge().getName()));
        return view;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position,
            long id)
    {
    }
}