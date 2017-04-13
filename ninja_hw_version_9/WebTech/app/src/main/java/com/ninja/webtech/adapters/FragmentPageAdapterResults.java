package com.ninja.webtech.adapters;

import android.content.Context;
import android.os.Bundle;
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
    private String mQuery;

    public FragmentPageAdapterResults(FragmentManager fm, Context context, String query) {
        super(fm);
        mContext = context;
        mQuery = query;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                Fragment fragment = UsersResultsFragment.getNewInstance();
                Bundle bundle = new Bundle();
                bundle.putString("query", mQuery);
                fragment.setArguments(bundle);
                return fragment;

            case 1 :
                Fragment fragment1 = PagesResultsFragment.getNewInstance();
                Bundle bundle1 = new Bundle();
                bundle1.putString("query", mQuery);
                fragment1.setArguments(bundle1);
                return fragment1;
            case 2 :
                Fragment fragment2 = EventsResultsFragment.getInstance();
                Bundle bundle2 = new Bundle();
                bundle2.putString("query", mQuery);
                fragment2.setArguments(bundle2);
                return fragment2;
            case 3 :
                Fragment fragment3 = PlacesResultsFragment.getNewInstance();
                Bundle bundle3 = new Bundle();
                bundle3.putString("query", mQuery);
                fragment3.setArguments(bundle3);
                return fragment3;
            case 4 :
                Fragment fragment4 = GroupsResultsFragment.getNewInstance();
                Bundle bundle4 = new Bundle();
                bundle4.putString("query", mQuery);
                fragment4.setArguments(bundle4);
                return fragment4;
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
