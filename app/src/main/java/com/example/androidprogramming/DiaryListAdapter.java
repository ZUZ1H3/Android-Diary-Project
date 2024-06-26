package com.example.androidprogramming;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DiaryListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Diary> diaryList;

    public DiaryListAdapter(Context context, ArrayList<Diary> diaryList) {
        this.context = context;
        this.diaryList = diaryList;
    }

    @Override
    public int getCount() {
        return diaryList.size();
    }

    @Override
    public Object getItem(int position) {
        return diaryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.diary_list_item, parent, false);
        }

        TextView dateText = convertView.findViewById(R.id.dateText);
        TextView diaryText = convertView.findViewById(R.id.diaryText);
        ImageView weatherImg = convertView.findViewById(R.id.weatherImg);
        ImageView moodImg = convertView.findViewById(R.id.moodImg);
        ImageView diaryImg = convertView.findViewById(R.id.diaryImg); // 새로 추가된 이미지뷰

        Diary diary = diaryList.get(position);

        // 날짜와 일기 내용 설정
        dateText.setText(diary.getDate2(diary.getDate()));
        diaryText.setText(diary.getContent());

        // 날씨 이미지 설정
        switch (diary.getWeather()) {
            case "sunny":
                weatherImg.setImageResource(R.drawable.weather_sunny);
                break;
            case "rainy":
                weatherImg.setImageResource(R.drawable.weather_rainy);
                break;
            case "cloudy":
                weatherImg.setImageResource(R.drawable.weather_cloudy);
                break;
            case "windy":
                weatherImg.setImageResource(R.drawable.weather_windy);
                break;
            case "lightning":
                weatherImg.setImageResource(R.drawable.weather_lightning);
                break;
            case "snowy":
                weatherImg.setImageResource(R.drawable.weather_snowy);
                break;
            case "rainbow":
                weatherImg.setImageResource(R.drawable.weather_rainbow);
                break;
            default:
                weatherImg.setImageResource(R.drawable.question);
                break;
        }

        // 기분 이미지 설정
        switch (diary.getMood()) {
            case "happy":
                moodImg.setImageResource(R.drawable.mood_happy);
                break;
            case "sad":
                moodImg.setImageResource(R.drawable.mood_sad);
                break;
            case "angry":
                moodImg.setImageResource(R.drawable.mood_angry);
                break;
            case "lucky":
                moodImg.setImageResource(R.drawable.mood_lucky);
                break;
            case "soso":
                moodImg.setImageResource(R.drawable.mood_soso);
                break;
            case "flutter":
                moodImg.setImageResource(R.drawable.mood_flutter);
                break;
            case "tired":
                moodImg.setImageResource(R.drawable.mood_tired);
                break;
            case "comfortable":
                moodImg.setImageResource(R.drawable.mood_comfortable);
                break;
            case "exciting":
                moodImg.setImageResource(R.drawable.mood_exciting);
                break;
            default:
                moodImg.setImageResource(R.drawable.question);
                break;
        }

        // DiaryListAdapter의 getView 메서드에서 일기 이미지 설정 부분
        byte[] imageBytes = diary.getImage();
        if (imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            if (bitmap != null) {
                diaryImg.setImageBitmap(bitmap);
                diaryImg.setVisibility(View.VISIBLE);
            } else {
                diaryImg.setVisibility(View.GONE);
            }
        } else {
            diaryImg.setVisibility(View.GONE);
        }

        return convertView;
    }

}
