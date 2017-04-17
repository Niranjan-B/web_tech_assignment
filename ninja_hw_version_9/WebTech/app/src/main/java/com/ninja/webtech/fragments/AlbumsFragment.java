package com.ninja.webtech.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.ninja.webtech.R;
import com.ninja.webtech.adapters.ExpandableListViewAdapterAlbums;
import com.ninja.webtech.models.album.Albums;
import com.ninja.webtech.network.RetrofitManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumsFragment extends Fragment {

    ExpandableListView mExpandableListView;
    ExpandableListViewAdapterAlbums mExpandableListViewAdapter;
    List<String> mHeaders;
    HashMap<String, List<String>> mExpandableContent;
    TextView mTextView;

    public static AlbumsFragment getInstance() {
        return new AlbumsFragment();
    }

    public AlbumsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_albums, container, false);

        mExpandableListView = (ExpandableListView) view.findViewById(R.id.expandable_list_view_fragment_albums);
        mTextView = (TextView) view.findViewById(R.id.text_view_no_albums_fragment);


        Bundle bundle = getArguments();
        if (bundle != null) {
            String id = getArguments().getString("id");

            // calling retrofit service here
            Map<String, String> queryMap = new HashMap<>();
            queryMap.put("search_type", "albums");
            queryMap.put("searched_keyword", id);

            RetrofitManager.getRetrofitInstance().getAlbums(queryMap)
                           .subscribeOn(Schedulers.newThread())
                           .observeOn(AndroidSchedulers.mainThread())
                           .subscribe(this::handleResponse, this::handleError);

        } else {
            Log.d("id from albums = ", "onCreateView: null bundle");
        }

        return view;
    }

    private void handleResponse(Albums albums) {
        //Log.d("ninja", "" + albums.getAlbums().getData());

        // continue displaying the list-view
        mExpandableListView.setVisibility(View.VISIBLE);
        mTextView.setVisibility(View.GONE);

        ArrayList<String> tempList = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        // adding all headers into array-list
        for (int i=0; i<albums.getAlbums().getData().size(); i++) {
            tempList.add(albums.getAlbums().getData().get(i).getName());
        }
        mHeaders = new ArrayList<>(tempList);

        // adding stuff to hash-map
        for (int i=0; i<albums.getAlbums().getData().size(); i++) {
            ArrayList<String> imgList = new ArrayList<>();
            for (int j=0; j<2; j++) {
                imgList.add(albums.getAlbums().getData().get(i).getPhotos().getData().get(j).getImages().get(0).getSource());
            }
            map.put(mHeaders.get(i), imgList);
        }

        mExpandableContent = map;
        mExpandableListViewAdapter = new ExpandableListViewAdapterAlbums(getActivity(), mHeaders, mExpandableContent);
        mExpandableListView.setAdapter(mExpandableListViewAdapter);
    }

    private void handleError(Throwable throwable) {
        // show no albums available here
        mExpandableListView.setVisibility(View.GONE);
        mTextView.setVisibility(View.VISIBLE);
    }

}
