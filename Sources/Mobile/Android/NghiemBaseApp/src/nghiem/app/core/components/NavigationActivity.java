package nghiem.app.core.components;

import java.util.Arrays;
import java.util.List;

import nghiem.app.core.components.ListViewFragment.OnScrollUpDownListener;
import nghiem.app.core.data.NavigationData;
import nghiem.app.gen.R;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class NavigationActivity extends NghiemActivity implements
        OnScrollUpDownListener
{
    private class NavigationAdapter extends ArrayAdapter<NavigationData>
    {
        public NavigationAdapter()
        {
            super(NavigationActivity.this, android.R.layout.simple_list_item_1,
                    mNavigationData);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View view = convertView;
            if (view == null)
            {
                view = inflate(R.layout.list_item_navigation, parent, false);
            }
            TextView title = (TextView) view;
            title.setText(getItem(position).getTitle());
            title.setCompoundDrawables(getItem(position).getIcon(), null, null,
                    null);

            return view;
        }
    }

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private FrameLayout mMainView;
    private LinearLayout mActionBar, mNavigation;

    private ListView mNavigationList;
    private TextView mActionBarTitle;
    private Button mBtnActionBarRightText;
    private ImageButton mBtnActionBarLeft, mBtnActionBarRightOne,
            mBtnActionBarRightTwo;

    protected NavigationData[] mNavigationData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_with_navigation);
        mNavigation = (LinearLayout) findViewById(R.id.navigation);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mNavigationList = (ListView) findViewById(R.id.navigation_list);

        mActionBar = (LinearLayout) findViewById(R.id.actionbar);
        mMainView = (FrameLayout) findViewById(R.id.main_view);
        mActionBarTitle = (TextView) findViewById(R.id.actionbar_title);
        mBtnActionBarLeft = (ImageButton) findViewById(R.id.actionbar_btn_left);
        mBtnActionBarRightOne = (ImageButton) findViewById(R.id.actionbar_btn_right_1);
        mBtnActionBarRightTwo = (ImageButton) findViewById(R.id.actionbar_btn_right_2);
        mBtnActionBarRightText = (Button) findViewById(R.id.actionbar_btn_right_text);

        mBtnActionBarLeft.setImageResource(R.drawable.ic_drawer);
        mBtnActionBarLeft.setVisibility(View.VISIBLE);
        mBtnActionBarLeft.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LOG.debug("open navigation");
                mDrawerLayout.openDrawer(mNavigation);
            }
        });

        setActionBarOverLay(true);
        setActionBarTitle(R.string.app_name);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        mNavigationList.setAdapter(new NavigationAdapter());
        mNavigationList.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view,
                    int position, long id)
            {
                LOG.debug("navigator item " + position + "clicked");
                onNavigationItemClick((ListView) adapter, view, position, id);
            }
        });
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void setContentView(int layout)
    {
        LOG.debug("set layout " + layout + " to main view");
        inflate(layout, mMainView);
    }

    protected void setNavigationData(List<NavigationData> data)
    {
        LOG.debug("set navigation data: " + data.toString());
        mNavigationData = data.toArray(new NavigationData[data.size()]);
    }

    protected void setNavigationData(NavigationData[] data)
    {
        LOG.debug("set navigation data: " + Arrays.toString(data));
        mNavigationData = data;
    }

    protected void onNavigationItemClick(ListView adapter, View view,
            int position, long id)
    {
        setActionBarTitle(mNavigationData[position].getTitle());
        mDrawerLayout.closeDrawers();
    }

    public void showActionBar()
    {
        LOG.debug("show action bar");
        if (mActionBar.getVisibility() == View.VISIBLE)
        {
            return;
        }
        Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.show_down);
        animation.setAnimationListener(new AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                mActionBar.setVisibility(View.VISIBLE);
            }
        });
        mActionBar.startAnimation(animation);
    }

    public void hideActionBar()
    {
        LOG.debug("hide action bar");
        if (mActionBar.getVisibility() == View.GONE)
        {
            return;
        }
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.hide_up);
        animation.setAnimationListener(new AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                mActionBar.setVisibility(View.GONE);
            }
        });
        mActionBar.startAnimation(animation);
    }

    @Override
    public void onScrollUp()
    {
        showActionBar();
    }

    @Override
    public void onScrollDown()
    {
        hideActionBar();
    }

    protected void setActionBarOverLay(boolean isOverlay)
    {
        LOG.debug("set action bar overlay: " + isOverlay);
        LayoutParams params = (LayoutParams) mMainView.getLayoutParams();
        if (isOverlay)
        {
            params.topMargin = 0;
            mActionBar.setBackgroundColor(getResources().getColor(
                    R.color.background_transparent));
        }
        else
        {
            params.topMargin = getResources().getDimensionPixelSize(
                    R.dimen.actionbar_height);
            mActionBar.setBackgroundColor(getResources().getColor(
                    R.color.background_solid));
        }
        mMainView.setLayoutParams(params);
    }

    protected void setActionBarTitle(int text)
    {
        LOG.debug("set action bar title id: " + text);
        mActionBarTitle.setText(text);
    }

    public void setActionBarTitle(CharSequence text)
    {
        LOG.debug("set action bar title: " + text);
        mActionBarTitle.setText(text);
    }

    protected void setBtnActionBarRightOne(Drawable icon,
            View.OnClickListener callback)
    {
        setActionBarButton(mBtnActionBarRightOne, icon, callback);
    }

    protected void setBtnActionBarRightTwo(Drawable icon,
            View.OnClickListener callback)
    {
        setActionBarButton(mBtnActionBarRightTwo, icon, callback);
    }

    public void setBtnActionBarRightText(int title,
            View.OnClickListener callback)
    {
        if (title > 0)
        {
            setBtnActionBarRightText(getString(title), callback);
        }
    }

    public void setBtnActionBarRightText(CharSequence title,
            View.OnClickListener callback)
    {
        if (title != null)
        {
            mBtnActionBarRightText.setText(title);
            mBtnActionBarRightText.setVisibility(View.VISIBLE);
            mBtnActionBarRightText.setOnClickListener(callback);
            LOG.debug("set action bar button "
                    + mBtnActionBarRightText.toString() + "with title " + title);
        }
        if (mBtnActionBarRightOne.getVisibility() == View.VISIBLE)
        {
            mBtnActionBarRightOne.setVisibility(View.GONE);
        }
        if (mBtnActionBarRightTwo.getVisibility() == View.VISIBLE)
        {
            mBtnActionBarRightTwo.setVisibility(View.GONE);
        }
    }

    protected void setBtnActionBarRightOne(int icon,
            View.OnClickListener callback)
    {
        setBtnActionBarRightOne(getResources().getDrawable(icon), callback);
    }

    protected void setBtnActionBarRightTwo(int icon,
            View.OnClickListener callback)
    {
        setBtnActionBarRightTwo(getResources().getDrawable(icon), callback);
    }

    protected void setBtnActionBarRightOne(Bitmap icon,
            View.OnClickListener callback)
    {
        setBtnActionBarRightOne(new BitmapDrawable(getResources(), icon),
                callback);
    }

    protected void setBtnActionBarRightTwo(Bitmap icon,
            View.OnClickListener callback)
    {
        setBtnActionBarRightTwo(new BitmapDrawable(getResources(), icon),
                callback);
    }

    private void setActionBarButton(ImageButton view, Drawable icon,
            View.OnClickListener callback)
    {
        if (icon != null)
        {
            view.setImageDrawable(icon);
            view.setVisibility(View.VISIBLE);
            view.setOnClickListener(callback);
        }
    }

    public void hideActionBarButtonRight()
    {
        mBtnActionBarRightOne.setVisibility(View.GONE);
        mBtnActionBarRightTwo.setVisibility(View.GONE);
        mBtnActionBarRightText.setVisibility(View.GONE);
    }

    public void setDrawerLockMode(int mode)
    {
        mDrawerLayout.setDrawerLockMode(mode);
    }
}
