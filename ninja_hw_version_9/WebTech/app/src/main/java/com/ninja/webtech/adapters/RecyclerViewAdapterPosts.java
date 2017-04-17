package com.ninja.webtech.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninja.webtech.R;
import com.ninja.webtech.models.post.Posts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by niranjanb on 16/04/17.
 */

public class RecyclerViewAdapterPosts extends RecyclerView.Adapter<RecyclerViewAdapterPosts.ViewHolder> {

    private Context mContext;
    private Posts mPosts;

    public RecyclerViewAdapterPosts(Context context,Posts posts) {
        mContext = context;
        mPosts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row_fragment_posts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(mContext).load(mPosts.getPicture().getData().getUrl()).into(holder.mProfilePic);
        holder.mName.setText(mPosts.getName());
        holder.mDate.setText(mPosts.getPosts().getData().get(position).getCreatedTime());
        if (mPosts.getPosts().getData().get(position).getMessage() != null) {
            holder.mMessage.setText(mPosts.getPosts().getData().get(position).getMessage());
        } else {
            holder.mMessage.setText(mPosts.getPosts().getData().get(position).getStory());
        }
    }

    @Override
    public int getItemCount() {
        return mPosts.getPosts().getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mProfilePic;
        TextView mName, mDate, mMessage;

        public ViewHolder(View itemView) {
            super(itemView);

            mProfilePic = (ImageView) itemView.findViewById(R.id.image_view_custom_row_posts);
            mName = (TextView) itemView.findViewById(R.id.text_view_name_custom_row_posts);
            mDate = (TextView) itemView.findViewById(R.id.text_view_date_custom_row_posts);
            mMessage = (TextView) itemView.findViewById(R.id.text_view_message_custom_row_posts);
        }
    }
}
