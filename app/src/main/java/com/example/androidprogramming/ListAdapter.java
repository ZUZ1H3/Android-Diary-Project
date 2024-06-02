package com.example.androidprogramming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Diary> diaryList; // Diary 객체의 ArrayList를 사용합니다.

    public ListAdapter(Context context, ArrayList<Diary> diaryList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        TextView dateText = convertView.findViewById(R.id.dateText);
        TextView diaryText = convertView.findViewById(R.id.diaryText);

        Diary diary = diaryList.get(position);

        // 날짜와 일기 내용을 설정합니다.
        dateText.setText(diary.getDate2(diary.getDate()));
        diaryText.setText(diary.getContent());

        return convertView;
    }
}