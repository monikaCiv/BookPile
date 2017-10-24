package com.example.monique.hrpaknjiga.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.monique.hrpaknjiga.R;
import com.example.monique.hrpaknjiga.models.FacebookPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FacebookMenu extends AppCompatActivity {

    public static final String TAG = FacebookMenu.class.getSimpleName();

    @BindView(R.id.bookIdButton) Button mBookIdButton;
    @BindView(R.id.reviewButton) Button mReviewButton;
    @BindView(R.id.listsButton) Button mListsButton;
    @BindView(R.id.movieButton) Button mMovieButton;

    private static final String BookIDs = "408374535970602";
    private static final String MiniRecenzije = "409389049202484";
    private static final String BookToMovie = "415424628598926";
    private static final String TopLists = "408505212624201";

    private FacebookPost[] BookIDsPosts;
    private FacebookPost[] MiniRecenzijePosts;
    private FacebookPost[] BookToMoviePosts;
    private FacebookPost[] TopListsPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_menu);
        ButterKnife.bind(this);
    }

    public void startRequest(final String albumName) {

        String apiURL = "https://graph.facebook.com/v2.2/";
        String pageID = "407919446016111";
        String accessToken = "EAAC4RiaFFbIBAPtVZC8n2DuFilXYjyHEskSRrtmC9tm7K2bq9Ia1FcClRdEVfn2HixoUjOExShGRLxPms47pwnZC90Swr4dxfoB8XDZB4y379XQJZB8JmVAhiMKHeiGRP34z1c0ItPrGpZBlUpY0MiupIsonSVwEZD";
        String endpointFields = "/photos?fields=id,name,source,created_time&limit=100&access_token=";

        StringBuilder facebookApiUrl =
                new StringBuilder(apiURL);

        //dodaj koliko čega treba za dobiti slike iz albuma
        facebookApiUrl.append(albumName)
                .append(endpointFields)
                .append(accessToken);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(facebookApiUrl.toString())
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(FacebookMenu.this, "Neuspješno dohvaćanje podataka.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        Log.d(TAG, jsonData);
                        if (albumName.equals(BookIDs)) {
                            BookIDsPosts = getAllPhotos(jsonData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(FacebookMenu.this, FacebookActivity.class);
                                    intent.putExtra("ARRAY", BookIDsPosts);
                                    intent.putExtra("TITLE", "Book IDs");
                                    startActivity(intent);
                                }
                            });
                            //Log.d(TAG, "DULJINA NIZA BOOK ID: " + BookIDsPosts.length);
                        } else if (albumName.equals(MiniRecenzije)) {
                            MiniRecenzijePosts = getAllPhotos(jsonData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(FacebookMenu.this, FacebookActivity.class);
                                    intent.putExtra("ARRAY", MiniRecenzijePosts);
                                    intent.putExtra("TITLE", "Mini Recenzije");
                                    startActivity(intent);
                                }
                            });
                            Log.d(TAG, "DULJINA NIZA MINI RECENZIJE: " + MiniRecenzijePosts.length);
                        } else if (albumName.equals(TopLists)) {
                            TopListsPosts = getAllPhotos(jsonData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(FacebookMenu.this, FacebookActivity.class);
                                    intent.putExtra("ARRAY", TopListsPosts);
                                    intent.putExtra("TITLE", "Top liste i tagovi");
                                    startActivity(intent);
                                }
                            });
                            //Log.d(TAG, "DULJINA NIZA TOP LISTE: " + TopListsPosts.length);
                        } else if (albumName.equals(BookToMovie)) {
                            BookToMoviePosts = getAllPhotos(jsonData);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(FacebookMenu.this, FacebookActivity.class);
                                    intent.putExtra("ARRAY", BookToMoviePosts);
                                    intent.putExtra("TITLE", "Filmske adaptacije knjiga");
                                    startActivity(intent);
                                }
                            });
                            //Log.d(TAG, "DULJINA NIZA KNJIGA ILI FILM: " + BookToMoviePosts.length);
                        }
                    } else {
                        Toast.makeText(FacebookMenu.this, "Neuspješno dohvaćanje podataka.", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Exception caught: ", e);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FacebookPost[] getAllPhotos(String jsonData) throws JSONException {

        Log.d(TAG, "Pozvana metoda za dobivanje svih slika!!");

        JSONObject result = new JSONObject(jsonData);
        JSONArray photos = result.getJSONArray("data");

        FacebookPost[] fbPosts = new FacebookPost[photos.length()];

        for (int i = 0; i < photos.length(); i++) {
            JSONObject jsonPhoto = photos.getJSONObject(i);

            FacebookPost post = new FacebookPost();

            //Dohvaćanje JSON objekata i postavljanje atributa objekta tipa "NearbyPlace"
            post.setId(jsonPhoto.getString("id"));
            post.setTitle(jsonPhoto.getString("name"));
            post.setAuthor(jsonPhoto.getString("name"));
            post.setDescription(jsonPhoto.getString("name"));
            post.setImage(jsonPhoto.getString("source"));
            post.setDate(jsonPhoto.getString("created_time"));

            fbPosts[i] = post;

            //Log.d(TAG, "Photo ID: " + post.getId());

        }
        // Log.d(TAG, "Broj slika u albumu: " + fbPosts.length);
        return fbPosts;
    }

    //PROVJERA DOSTUPNOSTI MREŽE
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }


    //Ovisno o kliku na pojedini gumb proslijedi podatke i pozovi metodu za dohvaćanje slika iz albuma

    @OnClick(R.id.reviewButton)
    public void startReviewActivity () {
        if(isNetworkAvailable()) {
            startRequest(MiniRecenzije );
        } else {
            Toast.makeText(FacebookMenu.this, "Nema mrežne veze. Pokušajte ponovno.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.listsButton)
    public void startListsActivity () {
        if(isNetworkAvailable()) {
            startRequest(TopLists);
        } else {
            Toast.makeText(FacebookMenu.this, "Nema mrežne veze. Pokušajte ponovno.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.movieButton)
    public void startMovieActivity () {
        if(isNetworkAvailable()) {
            startRequest(BookToMovie);

        } else {
            Toast.makeText(FacebookMenu.this, "Nema mrežne veze. Pokušajte ponovno.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.bookIdButton)
    public void startBookIDActivity () {
        if(isNetworkAvailable()) {
            startRequest(BookIDs);

        } else {
            Toast.makeText(FacebookMenu.this, "Nema mrežne veze. Pokušajte ponovno.", Toast.LENGTH_SHORT).show();
        }
    }

}
