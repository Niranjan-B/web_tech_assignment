package com.ninja.webtech.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ninja.webtech.R;
import com.ninja.webtech.models.user.Users;
import com.ninja.webtech.network.RetrofitManager;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersResultsFragment extends Fragment {

    public static UsersResultsFragment getNewInstance() {
        return new UsersResultsFragment();
    }


    public UsersResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            Log.d("users", getArguments().getString("query"));

            // call retrofit here
            Map<String, String> queryMap = new HashMap<>();
            queryMap.put("search_type", "users");
            queryMap.put("searched_keyword", getArguments().getString("query"));

            RetrofitManager.getRetrofitInstance().getQueriedUsers(queryMap)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponse, this::handleError);

        } else {
            Log.d("users", "null bundle");
        }
        return inflater.inflate(R.layout.fragment_users_results, container, false);
    }

    private void handleError(Throwable throwable) {
        Log.d("ninja", ""+throwable);
    }

    private void handleResponse(Users users) {
        Log.d("ninja", ""+users.getData());
    }

}
