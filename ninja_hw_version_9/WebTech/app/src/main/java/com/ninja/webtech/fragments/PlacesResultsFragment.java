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
public class PlacesResultsFragment extends Fragment {

    public static PlacesResultsFragment getNewInstance() {
        return new PlacesResultsFragment();
    }


    public PlacesResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            Log.d("places", getArguments().getString("query"));
        } else {
            Log.d("places", "null bundle");
        }
        return inflater.inflate(R.layout.fragment_places_resuts, container, false);
    }

}
