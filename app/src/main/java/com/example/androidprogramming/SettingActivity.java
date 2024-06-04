package com.example.androidprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5;
    private ImageButton button_save, button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        radioButton5 = findViewById(R.id.radioButton5);
        button_back = findViewById(R.id.button_back);
        button_save = findViewById(R.id.button_save);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedBackground = R.drawable.background4;
                if (radioButton1.isChecked()) {
                    selectedBackground = R.drawable.background1;
                } else if (radioButton2.isChecked()) {
                    selectedBackground = R.drawable.background2;
                } else if (radioButton3.isChecked()) {
                    selectedBackground = R.drawable.background3;
                }else if (radioButton4.isChecked()) {
                    selectedBackground = R.drawable.background4;
                }else if (radioButton5.isChecked()) {
                    selectedBackground = R.drawable.background5;
                }

                SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("background", selectedBackground);
                editor.apply();

                Toast.makeText(SettingActivity.this, "선택한 배경으로 변경되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
