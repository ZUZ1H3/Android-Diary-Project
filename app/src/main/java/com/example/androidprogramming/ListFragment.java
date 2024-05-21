package com.example.androidprogramming;

import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class ListFragment extends Fragment {

    private ListView listView;
    private DiaryAdapter adapter;
    private ArrayList<String> diaryList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        listView = view.findViewById(R.id.listView);
        diaryList = new ArrayList<>();

        // 일기 파일들을 읽어옵니다.
        File directory = getContext().getFilesDir(); // 일기 파일들이 저장된 디렉토리
        File[] files = directory.listFiles(); // 디렉토리 안의 모든 파일들
        for (File file : files) {
            String filename = file.getName();
            if (filename.endsWith(".txt")) { // .txt 파일만 선택
                diaryList.add(filename);
            }
        }

        // 그리드뷰에 어댑터를 설정합니다.
        adapter = new DiaryAdapter(getContext(), diaryList);
        listView.setAdapter(adapter);

        return view;
    }

}
