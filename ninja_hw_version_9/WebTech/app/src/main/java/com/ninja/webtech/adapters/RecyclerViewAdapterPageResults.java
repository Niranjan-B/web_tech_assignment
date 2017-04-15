package com.ninja.webtech.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninja.webtech.R;
import com.ninja.webtech.models.page.Datum;
import com.ninja.webtech.utilities.OnItemClickListenerRVPages;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by niranjanb on 14/04/17.
 */

public class RecyclerViewAdapterPageResults extends RecyclerView.Adapter<RecyclerViewAdapterPageResults.ViewHolder> {


    ArrayList<Datum> mPageResults = new ArrayList<>();
    Context mContext;
    OnItemClickListenerRVPages mClickListener;

    public RecyclerViewAdapterPageResults(ArrayList<Datum> usersList, Context context, OnItemClickListenerRVPages onItemClickListenerRV) {
        this.mPageResults = usersList;
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
        Picasso.with(mContext).load(mPageResults.get(position).getPicture().getData().getUrl()).into(holder.mProfilePic);
        holder.mName.setText(mPageResults.get(position).getName());
        holder.bind(mPageResults.get(position), mClickListener);
    }

    @Override
    public int getItemCount() {
        return mPageResults.size();
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

        public void bind(final Datum datum, final OnItemClickListenerRVPages onItemClickListenerRV) {
            view.setOnClickListener(view1 -> onItemClickListenerRV.onItemClick(datum));
        }
    }

}
