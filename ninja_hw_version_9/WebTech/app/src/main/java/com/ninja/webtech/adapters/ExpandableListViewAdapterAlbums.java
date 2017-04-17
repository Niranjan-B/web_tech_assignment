package com.ninja.webtech.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninja.webtech.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

/**
 * Created by niranjanb on 16/04/17.
 */

public class ExpandableListViewAdapterAlbums extends BaseExpandableListAdapter {
    private Context mContext;
    private List<String> mHeaderTitleList;
    private HashMap<String, List<String>> mExpandableListDetail;

    public ExpandableListViewAdapterAlbums(Context context, List<String> headerTitleList, HashMap<String,
            List<String>> expandableListContent) {
        mContext = context;
        mHeaderTitleList = headerTitleList;
        mExpandableListDetail = expandableListContent;
    }

    @Override
    public int getGroupCount() {
        return mHeaderTitleList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mExpandableListDetail.get(mHeaderTitleList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return mHeaderTitleList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.mExpandableListDetail.get(mHeaderTitleList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String listTitle = (String) getGroup(i);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.group_header_expandable_list_view, null);
        }
        TextView headerText = (TextView) view.findViewById(R.id.text_view_group_header_expandable_list_view);
        headerText.setText(listTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String expandedListText = (String) getChild(i, i1);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_item_expandable_list_view, null);
        }
        ImageView imgView = (ImageView) view.findViewById(R.id.image_view_expandable_list_view);
        Picasso.with(mContext).load(expandedListText).into(imgView);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
