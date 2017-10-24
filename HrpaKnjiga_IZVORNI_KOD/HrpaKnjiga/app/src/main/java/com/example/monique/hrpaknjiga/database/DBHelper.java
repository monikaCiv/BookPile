package com.example.monique.hrpaknjiga.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.monique.hrpaknjiga.models.Book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "books.db";

    private static final String BOOKS_TABLE = "BOOKS";
    private static final String COLUMN_TITLE = "TITLE";
    private static final String COLUMN_AUTHOR = "AUTHOR";
    private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    private static final String COLUMN_STATUS = "STATUS";
    private static final String COLUMN_PAGES = "PAGES";
    private static final String COLUMN_PROGRESS = "PROGRESS";
    private static final String COLUMN_RATING = "RATING";

    private static final String COLUMN_CREATION_DATE = "DATE_CREATION";
    private static final String COLUMN_DATE_STARTED = "DATE_START";
    private static final String COLUMN_DATE_FINISHED = "DATE_END";

    private static String CREATE_TABLE_BOOKS =
            "CREATE TABLE " + BOOKS_TABLE + " (" +
                    BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_TITLE + " TEXT," +
                    COLUMN_AUTHOR + " TEXT," +
                    COLUMN_DESCRIPTION + " TEXT," +
                    COLUMN_STATUS + " INTEGER," +
                    COLUMN_PAGES + " INTEGER," +
                    COLUMN_PROGRESS + " INTEGER," +
                    COLUMN_RATING + " INTEGER," +
                    COLUMN_DATE_STARTED + " INTEGER," +
                    COLUMN_DATE_FINISHED + " INTEGER)";

    private static final String ALTER_CREATION_DATE = "ALTER TABLE " + BOOKS_TABLE +
            " ADD_COLUMN " + COLUMN_CREATION_DATE + " INTEGER";

    //Smije postojati samo jedna instanca klase DBHelper - treba nam samo jedna baza
    private static DBHelper mInstance = null;

    //Zato je potrebno konstruktor učiniti privatnim.
    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //I napraviti metodu za pristup instanci.
    public static synchronized DBHelper getInstance (Context context) {
        if (mInstance == null) {
            context = context.getApplicationContext();
            mInstance = new DBHelper(context);
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BOOKS);
    }

    //Pogledaj što će se dogoditi na upgrade. Kako izmijeniti, a da se ne obrišu stari podaci.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                db.execSQL(ALTER_CREATION_DATE);
        }
    }
    //Dohvati sve knjige.
    public ArrayList<Book> getAllBooks() {

        String SELECT_ALL_BOOKS = "SELECT " + BaseColumns._ID + "," +
                COLUMN_TITLE + "," +
                COLUMN_AUTHOR + "," +
                COLUMN_DESCRIPTION + "," +
                COLUMN_STATUS + "," +
                COLUMN_PAGES + "," +
                COLUMN_PROGRESS + "," +
                COLUMN_RATING  + "," +
                COLUMN_DATE_STARTED + "," +
                COLUMN_DATE_FINISHED + " FROM " + BOOKS_TABLE;

        ArrayList<Book> books = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor booksCursor = db.rawQuery(SELECT_ALL_BOOKS, null);

        if(booksCursor.moveToFirst()) {
            do {
                    Book book = new Book(
                            booksCursor.getInt(0),               //id
                            booksCursor.getString(1),           //title
                            booksCursor.getString(2),           //author
                            booksCursor.getString(3),           //description
                            toBoolean(booksCursor.getInt(4)),   //status
                            booksCursor.getInt(5),              //pages
                            booksCursor.getInt(6),              //progress
                            booksCursor.getInt(7),              //rating
                            booksCursor.getLong(8),              //start
                            booksCursor.getLong(9)              //end
                    );
                books.add(book);
            } while (booksCursor.moveToNext());
        }
        booksCursor.close();
        db.close();
        return books;
    }

    //Dohvati sve nepročitane knjige
    public ArrayList<Book> getCurrentlyReadingBooks() {

        String SELECT_CURRENT_BOOKS = "SELECT " + BaseColumns._ID + "," +
                COLUMN_TITLE + "," +
                COLUMN_AUTHOR + "," +
                COLUMN_DESCRIPTION + "," +
                COLUMN_STATUS + "," +
                COLUMN_PAGES + "," +
                COLUMN_PROGRESS + "," +
                COLUMN_RATING  + "," +
                COLUMN_DATE_STARTED + "," +
                COLUMN_DATE_FINISHED + " FROM " + BOOKS_TABLE + " WHERE " +  COLUMN_STATUS + "=0";

        ArrayList<Book> books = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor booksCursor = db.rawQuery(SELECT_CURRENT_BOOKS, null);

        if(booksCursor.moveToFirst()) {
            do {
                Book book = new Book(
                        booksCursor.getInt(0),               //id
                        booksCursor.getString(1),           //title
                        booksCursor.getString(2),           //author
                        booksCursor.getString(3),           //description
                        toBoolean(booksCursor.getInt(4)),   //status
                        booksCursor.getInt(5),              //pages
                        booksCursor.getInt(6),              //progress
                        booksCursor.getInt(7),              //rating
                        booksCursor.getLong(8),              //start
                        booksCursor.getLong(9)              //end
                );
                books.add(book);
            } while (booksCursor.moveToNext());
        }
        booksCursor.close();
        db.close();
        return books;
    }

    //Dohvati sve pročitane knjige
    public ArrayList<Book> getFinishedBooks() {

        String SELECT_FINISHED_BOOKS = "SELECT " + BaseColumns._ID + "," +
                COLUMN_TITLE + "," +
                COLUMN_AUTHOR + "," +
                COLUMN_DESCRIPTION + "," +
                COLUMN_STATUS + "," +
                COLUMN_PAGES + "," +
                COLUMN_PROGRESS + "," +
                COLUMN_RATING  + "," +
                COLUMN_DATE_STARTED + "," +
                COLUMN_DATE_FINISHED + " FROM " + BOOKS_TABLE + " WHERE " +
                COLUMN_STATUS + "=1 ORDER BY " + COLUMN_DATE_FINISHED + " DESC";

        ArrayList<Book> books = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor booksCursor = db.rawQuery(SELECT_FINISHED_BOOKS, null);

        if(booksCursor.moveToFirst()) {
            do {
                Book book = new Book(
                        booksCursor.getInt(0),               //id
                        booksCursor.getString(1),           //title
                        booksCursor.getString(2),           //author
                        booksCursor.getString(3),           //description
                        toBoolean(booksCursor.getInt(4)),   //status
                        booksCursor.getInt(5),              //pages
                        booksCursor.getInt(6),              //progress
                        booksCursor.getInt(7),              //rating
                        booksCursor.getLong(8),              //start
                        booksCursor.getLong(9)              //end
                );
                books.add(book);
            } while (booksCursor.moveToNext());
        }
        booksCursor.close();
        db.close();
        return books;
    }

    //Umetni određenu knjigu.
    public void insertBook(Book book) {
        //Using the insert method of the database object:
        ContentValues bookValues = new ContentValues();
        bookValues.put(COLUMN_TITLE, book.getTitle());
        bookValues.put(COLUMN_AUTHOR, book.getAuthor());
        bookValues.put(COLUMN_DESCRIPTION, book.getDescription());
        bookValues.put(COLUMN_STATUS, toInt(book.getStatus()));
        bookValues.put(COLUMN_PAGES, book.getPages());
        bookValues.put(COLUMN_PROGRESS, book.getProgress());
        bookValues.put(COLUMN_RATING, book.getRating());
        bookValues.put(COLUMN_DATE_STARTED, book.getDateStarted());
        bookValues.put(COLUMN_DATE_FINISHED, book.getDateFinished());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(BOOKS_TABLE, null, bookValues);
        db.close();
    }

    //Obriši određenu knjigu.
    public void deleteBook (int bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BOOKS_TABLE, String.format("%s=%s", BaseColumns._ID, String.valueOf(bookId)), null);
        db.close();
    }

    //Dohvati određenu knjigu.
    public Book getBook(int bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor bookCursor = db.rawQuery("SELECT * FROM " + BOOKS_TABLE + " WHERE " + BaseColumns._ID + "='" + String.valueOf(bookId) + "'", null);
        Book selectedBook = new Book();
        if(bookCursor != null) {

            if (bookCursor.moveToFirst()) {
                        selectedBook.setId(bookCursor.getInt(0));               //id
                        selectedBook.setTitle(bookCursor.getString(1));         //title
                        selectedBook.setAuthor(bookCursor.getString(2));        //author
                        selectedBook.setDescription(bookCursor.getString(3));   //description
                        selectedBook.setStatus(toBoolean(bookCursor.getInt(4)));//status
                        selectedBook.setPages(bookCursor.getInt(5));            //pages
                        selectedBook.setProgress(bookCursor.getInt(6));         //progress
                        selectedBook.setRating(bookCursor.getInt(7));           //rating
                        selectedBook.setDateStarted(bookCursor.getLong(8));     //start
                        selectedBook.setDateFinished(bookCursor.getLong(9));    //finished
            }
            bookCursor.close();
        }
        db.close();
        return selectedBook;
    }

    //Izmjeni određenu knjigu.
    public void updateBook(Book book) {
        int bookId = book.getId();
        ContentValues updatedBookValues = new ContentValues();

        updatedBookValues.put(COLUMN_TITLE, book.getTitle());
        updatedBookValues.put(COLUMN_AUTHOR, book.getAuthor());
        updatedBookValues.put(COLUMN_DESCRIPTION, book.getDescription());
        updatedBookValues.put(COLUMN_STATUS, book.getStatus());
        updatedBookValues.put(COLUMN_PAGES, book.getPages());
        updatedBookValues.put(COLUMN_PROGRESS, book.getProgress());
        updatedBookValues.put(COLUMN_RATING, book.getRating());
        updatedBookValues.put(COLUMN_DATE_STARTED, book.getDateStarted());
        updatedBookValues.put(COLUMN_DATE_FINISHED, book.getDateFinished());

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(BOOKS_TABLE, updatedBookValues, "_id=" + bookId, null);
        db.close();
    }

    //Obriši sve knjige.
    public void deleteAllBooks() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ BOOKS_TABLE);
        db.close();
    }

    //Za pretvorbu statusa
    private boolean toBoolean (int statusInt) {
        if(statusInt == 0) return false;
        else return true;
    }

    //Za pretvorbu statusa
    private int toInt (boolean statusBool) {
        if(statusBool == true) return 1;
        else return 0;
    }





}
