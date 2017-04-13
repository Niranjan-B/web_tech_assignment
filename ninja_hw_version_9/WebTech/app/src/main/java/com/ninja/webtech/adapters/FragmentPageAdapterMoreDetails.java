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
import com.ninja.webtech.fragments.AlbumsFragment;
import com.ninja.webtech.fragments.PostsFragment;

/**
 * Created by niranjanb on 13/04/17.
 */

public class FragmentPageAdapterMoreDetails extends FragmentPagerAdapter {

    private static final int TAB_COUNT = 2;
    private static final String TAB_TITLES[] = {"Albums", "Posts"};
    private static final int TAB_ICONS[] = {R.mipmap.albums, R.mipmap.posts};
    private Context mContext;

    public FragmentPageAdapterMoreDetails(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return AlbumsFragment.getInstance();
            case 1:
                return PostsFragment.getInstance();
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
        tv.setText(TAB_TITLES[position]);
        ImageView iv = (ImageView) view.findViewById(R.id.image_view_custom_tab_layout);
        iv.setImageResource(TAB_ICONS[position]);
        return view;
    }
}
