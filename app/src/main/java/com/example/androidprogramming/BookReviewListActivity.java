package com.example.androidprogramming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BookReviewListActivity extends AppCompatActivity {

    private ListView listViewReviews;
    private BookReviewListAdapter adapter;
    private ArrayList<Book> bookList;
    private BookReviewDBHelper dbHelper;

    private ImageButton addBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_review_list);

        listViewReviews = findViewById(R.id.listView_reviews);

        dbHelper = new BookReviewDBHelper(this);
        bookList = dbHelper.getAllReviews();

        adapter = new BookReviewListAdapter(this, bookList);
        listViewReviews.setAdapter(adapter);

        addBtn = findViewById(R.id.button_add);
        backBtn = findViewById(R.id.button_back);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BookSearchActivity.class);
                startActivity(intent);
            }
        });
        // SharedPreferences에서 배경 이미지 읽어오기
        SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        int background = sharedPreferences.getInt("background", R.drawable.background1);

        // 배경 이미지 설정하기
        View rootView = findViewById(android.R.id.content);
        rootView.setBackgroundResource(background);

    }
}
