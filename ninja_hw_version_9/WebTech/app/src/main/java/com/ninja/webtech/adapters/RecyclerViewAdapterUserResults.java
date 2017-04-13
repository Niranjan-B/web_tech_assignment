package com.ninja.webtech.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninja.webtech.R;
import com.ninja.webtech.models.user.Datum;
import com.ninja.webtech.utilities.OnItemClickListenerRV;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by niranjanb on 13/04/17.
 */

public class RecyclerViewAdapterUserResults extends RecyclerView.Adapter<RecyclerViewAdapterUserResults.ViewHolder> {

    ArrayList<Datum> mUsersList = new ArrayList<>();
    Context mContext;
    OnItemClickListenerRV mClickListener;

    public RecyclerViewAdapterUserResults(ArrayList<Datum> usersList, Context context, OnItemClickListenerRV onItemClickListenerRV) {
        this.mUsersList = usersList;
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
        Picasso.with(mContext).load(mUsersList.get(position).getPicture().getData().getUrl()).into(holder.mProfilePic);
        holder.mName.setText(mUsersList.get(position).getName());
        holder.bind(mUsersList.get(position), mClickListener);
    }

    @Override
    public int getItemCount() {
        return mUsersList.size();
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

        public void bind(final Datum datum, final OnItemClickListenerRV onItemClickListenerRV) {
            view.setOnClickListener(view1 -> onItemClickListenerRV.onItemClick(datum));
        }
    }

}
