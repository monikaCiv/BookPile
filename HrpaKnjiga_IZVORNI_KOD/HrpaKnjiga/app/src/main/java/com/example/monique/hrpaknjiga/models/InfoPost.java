package com.example.monique.hrpaknjiga.models;

public class InfoPost {
    private String mTitle;
    private String mText;
    private String mLink;
    private int mImage;

    public InfoPost(String title, String text, String link, int image) {
        this.mTitle = title;
        this.mText = text;
        this.mLink = link;
        this.mImage = image;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        this.mLink = link;
    }

    public int getImage() {
        return mImage;
    }

    public void setmImage(int image) {
        this.mImage = image;
    }
}


