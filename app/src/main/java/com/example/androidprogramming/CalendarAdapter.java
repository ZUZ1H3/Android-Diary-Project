package com.example.androidprogramming;

import android.content.Context;
import android.content.Intent; // Intent import 추가
import android.graphics.Color;
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
    private DiaryDBHelper diaryDBHelper;

    public CalendarAdapter(ArrayList<Calendar> dayList, Context context) {
        this.dayList = dayList;
        this.diaryDBHelper = new DiaryDBHelper(context);
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
            String mood = checkDiaryMood(day);

            if (mood != null) {
                holder.dayText.setVisibility(View.GONE); // 텍스트를 숨깁니다
                holder.dayImage.setVisibility(View.VISIBLE); // 이미지를 표시합니다

                switch (mood) {
                    case "happy":
                        holder.dayImage.setImageResource(R.drawable.mood_happy);
                        break;
                    case "sad":
                        holder.dayImage.setImageResource(R.drawable.mood_sad);
                        break;
                    case "angry":
                        holder.dayImage.setImageResource(R.drawable.mood_angry);
                        break;
                    case "lucky":
                        holder.dayImage.setImageResource(R.drawable.mood_lucky);
                        break;
                    case "soso":
                        holder.dayImage.setImageResource(R.drawable.mood_soso);
                        break;
                    case "flutter":
                        holder.dayImage.setImageResource(R.drawable.mood_flutter);
                        break;
                    case "tired":
                        holder.dayImage.setImageResource(R.drawable.mood_tired);
                        break;
                    case "comfortable":
                        holder.dayImage.setImageResource(R.drawable.mood_comfortable);
                        break;
                    case "exiting":
                        holder.dayImage.setImageResource(R.drawable.mood_exciting);
                        break;
                    default:
                        holder.dayImage.setImageResource(R.drawable.emoji); // 기본 이미지
                        break;
                }
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

                // AddActivity 실행을 위한 Intent 생성
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, AddActivity.class);
                // 데이터를 Intent에 추가
                intent.putExtra("year", iYear);
                intent.putExtra("month", iMonth);
                intent.putExtra("day", iDay);
                // AddActivity 실행
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView dayText;
        ImageView dayImage;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            dayText = itemView.findViewById(R.id.dayText);
            dayImage = itemView.findViewById(R.id.dayImage);
        }
    }

    private String checkDiaryMood(Calendar day) {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(day.getTime());
        // DBHelper를 사용하여 해당 날짜의 기분 데이터를 가져옵니다.
        return diaryDBHelper.getMoodOnDate(date);
    }
}
