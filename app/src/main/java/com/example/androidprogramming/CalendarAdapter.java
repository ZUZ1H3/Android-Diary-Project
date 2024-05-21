package com.example.androidprogramming;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {
    ArrayList<Calendar> dayList;

    public CalendarAdapter(ArrayList<Calendar> dayList) {
        this.dayList = dayList;
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
            holder.dayText.setText(String.valueOf(day.get(Calendar.DAY_OF_MONTH)));
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

                //String yearMonDay = iYear + "년 " + iMonth + "월 " + iDay + "일";
                //Toast.makeText(holder.itemView.getContext(), yearMonDay, Toast.LENGTH_SHORT).show();

                AddFragment addFragment = new AddFragment();
                // 날짜 데이터를 번들에 담아 프래그먼트로 전달
                Bundle bundle = new Bundle();
                bundle.putInt("year", iYear);
                bundle.putInt("month", iMonth);
                bundle.putInt("day", iDay);
                addFragment.setArguments(bundle);
                ((MainActivity)holder.itemView.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, addFragment)
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

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            dayText = itemView.findViewById(R.id.dayText); // dayText 초기화
        }
    }
}
