package com.example.androidprogramming;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.*;


public class AddFragment extends Fragment {

    private RadioGroup rGroup_weather, rGroup_mood;
    private Button button_write;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);

        rGroup_weather = rootView.findViewById(R.id.rGroup_weather);
        rGroup_mood = rootView.findViewById(R.id.rGroup_mood);
        button_write = rootView.findViewById(R.id.button_write);

        button_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedWeatherId = rGroup_weather.getCheckedRadioButtonId();
                int selectedMoodId = rGroup_mood.getCheckedRadioButtonId();

                String selectedWeather = ((RadioButton) rootView.findViewById(selectedWeatherId)).getText().toString();
                String selectedMood = ((RadioButton) rootView.findViewById(selectedMoodId)).getText().toString();


                Bundle bundle = new Bundle();
                bundle.putString("weather", selectedWeather);
                bundle.putString("mood", selectedMood);

                DiaryFragment diaryFragment = new DiaryFragment();
                diaryFragment.setArguments(bundle);
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, diaryFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return rootView;
        //return inflater.inflate(R.layout.fragment_add, container, false);
    }

}