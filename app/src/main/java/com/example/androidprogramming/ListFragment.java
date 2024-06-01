package com.example.androidprogramming;

import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.util.*;


public class ListFragment extends Fragment {

    private ListView listView;
    private ListAdapter adapter;
    private ArrayList<Diary> diaryList; // Diary 객체의 ArrayList를 사용합니다.
    private DiaryDBHelper diaryDBHelper;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        listView = view.findViewById(R.id.listView);
        diaryList = new ArrayList<>();
        diaryDBHelper = new DiaryDBHelper(getContext());

        // 데이터베이스에서 일기 데이터를 조회합니다.
        diaryList.addAll(diaryDBHelper.getAllDiaries()); // DBHelper에서 가져온 List를 ArrayList에 추가합니다.

        // 리스트뷰에 어댑터를 설정합니다.
        adapter = new ListAdapter(getContext(), diaryList);
        listView.setAdapter(adapter);

        return view;
    }
    public void onResume() {
        super.onResume();
        // 프래그먼트가 화면에 표시될 때 네비게이션 바를 숨깁니다.
        ((MainActivity) getActivity()).showNavigationBar();

    }


}

