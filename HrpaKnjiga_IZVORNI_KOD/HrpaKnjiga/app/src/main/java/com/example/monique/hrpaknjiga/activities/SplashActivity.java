package com.example.monique.hrpaknjiga.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.monique.hrpaknjiga.R;

public class SplashActivity extends AppCompatActivity {

    //Duljina prikaza "splash" zaslona u milisekundama.
    public static final int DISPLAY_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Aktivnost zauzima puni zaslon. Nisu vidljivi postotak baterije, vrijeme i slično.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Postavi layout.
        setContentView(R.layout.activity_splash);

        /* Novi Handler za početak MENU aktivnosti.
         * Zatvoriti "splash" zaslon nakon zadanog vremena, postavi "delay". */
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                //Kreiraj intent za pozivanje nove aktivnosti.
                Intent mainIntent = new Intent(SplashActivity.this, MenuActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, DISPLAY_TIME);
    }
}

