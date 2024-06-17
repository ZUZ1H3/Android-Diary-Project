package com.example.androidprogramming;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

public class BookReviewDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BookDB.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_BOOK = "BOOK";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "book_title";
    public static final String COLUMN_AUTHOR = "book_author";
    public static final String COLUMN_REVIEW = "review";
    public static final String COLUMN_IMAGE = "image";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_BOOK + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_AUTHOR + " TEXT, " +
                    COLUMN_REVIEW + " TEXT, " +
                    COLUMN_IMAGE + " BLOB" +
                    ");";

    public BookReviewDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }


    public ArrayList<Book> getAllReviews() {
        ArrayList<Book> bookList = new ArrayList<>();
        String selectQuery = "SELECT * FROM book ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String author = cursor.getString(2);
                String review = cursor.getString(3);
                byte[] imageByteArray = cursor.getBlob(4);

                Book book = new Book(id, title, author, review, imageByteArray);
                bookList.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bookList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        onCreate(db);
    }
}
