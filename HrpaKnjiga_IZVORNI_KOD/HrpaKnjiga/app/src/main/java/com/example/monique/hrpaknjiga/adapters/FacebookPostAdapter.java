/*  LITERATURA I IZVORI KOJI SU POSLUŽILI ZA IZRADU OVOG ADAPTERA
*   Picaso: http://square.github.io/picasso/
* */

package com.example.monique.hrpaknjiga.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.monique.hrpaknjiga.R;
import com.example.monique.hrpaknjiga.models.Book;
import com.example.monique.hrpaknjiga.models.FacebookPost;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.IOException;
import java.util.ArrayList;

public class FacebookPostAdapter extends BaseAdapter{

    ArrayList<FacebookPost> mPosts;

    public FacebookPostAdapter(ArrayList<FacebookPost> mPosts) {
        this.mPosts = mPosts;
    }


    @Override
    public int getCount() {
        return this.mPosts.size();
    }

    @Override
    public FacebookPost getItem(int position) {
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

        LayoutInflater mInflater = (LayoutInflater) parentContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(null == convertView) {

            convertView = mInflater.inflate(R.layout.facebook_post_element, null);
            postViewHolder = new PostViewHolder();

            postViewHolder.textTitle = (TextView) convertView.findViewById(R.id.bookPostTitle);
            postViewHolder.textAuthor = (TextView) convertView.findViewById(R.id.bookPostAuthor);
            postViewHolder.textDate = (TextView) convertView.findViewById(R.id.bookPostDate);
            postViewHolder.postImage = (ImageView) convertView.findViewById(R.id.postImage);

            convertView.setTag(postViewHolder);

        }
        else {
            postViewHolder = (PostViewHolder) convertView.getTag();
        }

        FacebookPost currentPost = (FacebookPost) getItem(position);

        if (currentPost != null) {
            postViewHolder.textTitle.setText(currentPost.getTitle().toUpperCase());
            postViewHolder.textAuthor.setText("Autor: " + currentPost.getAuthor());
            postViewHolder.textDate.setText(currentPost.getDate());
            Picasso.with(parentContext)
                    .load(currentPost.getImage())
                    .transform(new CropSquareTransformation())
                    .into(postViewHolder.postImage);
        }
        return convertView;
    }

    static class PostViewHolder {
        private ImageView postImage;
        private TextView textTitle;
        private TextView textAuthor;
        private TextView textDate;
    }

    public class CropSquareTransformation implements Transformation {
        @Override public Bitmap transform(Bitmap source) {
            int x = source.getWidth()*2;
            int y = source.getHeight()*2;
            Bitmap result = Bitmap.createScaledBitmap(source, x, y, false);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override public String key() { return "square()"; }
    }
}

