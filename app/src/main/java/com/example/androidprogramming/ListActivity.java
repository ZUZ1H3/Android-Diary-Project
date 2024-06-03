package com.example.androidprogramming;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private ListAdapter adapter;
    private ArrayList<Diary> diaryList; // Diary 객체의 ArrayList를 사용합니다.
    private DiaryDBHelper diaryDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);
        diaryList = new ArrayList<>();
        diaryDBHelper = new DiaryDBHelper(this);

        // 데이터베이스에서 일기 데이터를 조회합니다.
        diaryList.addAll(diaryDBHelper.getAllDiaries()); // DBHelper에서 가져온 List를 ArrayList에 추가합니다.

        // 리스트뷰에 어댑터를 설정합니다.
        adapter = new ListAdapter(this, diaryList);
        listView.setAdapter(adapter);

        // SharedPreferences에서 배경 이미지 읽어오기
        SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        int background = sharedPreferences.getInt("background", R.drawable.background1);

        // 배경 이미지 설정하기
        View rootView = findViewById(android.R.id.content);
        rootView.setBackgroundResource(background);
    }
}
