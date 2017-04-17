package com.ninja.webtech.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ninja.webtech.R;
import com.ninja.webtech.adapters.RecyclerViewAdapterPosts;
import com.ninja.webtech.models.post.Posts;
import com.ninja.webtech.network.RetrofitManager;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment {

    RecyclerViewAdapterPosts mAdapter;
    RecyclerView mRecyclerView;
    TextView mTextView;

    public static PostsFragment getInstance() {
        return new PostsFragment();
    }


    public PostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_posts);
        mTextView = (TextView) view.findViewById(R.id.text_view_no_posts_fragment);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);

        Bundle bundle = getArguments();
        if (bundle != null) {
            Log.d("id from posts = ", getArguments().getString("id"));

            Map<String, String> queryMap = new HashMap<>();
            queryMap.put("search_type", "posts");
            queryMap.put("searched_keyword", getArguments().getString("id"));

            RetrofitManager.getRetrofitInstance().getPosts(queryMap)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponse, this::handleError);
        } else {
            Log.d("id from posts = ", "onCreateView: null bundle");
        }

        return view;
    }

    private void handleError(Throwable throwable) {
        Log.d("ninja error", "" + throwable);
    }

    private void handleResponse(Posts posts) {
        if (posts.getPosts() != null) {
            mRecyclerView.setVisibility(View.VISIBLE);
            mTextView.setVisibility(View.INVISIBLE);

            mAdapter = new RecyclerViewAdapterPosts(getActivity(), posts);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mRecyclerView.setVisibility(View.INVISIBLE);
            mTextView.setVisibility(View.VISIBLE);
        }
    }

}
