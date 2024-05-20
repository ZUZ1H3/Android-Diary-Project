package com.example.androidprogramming;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarFragment extends Fragment {

    private TextView monthYearText;
    private Date now = new Date();
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월");
    private RecyclerView recyclerView;

    private ListFragment listFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        monthYearText = view.findViewById(R.id.monthYearText);
        Button preBtn = view.findViewById(R.id.preBtn);
        Button nextBtn = view.findViewById(R.id.nextBtn);
        Button listBtn = view.findViewById(R.id.listBtn);
        recyclerView = view.findViewById(R.id.recyclerview);

        calendar.setTime(now);
        setMonthView();

        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                setMonthView();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                setMonthView();
            }
        });

        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                if (listFragment != null) {
                    fragmentTransaction.show(listFragment);
                } else {
                    listFragment = new ListFragment();
                    fragmentTransaction.add(R.id.container, listFragment);
                }

                fragmentTransaction.hide(CalendarFragment.this);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    private void setMonthView() {
        monthYearText.setText(dateFormat.format(calendar.getTime()));
        ArrayList<String> dayList = dayInMonthArray(calendar);
        CalendarAdapter adapter = new CalendarAdapter(dayList);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 7);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }


    private ArrayList<String> dayInMonthArray(Calendar calendar) {
        ArrayList<String> dayList = new ArrayList<>();
        int month = calendar.get(Calendar.MONTH) + 1;
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 현재 월의 첫 번째 날의 요일

        // 빈 칸 채우기
        for (int i = 1; i < dayOfWeek; i++) {
            dayList.add("");
        }

        for (int i = 1; i <= lastDay; i++) {
            dayList.add(String.valueOf(i));
        }

        return dayList;
    }
}
