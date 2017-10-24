package com.example.monique.hrpaknjiga.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Book implements Serializable {

    private int mId;
    private String mTitle;
    private String mAuthor;
    private String mDescription;
    private boolean mStatus;
    private int mPages;
    private int mProgress;
    private int mRating;
    private long mDateStarted;
    private long mDateFinished;

    public Book () {
        this.mId = 0;
        this.mTitle = "";
        this.mAuthor = "";
        this.mDescription = "";
        this.mRating = 0;
        this.mStatus = false;
        this.mPages = 0;
        this.mProgress = 0;
        this.mDateStarted = 0;
        this.mDateFinished = 0;
    }

    public Book (int id, String title,
                 String author, String description,
                 boolean status, int pages, int progress, int rating,
                 long started, long finished) {

        this.mId = id;
        this.mTitle = title;
        this.mAuthor = author;
        this.mDescription = description;
        this.mRating = rating;
        this.mStatus = status;
        this.mPages = pages;
        this.mProgress = progress;
        this.mDateStarted = started;
        this.mDateFinished = finished;
    }


    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        this.mAuthor = author;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public int getRating() {
        return mRating;
    }

    public void setRating(int rating) {
        this.mRating = rating;
    }

    public boolean getStatus() {
        return mStatus;
    }

    public void setStatus(boolean status) {
        this.mStatus = status;
    }

    public int getPages() {
        return mPages;
    }

    public void setPages(int pages) {
        this.mPages = pages;
    }

    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int progress) {
        this.mProgress = progress;
    }

    public long getDateStarted() {
        return mDateStarted;
    }

    public void setDateStarted(long mDateStarted) {
        this.mDateStarted = mDateStarted;
    }

    public long getDateFinished() {
        return mDateFinished;
    }

    public void setDateFinished(long mDateFinished) {
        this.mDateFinished = mDateFinished;
    }

    //Može poslužiti u slučaju proširenja liste za prikaz kategorija
    public String getBookCategory() {
        String year, month, monthHR;

        Date date = new Date(this.mDateFinished * 1000L);
        SimpleDateFormat yyyy = new SimpleDateFormat("yyyy"); // the format of your date
        yyyy.setTimeZone(TimeZone.getDefault()); // give a timezone reference for formating (see comment at the bottom
        year = yyyy.format(date);

        SimpleDateFormat MM = new SimpleDateFormat("MM");
        MM.setTimeZone(TimeZone.getDefault());
        month = MM.format(date);

        switch (month) {
            case "01":
                monthHR = "Siječanj";
                break;
            case "02":
                monthHR = "Veljača";
                break;
            case "03":
                monthHR = "Ožujak";
                break;
            case "04":
                monthHR = "Travanj";
                break;
            case "05":
                monthHR = "Svibanj";
                break;
            case "06":
                monthHR = "Lipanj";
                break;
            case "07":
                monthHR = "Srpanj";
                break;
            case "08":
                monthHR = "Kolovoz";
                break;
            case "09":
                monthHR = "Rujan";
                break;
            case "10":
                monthHR = "Listopad";
                break;
            case "11":
                monthHR = "Studeni";
                break;
            case "12":
                monthHR = "Prosinac";
                break;
            default:
                monthHR = "--";
                break;
        }
        return monthHR + " " + year + ".";
    }

}
