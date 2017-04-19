package com.ninja.webtech.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
public class EventsFragment extends Fragment {

    RecyclerView mRecyclerView;
    static RecyclerViewFavoritesAdapter mAdapter;
    static Gson gson;
    static ArrayList<StorageClass> mList;
    static SharedPreferences mPref;


    public EventsFragment() {
        // Required empty public constructor
    }

    public static EventsFragment getInstance() {
        return new EventsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_events_fav);

        gson = new Gson();
        mList = new ArrayList<>();
        mPref = PreferenceManager.getDefaultSharedPreferences(getContext());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);

        Map<String, ?> keys = mPref.getAll();
        for (Map.Entry<String, ?> set : keys.entrySet()) {
            Log.d("ninja", "events = " + set.getValue().toString());
            try {
                StorageClass tempClass = gson.fromJson(set.getValue().toString(), StorageClass.class);
                if (tempClass.mType.equals("events")) {
                    mList.add(tempClass);
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }

        mAdapter = new RecyclerViewFavoritesAdapter(getContext(), mList, "events");
        mRecyclerView.setAdapter(mAdapter);

        // Inflate the layout for this fragment
        return view;
    }

    public static void refreshRecyclerView() {
        mList.clear();

        Map<String, ?> keys = mPref.getAll();
        for (Map.Entry<String, ?> set : keys.entrySet()) {
            try {
                StorageClass tempClass = gson.fromJson(set.getValue().toString(), StorageClass.class);
                if (tempClass.mType.equals("events")) {
                    mList.add(tempClass);
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }

        mAdapter.refreshView(mList);
    }

}
