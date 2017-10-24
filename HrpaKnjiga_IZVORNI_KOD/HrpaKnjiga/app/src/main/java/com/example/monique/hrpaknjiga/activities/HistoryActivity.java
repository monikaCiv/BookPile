package com.example.monique.hrpaknjiga.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monique.hrpaknjiga.R;
import com.example.monique.hrpaknjiga.adapters.DiaryBookAdapter;
import com.example.monique.hrpaknjiga.adapters.HistoryBookAdapter;
import com.example.monique.hrpaknjiga.database.DBHelper;
import com.example.monique.hrpaknjiga.models.Book;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {

    public static final String TAG = HistoryActivity.class.getSimpleName();

    ArrayList<Book> mFinishedBooks;
    private HistoryBookAdapter mHistoryBookAdapter;
    private DBHelper mDBHelper;

    @BindView(R.id.finishedBooksList) ListView mFinishedBooksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        this.mDBHelper = DBHelper.getInstance(this);

        this.mFinishedBooks = mDBHelper.getFinishedBooks();

        this.mHistoryBookAdapter = new HistoryBookAdapter(this.mFinishedBooks);
        this.mFinishedBooksList.setAdapter(this.mHistoryBookAdapter);

        Log.d(TAG, "Veličina liste: " + mFinishedBooks.size() + "");

    }

    //Ukloni knjigu s liste
    public void deleteFinishedBookHandler (final View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
        builder.setTitle("Upozorenje");
        builder.setMessage("Knjiga će biti trajno uklonjena i svi podaci o njoj izgubljeni.");

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
                mFinishedBooks = mDBHelper.getFinishedBooks();
                mHistoryBookAdapter = new HistoryBookAdapter(mFinishedBooks);
                mFinishedBooksList.setAdapter(mHistoryBookAdapter);

                Toast.makeText(HistoryActivity.this, "Uklonjeno.", Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
