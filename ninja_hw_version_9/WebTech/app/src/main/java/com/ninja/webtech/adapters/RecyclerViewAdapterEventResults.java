package com.ninja.webtech.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninja.webtech.R;
import com.ninja.webtech.models.event.Datum;
import com.ninja.webtech.utilities.OnItemClickListenerRVEvents;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by niranjanb on 14/04/17.
 */

public class RecyclerViewAdapterEventResults extends RecyclerView.Adapter<RecyclerViewAdapterEventResults.ViewHolder> {

    ArrayList<Datum> mEventResults = new ArrayList<>();
    Context mContext;
    OnItemClickListenerRVEvents mClickListener;

    public RecyclerViewAdapterEventResults(ArrayList<Datum> usersList, Context context, OnItemClickListenerRVEvents onItemClickListenerRV) {
        this.mEventResults = usersList;
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
        Picasso.with(mContext).load(mEventResults.get(position).getPicture().getData().getUrl()).into(holder.mProfilePic);
        holder.mName.setText(mEventResults.get(position).getName());
        holder.bind(mEventResults.get(position), mClickListener);
    }

    @Override
    public int getItemCount() {
        return mEventResults.size();
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

        public void bind(final Datum datum, final OnItemClickListenerRVEvents onItemClickListenerRV) {
            view.setOnClickListener(view1 -> onItemClickListenerRV.onItemClick(datum));
        }
    }
}
