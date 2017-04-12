package com.ninja.webtech.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninja.webtech.R;
import com.ninja.webtech.fragments.EventsResultsFragment;
import com.ninja.webtech.fragments.GroupsResultsFragment;
import com.ninja.webtech.fragments.PagesResultsFragment;
import com.ninja.webtech.fragments.PlacesResultsFragment;
import com.ninja.webtech.fragments.UsersResultsFragment;

/**
 * Created by niranjanb on 12/04/17.
 */

public class FragmentPageAdapterResults extends FragmentPagerAdapter {

    private static final int TAB_COUNT = 5;
    private static final String mTabTitles[] = {"Users", "Pages", "Events", "Places", "Groups"};
    private static final int mTabIcons[] = {R.mipmap.users, R.mipmap.pages, R.mipmap.events, R.mipmap.places, R.mipmap.groups};
    private Context mContext;

    public FragmentPageAdapterResults(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return UsersResultsFragment.getNewInstance();
            case 1 :
                return PagesResultsFragment.getNewInstance();
            case 2 :
                return EventsResultsFragment.getInstance();
            case 3 :
                return PlacesResultsFragment.getNewInstance();
            case 4 :
                return GroupsResultsFragment.getNewInstance();
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
