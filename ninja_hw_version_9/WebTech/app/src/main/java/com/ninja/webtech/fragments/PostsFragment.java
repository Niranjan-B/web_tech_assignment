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
public class PostsFragment extends Fragment {

    public static PostsFragment getInstance() {
        return new PostsFragment();
    }


    public PostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            Log.d("id from posts = ", getArguments().getString("id"));
        } else {
            Log.d("id from posts = ", "onCreateView: null bundle");
        }

        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

}