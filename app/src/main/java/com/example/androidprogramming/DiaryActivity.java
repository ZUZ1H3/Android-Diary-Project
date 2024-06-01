package com.example.androidprogramming;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    private TextView textView_date, textView_weather, textView_mood;
    private EditText editText_diary;
    private Button button_save;

    private int year, month, day;
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
            String weather = bundle.getString("weather");
            String mood = bundle.getString("mood");

            textView_date.setText(String.format(Locale.getDefault(), "%d년 %d월 %02d일", year, month, day));
            textView_mood.setText(mood);
            textView_weather.setText(weather);
        }

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = editText_diary.getText().toString();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month - 1, day);
                String date = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault()).format(calendar.getTime());

                saveDiary(date, textView_weather.getText().toString(), textView_mood.getText().toString(), content);
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