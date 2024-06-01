package com.example.androidprogramming;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {
    private ArrayList<Calendar> dayList;
    private DiaryDBHelper diaryDBHelper; // DBHelper 인스턴스를 추가합니다.

    public CalendarAdapter(ArrayList<Calendar> dayList, Context context) {
        this.dayList = dayList;
        this.diaryDBHelper = new DiaryDBHelper(context); // DBHelper 인스턴스를 초기화합니다.
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        Calendar day = dayList.get(position);

        if (day == null) {
            holder.dayText.setText(""); // 빈 칸
        } else {
            boolean hasDiary = checkDiaryData(day);

            if (hasDiary) {
                holder.dayText.setVisibility(View.GONE); // 텍스트를 숨깁니다
                holder.dayImage.setVisibility(View.VISIBLE); // 이미지를 표시합니다

                // 이미지를 설정합니다. 이 예시에서는 diaryImage라는 리소스를 사용한다고 가정합니다.
                holder.dayImage.setImageResource(R.drawable.emoji);
            } else {
                holder.dayText.setText(String.valueOf(day.get(Calendar.DAY_OF_MONTH)));
                holder.dayText.setVisibility(View.VISIBLE); // 텍스트를 표시합니다
                holder.dayImage.setVisibility(View.GONE); // 이미지를 숨깁니다
            }
        }

        // 텍스트 색상 지정 (토, 일)
        if ((position + 1) % 7 == 0) {
            holder.dayText.setTextColor(Color.BLUE);
        } else if (position == 0 || position % 7 == 0) {
            holder.dayText.setTextColor(Color.RED);
        }

        // 날짜 클릭 이벤트
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int iYear = day.get(Calendar.YEAR);
                int iMonth = day.get(Calendar.MONTH) + 1; // Calendar.MONTH는 0부터 시작
                int iDay = day.get(Calendar.DAY_OF_MONTH);

                AddFragment addFragment = new AddFragment();
                // 날짜 데이터를 번들에 담아 프래그먼트로 전달
                Bundle bundle = new Bundle();
                bundle.putInt("year", iYear);
                bundle.putInt("month", iMonth);
                bundle.putInt("day", iDay);
                addFragment.setArguments(bundle);
                ((MainActivity)holder.itemView.getContext()).getSupportFragmentManager().beginTransaction()
                        .add(R.id.containers, addFragment)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView dayText;
        ImageView dayImage; // 추가

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            dayText = itemView.findViewById(R.id.dayText); // dayText 초기화
            dayImage = itemView.findViewById(R.id.dayImage); // dayImage 초기화
        }
    }

    private boolean checkDiaryData(Calendar day) {
        String date = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault()).format(day.getTime());
        // DBHelper를 사용하여 해당 날짜에 일기 데이터가 있는지 확인합니다.
        return diaryDBHelper.hasDiaryOnDate(date);
    }
}
