package com.example.androidprogramming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> diaryList;

    public ListAdapter(Context context, ArrayList<String> diaryList) {
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

        String filename = diaryList.get(position);
        String date = filename.substring(0, filename.length() - 4); // 확장자를 제외한 파일 이름
        dateText.setText(date);

        String diary = ""; // 일기 내용
        try {
            FileInputStream fis = context.openFileInput(filename);
            byte[] data = new byte[fis.available()];
            fis.read(data);
            fis.close();
            diary = new String(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        diaryText.setText(diary);

        return convertView;
    }

}
