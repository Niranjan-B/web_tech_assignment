package com.ninja.webtech.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninja.webtech.R;
import com.ninja.webtech.adapters.FragmentPageAdapterFavorites;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {

    TabLayout mTabLayout;
    ViewPager mViewPager;


    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout_favorites_fragment);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager_favorites_fragment);
        FragmentPageAdapterFavorites adapter = new FragmentPageAdapterFavorites(getChildFragmentManager(), getActivity());

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }

        return view;
    }

}
