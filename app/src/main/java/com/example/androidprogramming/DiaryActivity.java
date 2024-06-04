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

    private TextView textView_date;
    private ImageView imageView_weather, imageView_mood;
    private EditText editText_diary;
    private ImageButton button_save;

    private int year, month, day;
    private String weather, mood;

    private DiaryDBHelper diaryDBHelper;

    private static final String[] WEATHER_TYPES = {"sunny", "rainy", "cloudy", "windy", "lightning", "snowy", "rainbow"};
    private static final int[] WEATHER_IMAGES = {R.drawable.weather_sunny, R.drawable.weather_rainy, R.drawable.weather_cloudy, R.drawable.weather_windy, R.drawable.weather_lightning, R.drawable.weather_snowy, R.drawable.weather_rainbow};

    private static final String[] MOOD_TYPES = {"happy", "sad", "angry", "lucky", "soso", "flutter", "tired", "comfortable", "exciting"};
    private static final int[] MOOD_IMAGES = {R.drawable.mood_happy, R.drawable.mood_sad, R.drawable.mood_angry, R.drawable.mood_lucky, R.drawable.mood_soso, R.drawable.mood_flutter, R.drawable.mood_tired, R.drawable.mood_comfortable, R.drawable.mood_exciting};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        initializeViews();
        diaryDBHelper = new DiaryDBHelper(this);

        if (getIntent().getExtras() != null) {
            extractIntentData(getIntent().getExtras());
            updateUI();
            setBackground();
        }

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDiaryAndFinish();
            }
        });
    }

    private void initializeViews() {
        textView_date = findViewById(R.id.textView_date);
        imageView_weather = findViewById(R.id.ImageView_weather);
        imageView_mood = findViewById(R.id.ImageView_mood);
        editText_diary = findViewById(R.id.editText_diary);
        button_save = findViewById(R.id.button_save);
    }

    private void extractIntentData(Bundle bundle) {
        year = bundle.getInt("year", 0);
        month = bundle.getInt("month", 0);
        day = bundle.getInt("day", 0);
        weather = bundle.getString("weather", "");
        mood = bundle.getString("mood", "");
    }

    private void updateUI() {
        textView_date.setText(String.format(Locale.getDefault(), "%d년 %02d월 %02d일", year, month, day));
        setImageResource(imageView_weather, WEATHER_TYPES, WEATHER_IMAGES, weather);
        setImageResource(imageView_mood, MOOD_TYPES, MOOD_IMAGES, mood);
    }

    private void setImageResource(ImageView imageView, String[] types, int[] images, String type) {
        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(type)) {
                imageView.setImageResource(images[i]);
                break;
            }
        }
    }

    private void setBackground() {
        SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        int background = sharedPreferences.getInt("background", R.drawable.background1);
        findViewById(android.R.id.content).setBackgroundResource(background);
    }

    private void saveDiaryAndFinish() {
        String content = editText_diary.getText().toString();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.getTime());

        saveDiary(date, weather, mood, content);
        finish();
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
