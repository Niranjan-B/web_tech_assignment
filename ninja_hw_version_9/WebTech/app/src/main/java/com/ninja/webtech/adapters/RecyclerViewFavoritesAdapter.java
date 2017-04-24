package com.ninja.webtech.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninja.webtech.R;
import com.ninja.webtech.activities.MoreDetailsActivity;
import com.ninja.webtech.utilities.StorageClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by niranjanb on 17/04/17.
 */

public class RecyclerViewFavoritesAdapter extends RecyclerView.Adapter<RecyclerViewFavoritesAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<StorageClass> mGeneralList;
    private String mType;

    public RecyclerViewFavoritesAdapter(Context context, ArrayList<StorageClass> list, String type) {
        mContext = context;
        mGeneralList = new ArrayList<>(list);
        mType = type;
    }

    public void refreshView(ArrayList<StorageClass> list) {
        mGeneralList.clear();
        mGeneralList = new ArrayList<>(list);
        notifyDataSetChanged();
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

        // bad programming, never set listeners inside adapter...SHIT HAPPENS!!!!!!
        holder.mMoreDetailsView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, MoreDetailsActivity.class);
            intent.putExtra("id", "" + mGeneralList.get(position).mId);
            intent.putExtra("picture", mGeneralList.get(position).mUrl);
            intent.putExtra("name", mGeneralList.get(position).mName);
            intent.putExtra("type", mType);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mGeneralList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mProfilePic, mFavStar;
        TextView mName;
        ImageView mMoreDetailsView;

        public ViewHolder(View itemView) {
            super(itemView);

            mProfilePic = (ImageView) itemView.findViewById(R.id.image_view_custom_row);
            mFavStar = (ImageView) itemView.findViewById(R.id.image_view_star_custom_row);
            mName = (TextView) itemView.findViewById(R.id.text_view_custom_row);
            mMoreDetailsView = (ImageView) itemView.findViewById(R.id.imageView2);
        }
    }

}
