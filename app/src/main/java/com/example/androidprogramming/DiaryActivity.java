package com.example.androidprogramming;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DiaryActivity extends AppCompatActivity {

    private TextView textView_date;
    private ImageView textView_weather, textView_mood;
    private EditText editText_diary;
    private ImageButton button_save;

    private int year, month, day;
    private String weather, mood; // 변수를 멤버 변수로 이동

    private DiaryDBHelper diaryDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        textView_date = findViewById(R.id.textView_date);
        textView_weather = findViewById(R.id.textView_weather);
        textView_mood = findViewById(R.id.textView_mood);
        editText_diary = findViewById(R.id.editText_diary);
        button_save = findViewById(R.id.button_save);

        diaryDBHelper = new DiaryDBHelper(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            year = bundle.getInt("year");
            month = bundle.getInt("month");
            day = bundle.getInt("day");
            weather = bundle.getString("weather");
            mood = bundle.getString("mood");

            textView_date.setText(String.format(Locale.getDefault(), "%d년 %02d월 %02d일", year, month, day));
            switch (weather) {
                case "sunny":
                    textView_weather.setImageResource(R.drawable.weather_sunny);
                    break;
                case "rainy":
                    textView_weather.setImageResource(R.drawable.weather_rainy);
                    break;
                case "cloudy":
                    textView_weather.setImageResource(R.drawable.weather_cloudy);
                    break;
                case "windy":
                    textView_weather.setImageResource(R.drawable.weather_windy);
                    break;
                case "lightning":
                    textView_weather.setImageResource(R.drawable.weather_lightning);
                    break;
                case "snowy":
                    textView_weather.setImageResource(R.drawable.weather_snowy);
                    break;
                case "rainbow":
                    textView_weather.setImageResource(R.drawable.weather_rainbow);
                    break;
            }
            switch (mood) {
                case "happy":
                    textView_mood.setImageResource(R.drawable.mood_happy);
                    break;
                case "sad":
                    textView_mood.setImageResource(R.drawable.mood_sad);
                    break;
                case "angry":
                    textView_mood.setImageResource(R.drawable.mood_angry);
                    break;
                case "lucky":
                    textView_mood.setImageResource(R.drawable.mood_lucky);
                    break;
                case "soso":
                    textView_mood.setImageResource(R.drawable.mood_soso);
                    break;
                case "flutter":
                    textView_mood.setImageResource(R.drawable.mood_flutter);
                    break;
                case "tired":
                    textView_mood.setImageResource(R.drawable.mood_tired);
                    break;
                case "comfortable":
                    textView_mood.setImageResource(R.drawable.mood_comfortable);
                    break;
                case "exciting":
                    textView_mood.setImageResource(R.drawable.mood_exciting);
                    break;
            }

            // SharedPreferences에서 배경 이미지 읽어오기
            SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
            int background = sharedPreferences.getInt("background", R.drawable.background1);

            // 배경 이미지 설정하기
            View rootView = findViewById(android.R.id.content);
            rootView.setBackgroundResource(background);
        }

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = editText_diary.getText().toString();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month - 1, day);
                String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime());

                saveDiary(date, weather, mood, content);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.commit();
                finish();
            }
        });
    }

    public void saveDiary(String date, String weather, String mood, String content) {
        SQLiteDatabase db = diaryDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("weather", weather);
        values.put("mood", mood);
        values.put("content", content);

        db.insert("diary", null, values);
        db.close();
    }
}
