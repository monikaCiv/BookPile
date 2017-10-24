package com.example.monique.hrpaknjiga.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.monique.hrpaknjiga.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity   {

    public static final String TAG = MenuActivity.class.getSimpleName();

    @BindView(R.id.quotesButton) Button mQuotesButton;
    @BindView(R.id.readingHistoryButton) Button mHistoryButton;
    @BindView(R.id.readingDiaryButton) Button mDiaryButton;
    @BindView(R.id.facebookButton) Button mFbButton;
    @BindView(R.id.locationButton) Button mLocationButton;
    @BindView(R.id.infoButton) Button mInfoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);
        Log.d(TAG, "Main code is running.");
    }

    @OnClick(R.id.quotesButton)
    public void startQuotesActivity () {
        Intent intent = new Intent(this, QuotesActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.readingDiaryButton)
    public void startDiaryActivity () {
        Intent intent = new Intent(this, DiaryActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.readingHistoryButton)
    public void startReadingHistory () {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.locationButton)
    public void startLocationServices () {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.facebookButton)
    public void startFacebookServices () {
        Intent intent = new Intent(this, FacebookMenu.class);
        startActivity(intent);
    }

    @OnClick(R.id.infoButton)
    public void startInfo () {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

}
