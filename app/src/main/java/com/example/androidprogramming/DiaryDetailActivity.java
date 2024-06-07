package com.example.androidprogramming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DiaryDetailActivity extends AppCompatActivity {
    private ImageButton button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_detail);

        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Intent로부터 일기 데이터를 가져옵니다.
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        String date = intent.getStringExtra("date");
        int weatherResource = intent.getIntExtra("weather", R.drawable.question); // Default weather image
        int moodResource = intent.getIntExtra("mood", R.drawable.question); // Default mood image

        // 일기 데이터를 UI에 설정합니다.
        TextView contentTextView = findViewById(R.id.textView_diary);
        TextView dateTextView = findViewById(R.id.textView_date);
        ImageView weatherImageView = findViewById(R.id.imageView_weather);
        ImageView moodImageView = findViewById(R.id.imageView_mood);

        contentTextView.setText(content);
        dateTextView.setText(date);
        weatherImageView.setImageResource(weatherResource);
        moodImageView.setImageResource(moodResource);

        // SharedPreferences에서 배경 이미지 읽어오기
        SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        int background = sharedPreferences.getInt("background", R.drawable.background1);

        // 배경 이미지 설정하기
        View rootView = findViewById(android.R.id.content);
        rootView.setBackgroundResource(background);
    }
}
