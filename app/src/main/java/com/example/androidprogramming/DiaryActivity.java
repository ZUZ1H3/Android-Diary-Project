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
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DiaryActivity extends AppCompatActivity {

    private TextView textViewDate;
    private ImageView imageViewWeather, imageViewMood;
    private EditText editTextDiary;
    private ImageButton buttonBack, buttonSave;

    private int year, month, day;
    private String weather, mood;

    private DiaryDBHelper diaryDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        initializeViews();
        diaryDBHelper = new DiaryDBHelper(this);

        if (getIntent().getExtras() != null) {
            extractDataFromBundle(getIntent().getExtras());
            updateUI();
        }

        setButtonListeners();
    }

    private void initializeViews() {
        textViewDate = findViewById(R.id.textView_date);
        imageViewWeather = findViewById(R.id.ImageView_weather);
        imageViewMood = findViewById(R.id.ImageView_mood);
        editTextDiary = findViewById(R.id.editText_diary);
        buttonSave = findViewById(R.id.button_save);
        buttonBack = findViewById(R.id.button_back);
    }

    private void extractDataFromBundle(Bundle bundle) {
        year = bundle.getInt("year");
        month = bundle.getInt("month");
        day = bundle.getInt("day");
        weather = bundle.getString("weather");
        mood = bundle.getString("mood");
        textViewDate.setText(String.format(Locale.getDefault(), "%d년 %02d월 %02d일", year, month, day));
    }

    private void updateUI() {
        setWeatherImage();
        setMoodImage();
        setBackgroundImage();
    }

    private void setWeatherImage() {
        imageViewWeather.setImageResource(getWeatherImageResource(weather));
    }

    private int getWeatherImageResource(String weather) {
        switch (weather) {
            case "sunny": return R.drawable.weather_sunny;
            case "rainy": return R.drawable.weather_rainy;
            case "cloudy": return R.drawable.weather_cloudy;
            case "windy": return R.drawable.weather_windy;
            case "lightning": return R.drawable.weather_lightning;
            case "snowy": return R.drawable.weather_snowy;
            case "rainbow": return R.drawable.weather_rainbow;
            default: return R.drawable.question;
        }
    }

    private void setMoodImage() {
        imageViewMood.setImageResource(getMoodImageResource(mood));
    }

    private int getMoodImageResource(String mood) {
        switch (mood) {
            case "happy": return R.drawable.mood_happy;
            case "sad": return R.drawable.mood_sad;
            case "angry": return R.drawable.mood_angry;
            case "lucky": return R.drawable.mood_lucky;
            case "soso": return R.drawable.mood_soso;
            case "flutter": return R.drawable.mood_flutter;
            case "tired": return R.drawable.mood_tired;
            case "comfortable": return R.drawable.mood_comfortable;
            case "exciting": return R.drawable.mood_exciting;
            default: return R.drawable.question;
        }
    }

    private void setBackgroundImage() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        int background = sharedPreferences.getInt("background", R.drawable.background1);
        View rootView = findViewById(android.R.id.content);
        rootView.setBackgroundResource(background);
    }

    private void setButtonListeners() {
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSaveButtonClick();
            }
        });
    }

    private void handleSaveButtonClick() {
        saveDiaryEntry();
        navigateBack();
    }

    private void saveDiaryEntry() {
        String content = editTextDiary.getText().toString();
        String date = getFormattedDate();
        saveDiaryToDatabase(date, weather, mood, content);
    }

    private String getFormattedDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime());
    }

    private void saveDiaryToDatabase(String date, String weather, String mood, String content) {
        SQLiteDatabase db = diaryDBHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("weather", weather);
        values.put("mood", mood);
        values.put("content", content);

        db.insert("diary", null, values);
        db.close();
    }

    private void navigateBack() {
        getSupportFragmentManager().beginTransaction().commit();
        finish();
    }
}
