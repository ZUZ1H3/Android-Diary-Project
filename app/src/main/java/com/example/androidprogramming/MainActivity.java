package com.example.androidprogramming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView monthYearText;
    private Date now = new Date();
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월");
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton listBtn = findViewById(R.id.listBtn);
        ImageButton graphBtn = findViewById(R.id.graphBtn);
        ImageButton settingBtn = findViewById(R.id.settingBtn);
        ImageButton preBtn = findViewById(R.id.preBtn);
        ImageButton nextBtn = findViewById(R.id.nextBtn);

        monthYearText = findViewById(R.id.monthYearText);
        recyclerView = findViewById(R.id.recyclerview);

        calendar.setTime(now);
        setMonthView();

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }
        });

        graphBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChartActivity.class);
                startActivity(intent);
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                setMonthView();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                setMonthView();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);

        ImageButton listBtn = findViewById(R.id.listBtn);
        ImageButton graphBtn = findViewById(R.id.graphBtn);
        ImageButton settingBtn = findViewById(R.id.settingBtn);
        ImageButton preBtn = findViewById(R.id.preBtn);
        ImageButton nextBtn = findViewById(R.id.nextBtn);

        monthYearText = findViewById(R.id.monthYearText);
        recyclerView = findViewById(R.id.recyclerview);

        calendar.setTime(now);
        setMonthView();

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }
        });

        graphBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChartActivity.class);
                startActivity(intent);
            }
        });

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                setMonthView();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                setMonthView();
            }
        });
        // SharedPreferences에서 배경 이미지 읽어오기
        SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
        int background = sharedPreferences.getInt("background", R.drawable.background1);

        // 배경 이미지 설정하기
        View rootView = findViewById(android.R.id.content);
        rootView.setBackgroundResource(background);

    }

    private void setMonthView() {
        monthYearText.setText(dateFormat.format(calendar.getTime()));
        ArrayList<Calendar> dayList = dayInMonthArray(calendar);
        CalendarAdapter adapter = new CalendarAdapter(dayList, this);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 7);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Calendar> dayInMonthArray(Calendar calendar) {
        ArrayList<Calendar> dayList = new ArrayList<>();
        int month = calendar.get(Calendar.MONTH) + 1;
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 현재 월의 첫 번째 날의 요일

        // 빈 칸 채우기
        for (int i = 1; i < dayOfWeek; i++) {
            dayList.add(null);
        }

        for (int i = 1; i <= lastDay; i++) {
            Calendar day = (Calendar) calendar.clone();
            day.set(Calendar.DAY_OF_MONTH, i);
            dayList.add(day);
        }

        return dayList;
    }
}
