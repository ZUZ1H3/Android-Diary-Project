package com.example.androidprogramming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private ListAdapter adapter;
    private ArrayList<Diary> diaryList; // Diary 객체의 ArrayList를 사용합니다.
    private DiaryDBHelper diaryDBHelper;
    private ImageButton button_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);
        diaryList = new ArrayList<>();
        diaryDBHelper = new DiaryDBHelper(this);
        button_back =findViewById(R.id.button_back);

        // 데이터베이스에서 일기 데이터를 조회합니다.
        diaryList.addAll(diaryDBHelper.getAllDiaries()); // DBHelper에서 가져온 List를 ArrayList에 추가합니다.

        // 리스트뷰에 어댑터를 설정합니다.
        adapter = new ListAdapter(this, diaryList);
        listView.setAdapter(adapter);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        // 리스트뷰 항목 클릭 리스너를 설정합니다.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 클릭된 일기를 가져옵니다.
                Diary clickedDiary = diaryList.get(position);

                // 상세보기 액티비티로 이동합니다.
                Intent intent = new Intent(ListActivity.this, DiaryDetailActivity.class);
                intent.putExtra("content", clickedDiary.getContent());
                intent.putExtra("date", clickedDiary.getDate());
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
