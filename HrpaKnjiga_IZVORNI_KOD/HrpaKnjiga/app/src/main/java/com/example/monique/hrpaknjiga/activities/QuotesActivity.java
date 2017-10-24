package com.example.monique.hrpaknjiga.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.monique.hrpaknjiga.R;
import com.example.monique.hrpaknjiga.models.ColorWheel;
import com.example.monique.hrpaknjiga.models.Quote;
import com.example.monique.hrpaknjiga.models.QuotesBook;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuotesActivity extends AppCompatActivity {

    public static final String TAG = QuotesActivity.class.getSimpleName();

    //Kreiraj novi objekt s citatima.
    private QuotesBook mQuotesBook = new QuotesBook();
    private ColorWheel mColorWheel = new ColorWheel();

    @BindView(R.id.getQuoteButton) Button mGetQuoteButton;
    @BindView(R.id.quoteText) TextView mQuoteText;
    @BindView(R.id.authorText) TextView mAuthorText;
    @BindView(R.id.bookText) TextView mBookText;
    @BindView(R.id.relativeLayout) RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        ButterKnife.bind(this);

        Quote quote = mQuotesBook.getQuote();
        int color = mColorWheel.getColor();

        mQuoteText.setText(quote.getQuote());
        mAuthorText.setText("- " + quote.getAuthor());
        mBookText.setText(quote.getBook());

        mRelativeLayout.setBackgroundColor(color);
        //mGetQuoteButton.setTextColor(color);

        Log.d(TAG, "Main code is running");
    }

    @OnClick(R.id.getQuoteButton)
    public void getRandomQuote(View view) {

        Log.d(TAG, "Button is clicked");

        //Preuzmi random citat i pohrani u varijablu tipa Quote.
        Quote quote = mQuotesBook.getQuote();
        //Preuzmi random boju i pohrani kao int.
        int color = mColorWheel.getColor();

        Log.d(TAG, quote.getAuthor());

        //Postavi tekst u odgovarajuće text viewove koristeći get metode klase Quote.
        mQuoteText.setText(quote.getQuote());
        mAuthorText.setText("- " + quote.getAuthor());
        mBookText.setText(quote.getBook());

        mRelativeLayout.setBackgroundColor(color);
        //mGetQuoteButton.setTextColor(color);
    }


}
