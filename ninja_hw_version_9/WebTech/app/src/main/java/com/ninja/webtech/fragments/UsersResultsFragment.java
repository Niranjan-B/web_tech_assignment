package com.ninja.webtech.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ninja.webtech.R;
import com.ninja.webtech.adapters.RecyclerViewAdapterUserResults;
import com.ninja.webtech.models.user.Datum;
import com.ninja.webtech.models.user.Users;
import com.ninja.webtech.network.RetrofitManager;
import com.ninja.webtech.utilities.OnItemClickListenerRV;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersResultsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterUserResults mRecyclerViewAdapter;
    private CompositeDisposable mCompositeDisposable;

    private Button mPrevious, mNext;

    public static UsersResultsFragment getNewInstance() {
        return new UsersResultsFragment();
    }


    public UsersResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_users_results, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_user_results);
        mPrevious = (Button) view.findViewById(R.id.previous_button_fragment_users);
        mNext = (Button) view.findViewById(R.id.next_button_fragment_users);

        mCompositeDisposable = new CompositeDisposable();

        initRecyclerView();

        Bundle bundle = getArguments();
        if (bundle != null) {
            // call retrofit here
            makeNetworkRequest(getArguments().getString("query"));
        } else {
            Log.d("users", "null bundle");
        }
        return view;
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void handleError(Throwable throwable) {
        Log.d("ninja", ""+throwable);
    }

    private void handleResponse(Users users) {
        mRecyclerViewAdapter = new RecyclerViewAdapterUserResults(new ArrayList<>(users.getData()), getContext(),
                datum -> Toast.makeText(getContext(), "" + datum.getId(), Toast.LENGTH_LONG).show());

        // check if previous key exists, else disable it
        if (users.getPaging().getPrevious() == null) {
            mPrevious.setEnabled(false);
        } else {
            mPrevious.setEnabled(true);
            mPrevious.setOnClickListener(view -> {
                makePageRequest(users.getPaging().getPrevious());
            });
        }

        // check if next key exists, else  disable it
        if (users.getPaging().getNext() == null) {
            mNext.setEnabled(false);
        } else {
            mNext.setEnabled(true);
            mNext.setOnClickListener(view -> {
                makePageRequest(users.getPaging().getNext());
            });
        }

        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mCompositeDisposable.clear();
    }

    private void makeNetworkRequest(String query) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("search_type", "users");
        queryMap.put("searched_keyword", query);

        mCompositeDisposable.add(
                RetrofitManager.getRetrofitInstance().getQueriedUsers(queryMap)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::handleResponse, this::handleError)
        );
    }

    private void makePageRequest(String url) {
        mCompositeDisposable.add(RetrofitManager.getRetrofitInstance().getNextPrevData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError)
        );
    }
}
