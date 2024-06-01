package com.example.androidprogramming;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.*;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DiaryFragment extends Fragment {

    private TextView textView_date, textView_weather, textView_mood;
    private EditText editText_diary;
    private Button button_save;

    private int year, month, day;
    private DiaryDBHelper diaryDBHelper; // DBHelper 인스턴스를 추가합니다.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_writing, container, false);

        textView_date = rootView.findViewById(R.id.textView_date);
        textView_weather = rootView.findViewById(R.id.textView_weather);
        textView_mood = rootView.findViewById(R.id.textView_mood);
        editText_diary = rootView.findViewById(R.id.editText_diary);
        button_save = rootView.findViewById(R.id.button_save);

        // DBHelper 인스턴스를 초기화합니다.
        diaryDBHelper = new DiaryDBHelper(getContext());

        // AddFragment에서 받은 데이터를 설정합니다.
        Bundle bundle = getArguments();
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

                // 데이터베이스에 일기를 저장합니다.
                saveDiary(date, textView_weather.getText().toString(), textView_mood.getText().toString(), content);

                // 일기를 저장한 후 ListFragment로 이동
                /*FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ListFragment listFragment = new ListFragment();

                transaction.replace(R.id.containers, listFragment);
                transaction.addToBackStack(null); // 사용자가 뒤로 가기를 눌렀을 때 이전 프래그먼트로 돌아갈 수 있도록 합니다.
                transaction.commit();*/
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return rootView;
    }

    // 데이터베이스에 일기를 저장하는 메서드로 수정합니다.
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

    // 프래그먼트가 화면에 표시될 때 네비게이션 바를 숨깁니다.
    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).hideNavigationBar();
    }
}
