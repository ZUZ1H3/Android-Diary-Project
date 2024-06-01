package com.example.androidprogramming;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.*;


public class AddFragment extends Fragment {

    private RadioGroup rGroup_weather, rGroup_mood;
    private Button button_write;
    private int year, month, day;  // 'final' 키워드 추가

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);

        rGroup_weather = rootView.findViewById(R.id.rGroup_weather);
        rGroup_mood = rootView.findViewById(R.id.rGroup_mood);
        button_write = rootView.findViewById(R.id.button_write);

        // 날짜 데이터 받아오기
        Bundle bundle = getArguments();
        if (bundle != null) {
            year = bundle.getInt("year");
            month = bundle.getInt("month");
            day = bundle.getInt("day");

            // 날짜를 텍스트뷰에 표시
            TextView dateText = rootView.findViewById(R.id.date);
            dateText.setText(month + "월 " + day + "일");
        }

        button_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedWeatherId = rGroup_weather.getCheckedRadioButtonId();
                int selectedMoodId = rGroup_mood.getCheckedRadioButtonId();

                String selectedWeather = ((RadioButton) rootView.findViewById(selectedWeatherId)).getText().toString();
                String selectedMood = ((RadioButton) rootView.findViewById(selectedMoodId)).getText().toString();

                Bundle bundle = new Bundle();
                bundle.putInt("year", year);
                bundle.putInt("month", month);
                bundle.putInt("day", day);
                bundle.putString("weather", selectedWeather);
                bundle.putString("mood", selectedMood);

                DiaryFragment diaryFragment = new DiaryFragment();
                diaryFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().popBackStack();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.containers, diaryFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
    }


    public void onResume() {
        super.onResume();
        // 프래그먼트가 화면에 표시될 때 네비게이션 바를 숨깁니다.
        ((MainActivity) getActivity()).hideNavigationBar();
    }

}