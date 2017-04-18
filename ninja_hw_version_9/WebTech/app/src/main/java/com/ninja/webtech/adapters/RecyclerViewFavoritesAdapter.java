package com.ninja.webtech.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninja.webtech.R;
import com.ninja.webtech.utilities.StorageClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by niranjanb on 17/04/17.
 */

public class RecyclerViewFavoritesAdapter extends RecyclerView.Adapter<RecyclerViewFavoritesAdapter.ViewHolder> {

    Context mContext;
    ArrayList<StorageClass> mGeneralList;

    public RecyclerViewFavoritesAdapter(Context context, ArrayList<StorageClass> list) {
        mContext = context;
        mGeneralList = new ArrayList<>(list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_row_recycler_view_results, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(mContext).load(mGeneralList.get(position).mUrl).into(holder.mProfilePic);
        holder.mFavStar.setImageResource(R.mipmap.favorites_on);
        holder.mName.setText(mGeneralList.get(position).mName);
    }

    @Override
    public int getItemCount() {
        return mGeneralList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mProfilePic, mFavStar;
        TextView mName;

        public ViewHolder(View itemView) {
            super(itemView);

            mProfilePic = (ImageView) itemView.findViewById(R.id.image_view_custom_row);
            mFavStar = (ImageView) itemView.findViewById(R.id.image_view_star_custom_row);
            mName = (TextView) itemView.findViewById(R.id.text_view_custom_row);
        }
    }

}
