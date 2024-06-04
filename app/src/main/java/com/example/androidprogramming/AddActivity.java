package com.example.androidprogramming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    private RadioGroup rGroup_weather, rGroup_mood;
    private ImageButton button_back, button_write;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        rGroup_weather = findViewById(R.id.rGroup_weather);
        rGroup_mood = findViewById(R.id.rGroup_mood);
        button_write = findViewById(R.id.button_write);
        button_back =findViewById(R.id.button_back);

        // 날짜 데이터 받아오기
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            year = bundle.getInt("year");
            month = bundle.getInt("month");
            day = bundle.getInt("day");

            // 날짜를 텍스트뷰에 표시
            TextView dateText = findViewById(R.id.date);
            dateText.setText(year + "년 " + month + "월 " + day + "일");
        }

        // SharedPreferences에서 배경 이미지 읽어오기
        SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        int background = sharedPreferences.getInt("background", R.drawable.background1);

        // 배경 이미지 설정하기
        View rootView = findViewById(android.R.id.content);
        rootView.setBackgroundResource(background);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        button_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedWeatherId = rGroup_weather.getCheckedRadioButtonId();
                int selectedMoodId = rGroup_mood.getCheckedRadioButtonId();

                if (selectedWeatherId == -1 || selectedMoodId == -1) {
                    Toast.makeText(AddActivity.this, "모든 항목을 선택해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton selectedWeatherButton = findViewById(selectedWeatherId);
                RadioButton selectedMoodButton = findViewById(selectedMoodId);

                String selectedWeather = selectedWeatherButton.getTag().toString();
                String selectedMood = selectedMoodButton.getTag().toString();

                // Intent를 사용하여 DiaryActivity를 시작하고 데이터 전달
                Intent intent = new Intent(AddActivity.this, DiaryActivity.class);
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                intent.putExtra("weather", selectedWeather);
                intent.putExtra("mood", selectedMood);

                // DiaryActivity 시작
                startActivity(intent);
                finish();
            }
        });


    }
}