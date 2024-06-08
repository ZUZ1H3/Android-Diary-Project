package com.example.androidprogramming;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class BookReviewActivity extends AppCompatActivity {

    private static final String TAG = "BookReviewActivity";

    private ImageView bookImage;
    private TextView bookTitle;
    private TextView bookAuthor;
    private EditText reviewEditText;
    private ImageButton backButton, saveButton;
    private BookReviewDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_review);

        bookImage = findViewById(R.id.book_image);
        bookTitle = findViewById(R.id.book_title);
        bookAuthor = findViewById(R.id.book_author);
        reviewEditText = findViewById(R.id.editText_review);
        backButton = findViewById(R.id.button_back);
        saveButton = findViewById(R.id.button_save);
        dbHelper = new BookReviewDBHelper(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        String imageUrl = intent.getStringExtra("imageUrl");

        bookTitle.setText(title);
        bookAuthor.setText(author);
        Picasso.get().load(imageUrl).into(bookImage);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String review = reviewEditText.getText().toString();
                if (!review.isEmpty()) {
                    Bitmap imageBitmap = ((BitmapDrawable) bookImage.getDrawable()).getBitmap();
                    saveReview(title, author, review, imageBitmap);
                    Toast.makeText(BookReviewActivity.this, "리뷰가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent reviewListIntent = new Intent(BookReviewActivity.this, BookReviewListActivity.class);
                    startActivity(reviewListIntent);
                    finish();
                } else {
                    Toast.makeText(BookReviewActivity.this, "리뷰를 작성하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // SharedPreferences에서 배경 이미지 읽어오기
        SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        int background = sharedPreferences.getInt("background", R.drawable.background1);

        // 배경 이미지 설정하기
        View rootView = findViewById(android.R.id.content);
        rootView.setBackgroundResource(background);
    }

    private byte[] getByteArrayFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private void saveReview(String title, String author, String review, Bitmap imageBitmap) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BookReviewDBHelper.COLUMN_TITLE, title);
        values.put(BookReviewDBHelper.COLUMN_AUTHOR, author);
        values.put(BookReviewDBHelper.COLUMN_REVIEW, review);

        byte[] imageByteArray = getByteArrayFromBitmap(imageBitmap);
        values.put(BookReviewDBHelper.COLUMN_IMAGE, imageByteArray);

        db.insert(BookReviewDBHelper.TABLE_BOOK, null, values);
        db.close();
    }

}
