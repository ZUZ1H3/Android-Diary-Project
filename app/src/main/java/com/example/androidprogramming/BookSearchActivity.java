package com.example.androidprogramming;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidprogramming.BookSearchListAdapter;

import java.util.ArrayList;
import java.util.List;

public class BookSearchActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private ImageButton searchButton, backButton;
    private ListView listViewBooks;
    private BookSearchListAdapter adapter;
    private BookViewModel bookViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);

        editTextSearch = findViewById(R.id.editText_search);
        searchButton = findViewById(R.id.button_search);
        backButton = findViewById(R.id.button_back);
        listViewBooks = findViewById(R.id.listView_books);

        List<BookResponse.Item> bookList = new ArrayList<>();
        adapter = new BookSearchListAdapter(this, bookList);
        listViewBooks.setAdapter(adapter);

        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = editTextSearch.getText().toString();
                if (!TextUtils.isEmpty(query)) {
                    bookViewModel.searchBooks(query);
                } else {
                    Toast.makeText(BookSearchActivity.this, "검색어를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bookViewModel.getBookItems().observe(this, new Observer<List<BookResponse.Item>>() {
            @Override
            public void onChanged(List<BookResponse.Item> items) {
                adapter.clear();
                if (items != null) {
                    adapter.addAll(items);
                }
                adapter.notifyDataSetChanged();
                listViewBooks.setVisibility(View.VISIBLE);
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
