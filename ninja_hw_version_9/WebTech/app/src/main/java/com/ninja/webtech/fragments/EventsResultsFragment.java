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
import com.ninja.webtech.adapters.RecyclerViewAdapterEventResults;
import com.ninja.webtech.adapters.RecyclerViewAdapterUserResults;
import com.ninja.webtech.models.event.Events;
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
public class EventsResultsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterEventResults mRecyclerViewAdapter;
    private CompositeDisposable mCompositeDisposable;

    private Button mPrevious, mNext;

    public static EventsResultsFragment getInstance() {
        return new EventsResultsFragment();
    }

    public EventsResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_events_results, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_event_results);
        mPrevious = (Button) view.findViewById(R.id.previous_button_fragment_events);
        mNext = (Button) view.findViewById(R.id.next_button_fragment_events);

        mCompositeDisposable = new CompositeDisposable();

        initRecyclerView();

        Bundle bundle = getArguments();
        if (bundle != null) {
            //Log.d("events", getArguments().getString("query"));
            makeNetworkRequest(getArguments().getString("query"));
        } else {
            Log.d("events", "null bundle");
        }
        return view;
    }

    private void makeNetworkRequest(String query) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("search_type", "events");
        queryMap.put("searched_keyword", query);

        mCompositeDisposable.add(
                RetrofitManager.getRetrofitInstance().getQueriedUsersEvents(queryMap)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void handleError(Throwable throwable) {
        Log.d("ninja", ""+throwable);
    }

    private void handleResponse(Events events) {
        mRecyclerViewAdapter = new RecyclerViewAdapterEventResults(new ArrayList<>(events.getData()), getContext(),
                datum -> {
                    Intent intent = new Intent(getActivity(), MoreDetailsActivity.class);
                    intent.putExtra("id", "" + datum.getId());
                    startActivity(intent);
                });

        // check if previous key exists, else disable it
        if (events.getPaging().getPrevious() == null) {
            mPrevious.setEnabled(false);
        } else {
            mPrevious.setEnabled(true);
            mPrevious.setOnClickListener(view -> {
                makePageRequest(events.getPaging().getPrevious());
            });
        }

        // check if next key exists, else  disable it
        if (events.getPaging().getNext() == null) {
            mNext.setEnabled(false);
        } else {
            mNext.setEnabled(true);
            mNext.setOnClickListener(view -> {
                makePageRequest(events.getPaging().getNext());
            });
        }

        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    private void makePageRequest(String previous) {
        mCompositeDisposable.add(RetrofitManager.getRetrofitInstance().getNextPrevDataEvents(previous)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError)
        );
    }


    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCompositeDisposable.clear();
    }

}
