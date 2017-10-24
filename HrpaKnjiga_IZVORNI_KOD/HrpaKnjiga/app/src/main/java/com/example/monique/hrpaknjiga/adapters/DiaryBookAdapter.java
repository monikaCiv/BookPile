package com.example.monique.hrpaknjiga.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.monique.hrpaknjiga.R;
import com.example.monique.hrpaknjiga.models.Book;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class DiaryBookAdapter extends BaseAdapter{

    ArrayList<Book> mBooks;

    public DiaryBookAdapter(ArrayList<Book> mBooks) {
        this.mBooks = mBooks;
    }

    @Override
    public int getCount() {
        return this.mBooks.size();
}

    @Override
    public Book getItem(int position) {
        return this.mBooks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BookViewHolder bookViewHolder;
        Context parentContext = parent.getContext();

        LayoutInflater mInflater = (LayoutInflater) parentContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if(null == convertView) {

            convertView = mInflater.inflate(R.layout.current_book_element, null);

            bookViewHolder = new BookViewHolder();

            bookViewHolder.textId = (TextView) convertView.findViewById(R.id.textId);
            bookViewHolder.textTitle = (TextView) convertView.findViewById(R.id.textTitle);
            bookViewHolder.textAuthor = (TextView) convertView.findViewById(R.id.textAuthor);
            bookViewHolder.dateStarted = (TextView) convertView.findViewById(R.id.textDateStarted);
            bookViewHolder.textDescription = (TextView) convertView.findViewById(R.id.textDescription);
            bookViewHolder.progressPercent = (TextView) convertView.findViewById(R.id.textProgressPercent);
            bookViewHolder.progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);

            bookViewHolder.editButton = (Button) convertView.findViewById(R.id.editButton);
            bookViewHolder.deleteButton = (Button) convertView.findViewById(R.id.deleteButton);

            convertView.setTag(bookViewHolder);

        }
        else {
            bookViewHolder = (BookViewHolder) convertView.getTag();
        }

        Book currentBook = getItem(position);

        if (currentBook != null) {
            bookViewHolder.textId.setText(currentBook.getId() + "");
            bookViewHolder.textTitle.setText(currentBook.getTitle());
            bookViewHolder.textAuthor.setText(currentBook.getAuthor());
            bookViewHolder.dateStarted.setText("Početak: " + getReadableDate(currentBook.getDateStarted()));
            bookViewHolder.textDescription.setText("Opis: " + currentBook.getDescription());
            bookViewHolder.progressPercent.setText(currentBook.getProgress() + "/" + currentBook.getPages());
            if(currentBook.getProgress() > 0) {
                int percent = (int) ((currentBook.getProgress() * 100.0f) / currentBook.getPages());
                bookViewHolder.progressBar.setProgress(percent);
            } else {
                bookViewHolder.progressBar.setProgress(0);
            }
        } else {
            Log.d("NULL-POINTER", "CurrentBook object is NULL!!");
            Log.d("NULL-POINTER", "SIZE: " + mBooks.size());
        }

        return convertView;
    }

    public void add(Book book) {
        this.mBooks.add(book);
        this.notifyDataSetChanged();
    }

    public void remove (int position) {
        this.mBooks.remove(position);
        this.notifyDataSetChanged();
    }

    static class BookViewHolder {
        private TextView textId;
        private TextView textTitle;
        private TextView textAuthor;
        private TextView dateStarted;
        private TextView textDescription;
        private TextView progressPercent;
        private ProgressBar progressBar;

        private Button editButton;
        private Button deleteButton;
    }

    public String getReadableDate (long unixTime) {
        Date date = new Date(unixTime*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy."); // the format of your date
        sdf.setTimeZone(TimeZone.getDefault()); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

}
