package com.example.monique.hrpaknjiga.models;

public class Quote {
    private String mBook;
    private String mAuthor;
    private String mQuote;

    public Quote(String book, String author, String quote) {
        this.mBook = book;
        this.mAuthor = author;
        this.mQuote = quote;
    }

    public String getBook() {

        return mBook;
    }

    public void setBook(String book) {

        this.mBook = book;
    }

    public String getAuthor() {

        return mAuthor;
    }

    public void setAuthor(String author) {

        this.mAuthor = author;
    }

    public String getQuote() {
        return mQuote;
    }

    public void setQuote(String quote) {

        this.mQuote = quote;
    }

}
