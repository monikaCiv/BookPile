package com.example.monique.hrpaknjiga.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.monique.hrpaknjiga.R;
import com.example.monique.hrpaknjiga.models.FacebookPost;
import com.example.monique.hrpaknjiga.models.InfoPost;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

public class InfoPostAdapter extends BaseAdapter{

    ArrayList<InfoPost> mPosts;

    public InfoPostAdapter(ArrayList<InfoPost> mPosts) {
        this.mPosts = mPosts;
    }


    @Override
    public int getCount() {
        return this.mPosts.size();
    }

    @Override
    public InfoPost getItem(int position) {
        return this.mPosts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PostViewHolder postViewHolder;
        Context parentContext = parent.getContext();

        if(null == convertView) {

            LayoutInflater mInflater = (LayoutInflater) parentContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.info_element, null);

            postViewHolder = new PostViewHolder();

            postViewHolder.textTitle = (TextView) convertView.findViewById(R.id.infoTitle);
            postViewHolder.textDescription = (TextView) convertView.findViewById(R.id.infoDescription);
            postViewHolder.postImage = (ImageView) convertView.findViewById(R.id.infoImage);

            convertView.setTag(postViewHolder);

        }
        else {
            postViewHolder = (PostViewHolder) convertView.getTag();
        }

        InfoPost currentPost = (InfoPost) getItem(position);

        if (currentPost != null) {
            postViewHolder.textTitle.setText(currentPost.getTitle());
            postViewHolder.textDescription.setText(currentPost.getText());
            postViewHolder.postImage.setImageResource(currentPost.getImage());
        }
        return convertView;
    }


    static class PostViewHolder {
        private ImageView postImage;
        private TextView textTitle;
        private TextView textDescription;
    }
}
