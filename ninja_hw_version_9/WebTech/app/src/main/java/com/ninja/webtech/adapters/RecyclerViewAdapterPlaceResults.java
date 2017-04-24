package com.ninja.webtech.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninja.webtech.R;
import com.ninja.webtech.models.place.Datum;
import com.ninja.webtech.utilities.OnItemClickListenerRVPlaces;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by niranjanb on 14/04/17.
 */

public class RecyclerViewAdapterPlaceResults extends RecyclerView.Adapter<RecyclerViewAdapterPlaceResults.ViewHolder> {

    ArrayList<Datum> mPlacesList = new ArrayList<>();
    Context mContext;
    OnItemClickListenerRVPlaces mClickListener;
    SharedPreferences mPref;

    public RecyclerViewAdapterPlaceResults(ArrayList<Datum> placesList, Context context, OnItemClickListenerRVPlaces onItemClickListenerRV) {
        this.mPlacesList = placesList;
        mContext = context;
        mClickListener = onItemClickListenerRV;
        mPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_recycler_view_results, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(mContext).load(mPlacesList.get(position).getPicture().getData().getUrl()).into(holder.mProfilePic);
        holder.mName.setText(mPlacesList.get(position).getName());
        holder.bind(mPlacesList.get(position), mClickListener);
        if (mPref.getString(mPlacesList.get(position).getId(), "").equals("")) {
            //Log.d("ninja", "empty " + mUsersList.get(position).getId());
            holder.mFav.setImageResource(R.drawable.ic_star_border_black_24dp);
        } else {
            holder.mFav.setImageResource(R.mipmap.favorites_on);
            //Log.d("ninja", "present");
        }
    }

    @Override
    public int getItemCount() {
        return mPlacesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mProfilePic, mFav;
        TextView mName;
        ImageView mMoreDetailView;

        public ViewHolder(View itemView) {
            super(itemView);
            mProfilePic = (ImageView) itemView.findViewById(R.id.image_view_custom_row);
            mName = (TextView) itemView.findViewById(R.id.text_view_custom_row);
            mFav = (ImageView) itemView.findViewById(R.id.image_view_star_custom_row);
            mMoreDetailView = (ImageView) itemView.findViewById(R.id.imageView2);
        }

        public void bind(final Datum datum, final OnItemClickListenerRVPlaces onItemClickListenerRV) {
            mMoreDetailView.setOnClickListener(view1 -> onItemClickListenerRV.onItemClick(datum));
        }
    }
}
