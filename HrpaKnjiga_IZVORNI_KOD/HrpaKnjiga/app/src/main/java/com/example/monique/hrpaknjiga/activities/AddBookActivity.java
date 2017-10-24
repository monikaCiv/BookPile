package com.example.monique.hrpaknjiga.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.monique.hrpaknjiga.R;
import com.example.monique.hrpaknjiga.adapters.DiaryBookAdapter;
import com.example.monique.hrpaknjiga.database.DBHelper;
import com.example.monique.hrpaknjiga.models.Book;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddBookActivity extends AppCompatActivity {

    public static final String TAG = AddBookActivity.class.getSimpleName();

    private DBHelper mDBHelper;

    @BindView(R.id.addBook) Button mAddBook;
    @BindView(R.id.editTitle) EditText mEditTitle;
    @BindView(R.id.editAuthor) EditText mEditAuthor;
    @BindView(R.id.editDescription) EditText mEditDescription;
    @BindView(R.id.editPages) EditText mEditPages;
    @BindView(R.id.editProgress) EditText mEditProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        ButterKnife.bind(this);

        this.mDBHelper = DBHelper.getInstance(this);
    }

    //Dodaj novu knjigu: forma + validacija
    @OnClick(R.id.addBook)
    public void saveNewBook(View view) {
        String error = "POGREŠKA UNOSA:\n";

        Book bookToAdd = new Book();

        if(mEditTitle.getText().toString().length() == 0 ||
                mEditAuthor.getText().toString().length() == 0) {
                error+="Naslov i autor su obavezna polja.\n";
        }
        else {
            if (mEditTitle.getText().toString().length() < 100 &&
                    mEditAuthor.getText().toString().length() < 100) {

                bookToAdd.setTitle(mEditTitle.getText().toString());
                bookToAdd.setAuthor(mEditAuthor.getText().toString());
            }
            else {
                error += "Predug naslov knjige ili ime autora.\n";
            }
        }

        if (mEditDescription.getText().toString().length() < 500) {
            bookToAdd.setDescription(mEditDescription.getText().toString());
        } else {
            error+="Predug opis. MAX 500 znakova.\n";
        }

        int pages, progress;
        if (mEditProgress.getText().toString().length() == 0 && mEditPages.getText().toString().length() > 0) {
            progress = 0;
            if(isIntegerParseInt(mEditPages.getText().toString())) {
                pages = Integer.parseInt(mEditPages.getText().toString());
                bookToAdd.setPages(pages);
                bookToAdd.setProgress(progress);
            } else {
                error+="Broj stranica mora biti pozitivan cijeli broj.";
            }
        }
        else if (mEditPages.getText().toString().length() == 0 && mEditPages.getText().toString().length() == 0) {
            pages = 0;
            progress = 0;
            bookToAdd.setPages(pages);
            bookToAdd.setProgress(progress);
        } else {
            if (isIntegerParseInt(mEditPages.getText().toString()) && isIntegerParseInt(mEditPages.getText().toString())) {
                pages = Integer.parseInt(mEditPages.getText().toString());
                progress = Integer.parseInt(mEditProgress.getText().toString());

                if (pages <= 5000 && progress <= 5000) {
                    if (progress <= pages) {
                        bookToAdd.setPages(pages);
                        bookToAdd.setProgress(progress);
                    } else {
                        error += "Napredak ne može biti veći od broja stranica.\n";
                    }
                } else {
                    error += "Broj stranica mora biti manji od 5000.\n";
                }
            } else {
                error += "Broj stranica i napredak moraju biti pozitivni cijeli brojevi.\n";
            }
        }

        bookToAdd.setDateStarted(System.currentTimeMillis() / 1000L);
                    Log.d(TAG, "GETTING VALUES FROM VIEWS - PAGES: " + mEditPages.getText().toString());


        if (error.equals("POGREŠKA UNOSA:\n")) {
            mDBHelper.insertBook(bookToAdd);
            Toast.makeText(AddBookActivity.this, "Knjiga uspješno dodana.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DiaryActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(AddBookActivity.this, error, Toast.LENGTH_SHORT).show();
        }

    }

    //Provjeri je li uneseni broj integer
    public static boolean isIntegerParseInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {}
        return false;
    }
}
