package com.example.monique.hrpaknjiga.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monique.hrpaknjiga.R;
import com.example.monique.hrpaknjiga.adapters.DiaryBookAdapter;
import com.example.monique.hrpaknjiga.database.DBHelper;
import com.example.monique.hrpaknjiga.models.Book;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditBookActivity extends AppCompatActivity {

    private DBHelper mDBHelper;
    private Book bookToEdit;

    @BindView(R.id.editTitle) EditText mEditTitle;
    @BindView(R.id.editAuthor) EditText mEditAuthor;
    @BindView(R.id.editDescription) EditText mEditDescription;
    @BindView(R.id.editPages) EditText mEditPages;
    @BindView(R.id.editProgress) EditText mEditProgress;
    @BindView(R.id.saveChangesBook) Button mSaveChangesButton;
    @BindView(R.id.readCheck) CheckBox mReadCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);
        ButterKnife.bind(this);

        //Dohvati id odabrane knjige proslijeđen iz prethodne aktivnosti.
        int selectedId;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                selectedId = 0;
            } else {
                selectedId = extras.getInt("TO_EDIT_BOOK_ID");
            }
        } else {
            selectedId = (int) savedInstanceState.getSerializable("TO_EDIT_BOOK_ID");
        }

        this.mDBHelper = DBHelper.getInstance(this);
        bookToEdit = mDBHelper.getBook(selectedId);

        //Postavi trenutne vrijednosti knjige u polja za izmjenu.
        mEditTitle.setText(bookToEdit.getTitle());
        mEditAuthor.setText(bookToEdit.getAuthor());
        mEditDescription.setText(bookToEdit.getDescription());
        mEditPages.setText(bookToEdit.getPages() + "");
        mEditProgress.setText(bookToEdit.getProgress() + "");
    }

    //Unos izmjena knjige, validacija forme i što ako je čitanje knjige dovršeno
    @OnClick(R.id.saveChangesBook)
    public void saveChanges (View view) {

        String error = "POGREŠKA UNOSA:\n";

        bookToEdit.setStatus(mReadCheck.isChecked());

        if(mEditTitle.getText().toString().length() == 0 ||
                mEditAuthor.getText().toString().length() == 0) {
            error+="Naslov i autor su obavezna polja.\n";
        }
        else {
            if (mEditTitle.getText().toString().length() < 100 &&
                    mEditAuthor.getText().toString().length() < 100) {

                bookToEdit.setTitle(mEditTitle.getText().toString());
                bookToEdit.setAuthor(mEditAuthor.getText().toString());
            }
            else {
                error += "Predug naslov knjige ili ime autora.\n";
            }
        }

        if (mEditDescription.getText().toString().length() < 500) {
            bookToEdit.setDescription(mEditDescription.getText().toString());
        } else {
            error+="Predug opis. MAX 500 znakova.\n";
        }

        int pages, progress;
        if (mEditProgress.getText().toString().length() == 0 && mEditPages.getText().toString().length() > 0) {
            progress = 0;
            if(isIntegerParseInt(mEditPages.getText().toString())) {
                pages = Integer.parseInt(mEditPages.getText().toString());
                bookToEdit.setPages(pages);
                bookToEdit.setProgress(progress);
            } else {
                error+="Broj stranica mora biti pozitivan cijeli broj.";
            }
        }
        else if (mEditPages.getText().toString().length() == 0 && mEditPages.getText().toString().length() == 0) {
            pages = 0;
            progress = 0;
            bookToEdit.setPages(pages);
            bookToEdit.setProgress(progress);
        } else {
            if (isIntegerParseInt(mEditPages.getText().toString()) && isIntegerParseInt(mEditPages.getText().toString())) {
                pages = Integer.parseInt(mEditPages.getText().toString());
                progress = Integer.parseInt(mEditProgress.getText().toString());

                if (pages <= 5000 && progress <= 5000) {
                    if (progress <= pages) {
                        bookToEdit.setPages(pages);
                        bookToEdit.setProgress(progress);
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


        //Ukoliko je čitanje knjige završeno
        if(mReadCheck.isChecked() && error == "POGREŠKA UNOSA:\n") {
            AlertDialog.Builder builder = new AlertDialog.Builder(EditBookActivity.this);
            builder.setTitle("Ocijeni knjigu!");
            builder.setMessage("Knjiga će biti spremljena u povijest čitanja. Prije toga, dajte joj svoju ocjenu.");

            //Inflate moj custom layout za dialog
            LayoutInflater inflater = EditBookActivity.this.getLayoutInflater();
            final View customView = inflater.inflate(R.layout.alert_rating, null);
            builder.setView(customView);

            builder.setNegativeButton("Prekid", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.setPositiveButton("Spremi", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Dohvati rating i postavi ga u knjigu
                    RatingBar mRatingStars = (RatingBar) customView.findViewById(R.id.ratingBar);
                    int rating = (int) mRatingStars.getRating();
                    bookToEdit.setRating(rating);
                    //Postavi datum završetka čitanja
                    bookToEdit.setDateFinished(System.currentTimeMillis() / 1000L);
                    //Progres izjednači s brojem stranica
                    bookToEdit.setProgress(bookToEdit.getPages());
                    //Spremi promjene u bazu
                    mDBHelper.updateBook(bookToEdit);

                    dialog.dismiss();
                    //Toast.makeText(EditBookActivity.this, rating + "", Toast.LENGTH_SHORT).show();
                    Toast.makeText(EditBookActivity.this, "Izmjene uspješno spremljene.", Toast.LENGTH_SHORT).show();

                    //Pokreni history aktivnost gdje je sada knjiga prebačena

                    Intent intent = new Intent(EditBookActivity.this, HistoryActivity.class);
                    startActivity(intent);

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        } else if(!mReadCheck.isChecked() && error == "POGREŠKA UNOSA:\n") {
            //Spremi promjene u bazu
            mDBHelper.updateBook(bookToEdit);
            Toast.makeText(EditBookActivity.this, "Izmjene uspješno spremljene.", Toast.LENGTH_SHORT).show();
            //Pokreni diary aktivnost
            Intent intent = new Intent(this, DiaryActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(EditBookActivity.this, error, Toast.LENGTH_SHORT).show();
        }
    }

    //provjeri je li uneseni broj integer
    public static boolean isIntegerParseInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {}
        return false;
    }

}
