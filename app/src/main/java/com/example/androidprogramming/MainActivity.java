package com.example.androidprogramming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationBarView;

import java.util.Set;

public class MainActivity extends AppCompatActivity{

    CalendarFragment calendarFragment;
    AddFragment addFragment;
    SettingFragment settingFragment;
    Fragment activeFragment;

    ListFragment listFragment;

    NavigationBarView navigationBarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarFragment = new CalendarFragment();
        addFragment = new AddFragment();
        settingFragment = new SettingFragment();
        listFragment = new ListFragment();

        // Add fragments to container
        getSupportFragmentManager().beginTransaction().add(R.id.containers, settingFragment, "3").hide(settingFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.containers, listFragment, "2").hide(listFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.containers, calendarFragment, "1").commit();

        activeFragment = calendarFragment;

        navigationBarView = findViewById(R.id.bottom_navigationview);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.calendar) {
                    getSupportFragmentManager().beginTransaction().hide(activeFragment).show(calendarFragment).commit();
                    activeFragment = calendarFragment;
                    return true;
                } else if (itemId == R.id.list) {
                    getSupportFragmentManager().beginTransaction().hide(activeFragment).show(listFragment).commit();
                    activeFragment = listFragment;
                    return true;
                } else if (itemId == R.id.setting) {
                    getSupportFragmentManager().beginTransaction().hide(activeFragment).show(settingFragment).commit();
                    activeFragment = settingFragment;
                    return true;
                }
                return false;
            }

        });

    }

    public void showNavigationBar() {
        navigationBarView.setVisibility(View.VISIBLE);
    }

    // 네비게이션 바를 숨기는 메서드
    public void hideNavigationBar() {
        navigationBarView.setVisibility(View.GONE);
    }

}