package com.example.androidprogramming;

import android.content.Intent;
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

    private ImageButton addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_review_list);

        listViewReviews = findViewById(R.id.listView_reviews);

        dbHelper = new BookReviewDBHelper(this);
        bookList = dbHelper.getAllReviews(); // 데이터베이스에서 서평 목록을 가져옵니다.

        // 서평 목록을 어댑터에 설정합니다.
        adapter = new BookReviewListAdapter(this, bookList);
        listViewReviews.setAdapter(adapter);

        addBtn = findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BookSearchActivity.class);
                startActivity(intent);
            }
        });
    }


    // 모든 서평을 데이터베이스에서 가져오는 메서드

}
