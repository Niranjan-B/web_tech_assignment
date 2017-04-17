package com.ninja.webtech.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ninja.webtech.R;
import com.ninja.webtech.activities.MoreDetailsActivity;
import com.ninja.webtech.adapters.RecyclerViewAdapterGroupResults;
import com.ninja.webtech.models.group.Groups;
import com.ninja.webtech.network.RetrofitManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsResultsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterGroupResults mRecyclerViewAdapter = null;
    private CompositeDisposable mCompositeDisposable;

    private Button mPrevious, mNext;

    public static GroupsResultsFragment getNewInstance() {
        return new GroupsResultsFragment();
    }

    public GroupsResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_groups_results, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_group_results);
        mPrevious = (Button) view.findViewById(R.id.previous_button_fragment_groups);
        mNext = (Button) view.findViewById(R.id.next_button_fragment_groups);

        mCompositeDisposable = new CompositeDisposable();

        initRecyclerView();

        Bundle bundle = getArguments();
        if (bundle != null) {
            //Log.d("groups", getArguments().getString("query"));
            makeNetworkRequest(getArguments().getString("query"));
        } else {
            Log.d("groups", "null bundle");
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCompositeDisposable.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mRecyclerViewAdapter != null) {
            mRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    private void makeNetworkRequest(String query) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("search_type", "groups");
        queryMap.put("searched_keyword", query);

        mCompositeDisposable.add(
                RetrofitManager.getRetrofitInstance().getQueriedGroups(queryMap)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleError(Throwable throwable) {
        Log.d("ninja", ""+throwable);
    }

    private void handleResponse(Groups groups) {
        mRecyclerViewAdapter = new RecyclerViewAdapterGroupResults(new ArrayList<>(groups.getData()), getContext(),
                datum -> {
                    Intent intent = new Intent(getActivity(), MoreDetailsActivity.class);
                    intent.putExtra("id", "" + datum.getId());
                    intent.putExtra("picture", datum.getPicture().getData().getUrl());
                    intent.putExtra("name", datum.getName());
                    intent.putExtra("type", "groups");
                    startActivity(intent);
                });

        // check if previous key exists, else disable it
        if (groups.getPaging().getPrevious() == null) {
            mPrevious.setEnabled(false);
        } else {
            mPrevious.setEnabled(true);
            mPrevious.setOnClickListener(view -> {
                makePageRequest(groups.getPaging().getPrevious());
            });
        }

        // check if next key exists, else  disable it
        if (groups.getPaging().getNext() == null) {
            mNext.setEnabled(false);
        } else {
            mNext.setEnabled(true);
            mNext.setOnClickListener(view -> {
                makePageRequest(groups.getPaging().getNext());
            });
        }

        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void makePageRequest(String url) {
        mCompositeDisposable.add(RetrofitManager.getRetrofitInstance().getNextPrevDataGroups(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError)
        );
    }

}
