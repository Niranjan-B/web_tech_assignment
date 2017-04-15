package com.ninja.webtech.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninja.webtech.R;
import com.ninja.webtech.models.group.Datum;
import com.ninja.webtech.utilities.OnItemClickListenerRVGroups;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by niranjanb on 15/04/17.
 */

public class RecyclerViewAdapterGroupResults extends RecyclerView.Adapter<RecyclerViewAdapterGroupResults.ViewHolder> {

    ArrayList<Datum> mGroupResults = new ArrayList<>();
    Context mContext;
    OnItemClickListenerRVGroups mClickListener;

    public RecyclerViewAdapterGroupResults(ArrayList<Datum> groupsList, Context context, OnItemClickListenerRVGroups onItemClickListenerRV) {
        this.mGroupResults = groupsList;
        mContext = context;
        mClickListener = onItemClickListenerRV;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_recycler_view_results, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(mContext).load(mGroupResults.get(position).getPicture().getData().getUrl()).into(holder.mProfilePic);
        holder.mName.setText(mGroupResults.get(position).getName());
        holder.bind(mGroupResults.get(position), mClickListener);
    }

    @Override
    public int getItemCount() {
        return mGroupResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mProfilePic;
        TextView mName;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);

            mProfilePic = (ImageView) itemView.findViewById(R.id.image_view_custom_row);
            mName = (TextView) itemView.findViewById(R.id.text_view_custom_row);
            view = itemView;
        }

        public void bind(final Datum datum, final OnItemClickListenerRVGroups onItemClickListenerRV) {
            view.setOnClickListener(view1 -> onItemClickListenerRV.onItemClick(datum));
        }
    }

}
