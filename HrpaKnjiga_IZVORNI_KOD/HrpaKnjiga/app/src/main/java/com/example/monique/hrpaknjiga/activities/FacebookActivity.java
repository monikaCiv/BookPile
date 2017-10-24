/* LITERATURA KOJA JE POSLUŽILA KAO POMOĆ ZA IZRADU OVE AKTIVNOSTI
*
*  Facebook Graph API:          https://developers.facebook.com/docs/graph-api/overview
*  OkHttp:    https://developers.facebook.com/docs/android/
*  StackOverflow post:          http://stackoverflow.com/questions/12168452/long-lasting-fb-access-token-for-server-to-pull-fb-page-info
*  Find my id:                  http://findmyfbid.com/
*  Graph API Explorer:          https://developers.facebook.com/tools/explorer/145634995501895/
******************************************************************************************************************/

package com.example.monique.hrpaknjiga.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monique.hrpaknjiga.R;
import com.example.monique.hrpaknjiga.adapters.DiaryBookAdapter;
import com.example.monique.hrpaknjiga.adapters.FacebookPostAdapter;
import com.example.monique.hrpaknjiga.models.FacebookPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FacebookActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener {

    public static final String TAG = FacebookActivity.class.getSimpleName();

    @BindView(R.id.postsList) ListView mPostsList;
    @BindView(R.id.popUpView) ScrollView mPopUp;
    @BindView(R.id.postTitle) TextView mTvTitle;
    @BindView(R.id.postAuthor) TextView mTvAuthor;
    @BindView(R.id.postDescription) TextView mTvDescription;
    @BindView(R.id.closeButton) Button mCloseButton;
    @BindView(R.id.activityTitle) TextView mTvAlbumName;
    @BindView(R.id.postDate) TextView mTvPostDate;

    private FacebookPost[] receivedPosts;
    private FacebookPostAdapter mPostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        Log.d(TAG, "Main code is running.");
        ButterKnife.bind(this);

        this.mPostsList.setOnItemClickListener(this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra("ARRAY");
        receivedPosts = Arrays.copyOf(parcelables, parcelables.length, FacebookPost[].class);
        ArrayList<FacebookPost> postsList =
                new ArrayList<FacebookPost>(Arrays.asList(receivedPosts));
        //Obrni niz tako da najpprije pokazuje objave novijeg datuma
        Collections.reverse(postsList);
        this.mPostAdapter = new FacebookPostAdapter(postsList);
        this.mPostsList.setAdapter(this.mPostAdapter);

        String title = intent.getStringExtra("TITLE");
        mTvAlbumName.setText(title);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FacebookPostAdapter adapter = (FacebookPostAdapter) parent.getAdapter();
        FacebookPost selectedPost = adapter.getItem(position);
        mPopUp.setVisibility(View.VISIBLE);
        mTvTitle.setText(selectedPost.getTitle().toUpperCase());
        mTvAuthor.setText(selectedPost.getAuthor());
        mTvDescription.setText(selectedPost.getDescription());
        mTvPostDate.setText(selectedPost.getDate());
    }

    @OnClick(R.id.closeButton)
    public void closeDetails() {
        mPopUp.setVisibility(View.GONE);
    }
}
