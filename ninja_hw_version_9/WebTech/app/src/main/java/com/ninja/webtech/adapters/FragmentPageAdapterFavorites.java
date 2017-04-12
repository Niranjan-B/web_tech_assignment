package com.ninja.webtech.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninja.webtech.R;
import com.ninja.webtech.fragments.EventsFragment;
import com.ninja.webtech.fragments.GroupsFragment;
import com.ninja.webtech.fragments.PagesFragment;
import com.ninja.webtech.fragments.PlacesFragment;
import com.ninja.webtech.fragments.UsersFragment;

/**
 * Created by niranjanb on 12/04/17.
 */

public class FragmentPageAdapterFavorites extends FragmentPagerAdapter {
    private static final int TAB_COUNT = 5;
    private static final String mTabTitles[] = {"Users", "Pages", "Events", "Places", "Groups"};
    private static final int mTabIcons[] = {R.mipmap.users, R.mipmap.pages, R.mipmap.events, R.mipmap.places, R.mipmap.groups};
    private Context mContext;

    public FragmentPageAdapterFavorites(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return UsersFragment.getNewInstance();
            case 1 :
                return PagesFragment.getNewInstance();
            case 2 :
                return EventsFragment.getInstance();
            case 3 :
                return PlacesFragment.getNewInstance();
            case 4 :
                return GroupsFragment.getNewInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_tab_layout, null);
        TextView tv = (TextView) view.findViewById(R.id.text_view_custom_tab_layout);
        tv.setText(mTabTitles[position]);
        ImageView iv = (ImageView) view.findViewById(R.id.image_view_custom_tab_layout);
        iv.setImageResource(mTabIcons[position]);
        return view;
    }
}
