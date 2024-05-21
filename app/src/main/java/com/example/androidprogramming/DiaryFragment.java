package com.example.androidprogramming;

import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.*;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DiaryFragment extends Fragment {

    private TextView textView_weather, textView_mood;
    private EditText editText_diary;
    private Button button_save;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_writing, container, false);

        textView_weather = rootView.findViewById(R.id.textView_weather);
        textView_mood = rootView.findViewById(R.id.textView_mood);
        editText_diary = rootView.findViewById(R.id.editText_diary);
        button_save = rootView.findViewById(R.id.button_save);

        // AddFragment에서 받은 데이터를 설정합니다.
        Bundle bundle = getArguments();
        if (bundle != null) {
            String weather = bundle.getString("weather");
            String mood = bundle.getString("mood");

            textView_weather.setText(weather);
            textView_mood.setText(mood);
        }

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diary = editText_diary.getText().toString();
                String date = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault()).format(new Date());
                saveDiary(diary, date);

                // 일기를 저장한 후 ListFragment로 이동
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                ListFragment listFragment = new ListFragment();

                transaction.replace(R.id.container, listFragment);
                transaction.commit();
            }
        });


        return rootView;
    }
    public void saveDiary(String diary, String date) {
        String filename = date + ".txt";
        FileOutputStream outputStream;

        try {
            outputStream = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(diary.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onResume() {
        super.onResume();
        // 프래그먼트가 화면에 표시될 때 네비게이션 바를 숨깁니다.
        ((MainActivity) getActivity()).hideNavigationBar();
    }
}
