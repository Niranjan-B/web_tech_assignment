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
    String mId;

    public FragmentPageAdapterMoreDetails(FragmentManager fm, Context context,String id) {
        super(fm);
        mContext = context;
        mId = id;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Fragment fragment = AlbumsFragment.getInstance();
                Bundle bundle = new Bundle();
                bundle.putString("id", mId);
                fragment.setArguments(bundle);
                return fragment;
            case 1:
                Fragment fragment1 = PostsFragment.getInstance();
                Bundle bundle1 = new Bundle();
                bundle1.putString("id", mId);
                fragment1.setArguments(bundle1);
                return fragment1;
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
