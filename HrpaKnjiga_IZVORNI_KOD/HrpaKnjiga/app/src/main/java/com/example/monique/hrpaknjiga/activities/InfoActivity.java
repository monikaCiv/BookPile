package com.example.monique.hrpaknjiga.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.monique.hrpaknjiga.R;
import com.example.monique.hrpaknjiga.adapters.FacebookPostAdapter;
import com.example.monique.hrpaknjiga.adapters.InfoPostAdapter;
import com.example.monique.hrpaknjiga.models.FacebookPost;
import com.example.monique.hrpaknjiga.models.InfoPost;
import com.example.monique.hrpaknjiga.models.InfoPostBook;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String TAG = InfoActivity.class.getSimpleName();

    @BindView(R.id.infoList) ListView mInfoList;

    private InfoPostBook mInfoBook  = new InfoPostBook();;
    private InfoPostAdapter mInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);


        this.mInfoList.setOnItemClickListener(this);
        this.mInfoAdapter= new InfoPostAdapter(mInfoBook.getInfo());
        this.mInfoList.setAdapter(this.mInfoAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        InfoPostAdapter adapter = (InfoPostAdapter) parent.getAdapter();
        InfoPost selectedPost = adapter.getItem(position);
        String url = selectedPost.getLink();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
