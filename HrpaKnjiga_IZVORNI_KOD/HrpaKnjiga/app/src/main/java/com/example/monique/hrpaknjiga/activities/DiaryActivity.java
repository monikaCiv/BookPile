package com.example.monique.hrpaknjiga.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monique.hrpaknjiga.R;
import com.example.monique.hrpaknjiga.adapters.DiaryBookAdapter;
import com.example.monique.hrpaknjiga.database.DBHelper;
import com.example.monique.hrpaknjiga.models.Book;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiaryActivity extends AppCompatActivity {

    private ArrayList<Book> mBooks;
    private DiaryBookAdapter mDiaryBookAdapter;
    private DBHelper mDBHelper;

    public static final String TAG = DiaryActivity.class.getSimpleName();

    @BindView(R.id.currentBooksList) ListView mCurrentBooksList;
    @BindView(R.id.addBook) ImageButton mAddBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "Main code is running.");

        setContentView(R.layout.activity_diary);
        ButterKnife.bind(this);

        //TESTNE KNJIGE
        //Book one = new Book(0, "TESTNA KNJIGA","Lala", "Hmmmmmmmmmmmmmmmmmm", false, 20, 10, 2, System.currentTimeMillis() / 1000L, 2000);
        //Book two = new Book(0, "NAKON IZMJENA","Lala", "Hmmmmm", false, 20, 10, 2, System.currentTimeMillis() / 1000L, 2000);


        this.mDBHelper = DBHelper.getInstance(this);

        //this.mDBHelper.deleteAllBooks();//testing code to delete all books from table
        //mDBHelper.insertBook(one); //testing code to add books
        //mDBHelper.insertBook(two); //testing code to add books
        //mDBHelper.deleteBook(37); //testing code to delete one book with specific id
        //Book sth = mDBHelper.getBook(57); //testing code to get book by id from DB
        //Book toUpdate = mDBHelper.getBook(64);
        //toUpdate.setTitle("NAKON IZMJENE"); //test update
        //mDBHelper.updateBook(toUpdate); //test update
       // Log.d(TAG, "Knjiga STH " + sth.getTitle()); //testing code to to get book by id from DB

        this.mBooks = mDBHelper.getCurrentlyReadingBooks();

        this.mDiaryBookAdapter = new DiaryBookAdapter(this.mBooks);
        this.mCurrentBooksList.setAdapter(this.mDiaryBookAdapter);

        Log.d(TAG, "Veličina liste: " + mBooks.size() + "");
    }

    @OnClick(R.id.addBook)
    public void startAddBookActivity() {
        Intent intent = new Intent(this, AddBookActivity.class);
        startActivity(intent);
    }

    //Brisanje pojedinog elementa liste iz BAZE
    public void deleteButtonHandler(final View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(DiaryActivity.this);
        builder.setTitle("Upozorenje");
        builder.setMessage("Knjiga će biti trajno uklonjena.");

        builder.setNegativeButton("Prekid", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Ukloni", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Kako dobiti ID odabrane knjige?
                //Odaberi RelativeLayout koji je odabran i iz njegovog child TextView is id-jem preuzmi id i prebaci u int.
                int selectedId;
                RelativeLayout vwParentRow = (RelativeLayout) view.getParent();
                TextView child = (TextView) vwParentRow.findViewById(R.id.textId);
                selectedId = Integer.parseInt(String.valueOf(child.getText()));

                mDBHelper.deleteBook(selectedId);

                //Osvježi izgled liste
                mBooks = mDBHelper.getCurrentlyReadingBooks();
                mDiaryBookAdapter = new DiaryBookAdapter(mBooks);
                mCurrentBooksList.setAdapter(mDiaryBookAdapter);

                Toast.makeText(DiaryActivity.this, "Uklonjeno.", Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    //Uređivanje pojedinog elementa liste - CHANGE PROGRESS i slično
    public void editButtonHandler(View view) {
        //Kako dobiti ID odabrane knjige?
        //Odaberi RelativeLayout koji je kliknut i iz njegovog child TextView is id-jem preuzmi id i prebaci u int.
        int selectedId;
        RelativeLayout vwParentRow = (RelativeLayout)view.getParent();
        TextView child = (TextView) vwParentRow.findViewById(R.id.textId);
        selectedId = Integer.parseInt(String.valueOf(child.getText()));

        //Otvori novi activity koji će omogućiti uređivanje i dodavanje još nekih svojstava.
        Intent intent = new Intent(this, EditBookActivity.class);
        intent.putExtra("TO_EDIT_BOOK_ID", selectedId);
        startActivity(intent);

    }

}