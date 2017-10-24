package com.example.monique.hrpaknjiga.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.monique.hrpaknjiga.R;
import com.example.monique.hrpaknjiga.models.Book;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class HistoryBookAdapter extends BaseAdapter {

    ArrayList<Book> mFinishedBooks;

    public HistoryBookAdapter(ArrayList<Book> mFinishedBooks) { this.mFinishedBooks = mFinishedBooks; }

    @Override
    public int getCount() {
        return this.mFinishedBooks.size();
    }

    @Override
    public Book getItem(int position) {
        return this.mFinishedBooks.get(position);
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

            convertView = mInflater.inflate(R.layout.finished_book_element, null);

            bookViewHolder = new BookViewHolder();

            bookViewHolder.textId = (TextView) convertView.findViewById(R.id.textId);
            bookViewHolder.textTitle = (TextView) convertView.findViewById(R.id.textTitle);
            bookViewHolder.textAuthor = (TextView) convertView.findViewById(R.id.textAuthor);
            bookViewHolder.dateFinished = (TextView) convertView.findViewById(R.id.textDateFinished);
            bookViewHolder.textDescription = (TextView) convertView.findViewById(R.id.textDescription);
            bookViewHolder.ratingStars = (ImageView) convertView.findViewById(R.id.ratingStars);
            bookViewHolder.closeButton = (ImageButton) convertView.findViewById(R.id.closeButton);
            bookViewHolder.textSeparator = (TextView) convertView.findViewById(R.id.listSeparator);

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
            bookViewHolder.dateFinished.setText("Pročitano: " + getReadableDate(currentBook.getDateFinished()));
            bookViewHolder.textDescription.setText("Opis: " + currentBook.getDescription());
            int rating = currentBook.getRating();
            switch (rating) {
                case 1:
                    bookViewHolder.ratingStars.setImageResource(R.drawable._1star);
                    break;
                case 2:
                    bookViewHolder.ratingStars.setImageResource(R.drawable._2stars);
                    break;
                case 3:
                    bookViewHolder.ratingStars.setImageResource(R.drawable._3stars);
                    break;
                case 4:
                    bookViewHolder.ratingStars.setImageResource(R.drawable._4stars);
                    break;
                case 5:
                    bookViewHolder.ratingStars.setImageResource(R.drawable._5stars);
                    break;
                default:
                    bookViewHolder.ratingStars.setImageResource(R.drawable._0stars);
                    break;
            }
            //ViewHolder for SEPARATOR VIDI MOŽE LI SE IMPLEMENTIRATI NA JEDNOSTAVNIJI NAČIN
            bookViewHolder.textSeparator.setText(currentBook.getBookCategory());
            bookViewHolder.textSeparator.setVisibility(View.GONE);
        }

        return convertView;
    }

    public void add(Book book) {
        this.mFinishedBooks.add(book);
        this.notifyDataSetChanged();
    }

    public void remove (int position) {
        this.mFinishedBooks.remove(position);
        this.notifyDataSetChanged();
    }

    static class BookViewHolder {
        private TextView textId;
        private TextView textTitle;
        private TextView textAuthor;
        private TextView dateFinished;
        private TextView textDescription;
        private ImageView ratingStars;
        private TextView textSeparator;
        private ImageButton closeButton;
    }

    public String getReadableDate (long unixTime) {
        Date date = new Date(unixTime*1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); // the format of your date
        sdf.setTimeZone(TimeZone.getDefault()); // give a timezone reference for formating (see comment at the bottom
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
}
