package com.ninja.webtech.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ninja.webtech.R;
import com.ninja.webtech.adapters.RecyclerViewFavoritesAdapter;
import com.ninja.webtech.utilities.StorageClass;

import java.util.ArrayList;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlacesFragment extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerViewFavoritesAdapter mAdapter;
    Gson gson;
    ArrayList<StorageClass> mList;
    SharedPreferences mPref;

    public PlacesFragment() {
        // Required empty public constructor
    }

    public static PlacesFragment getNewInstance() {
        return new PlacesFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_places, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_places_favs);
        gson = new Gson();
        mList = new ArrayList<>();
        mPref = PreferenceManager.getDefaultSharedPreferences(getContext());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);

        Map<String, ?> keys = mPref.getAll();
        for (Map.Entry<String, ?> set : keys.entrySet()) {
            StorageClass tempClass = gson.fromJson(set.getValue().toString(), StorageClass.class);
            if (tempClass.mType.equals("places")) {
                mList.add(tempClass);
            }
        }

        mAdapter = new RecyclerViewFavoritesAdapter(getContext(), mList);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

}
