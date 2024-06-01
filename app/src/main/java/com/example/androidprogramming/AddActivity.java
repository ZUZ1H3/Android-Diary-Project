package com.example.androidprogramming;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    private RadioGroup rGroup_weather, rGroup_mood;
    private Button button_write;
    private int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        rGroup_weather = findViewById(R.id.rGroup_weather);
        rGroup_mood = findViewById(R.id.rGroup_mood);
        button_write = findViewById(R.id.button_write);

        // 날짜 데이터 받아오기
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            year = bundle.getInt("year");
            month = bundle.getInt("month");
            day = bundle.getInt("day");

            // 날짜를 텍스트뷰에 표시
            TextView dateText = findViewById(R.id.date);
            dateText.setText(month + "월 " + day + "일");
        }


        button_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedWeatherId = rGroup_weather.getCheckedRadioButtonId();
                int selectedMoodId = rGroup_mood.getCheckedRadioButtonId();

                String selectedWeather = ((RadioButton) findViewById(selectedWeatherId)).getText().toString();
                String selectedMood = ((RadioButton) findViewById(selectedMoodId)).getText().toString();

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