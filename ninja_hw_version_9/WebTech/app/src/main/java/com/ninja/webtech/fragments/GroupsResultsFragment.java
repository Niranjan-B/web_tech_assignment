package com.ninja.webtech.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninja.webtech.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsResultsFragment extends Fragment {

    public static GroupsResultsFragment getNewInstance() {
        return new GroupsResultsFragment();
    }

    public GroupsResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            Log.d("groups", getArguments().getString("query"));
        } else {
            Log.d("groups", "null bundle");
        }
        return inflater.inflate(R.layout.fragment_groups_results, container, false);
    }

}
