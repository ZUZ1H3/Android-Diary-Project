package com.example.androidprogramming;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChartActivity extends AppCompatActivity {

    PieChart pieChart; // PieChart 객체 선언
    DiaryDBHelper dbHelper; // 데이터베이스 헬퍼 객체 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart); // 레이아웃 설정

        pieChart = findViewById(R.id.piechart); // PieChart 뷰 초기화
        dbHelper = new DiaryDBHelper(this); // DiaryDBHelper 객체 초기화

        pieChart.setUsePercentValues(true); // 퍼센트 값 사용 설정 해제
        pieChart.getDescription().setEnabled(false); // 설명 비활성화
        pieChart.setExtraOffsets(5, 0, 5, 5); // 차트의 여백 설정

        pieChart.setDragDecelerationFrictionCoef(0.95f); // 드래그 감속 마찰 계수 설정

        pieChart.setDrawHoleEnabled(true); // 구멍 그리기 비활성화
        //pieChart.setHoleColor(Color.WHITE); // 구멍 색상 설정
        //pieChart.setTransparentCircleRadius(61f); // 투명 원의 반지름 설정

        ArrayList<PieEntry> yValues = loadMoodData(); // 데이터 로드

        Description description = new Description(); // 설명 객체 생성
        description.setText("나의 감정"); // 설명 텍스트 설정
        description.setTextSize(15); // 설명 텍스트 크기 설정
        pieChart.setDescription(description); // 차트에 설명 설정

        pieChart.animateY(1000, Easing.EaseInOutCubic); // 애니메이션 설정

        PieDataSet dataSet = new PieDataSet(yValues, "Moods"); // 데이터셋 생성
        dataSet.setSliceSpace(3f); // 슬라이스 간격 설정
        dataSet.setSelectionShift(5f); // 선택 이동 크기 설정

        // 색상 설정을 위한 맵 (16진수 문자열로 지정)
        Map<String, Integer> moodColorMap = new HashMap<>();
        moodColorMap.put("happy", Color.parseColor("#FFEC91"));
        moodColorMap.put("sad", Color.parseColor("#A4E1DD"));
        moodColorMap.put("tired", Color.parseColor("#DCDCDC"));
        moodColorMap.put("angry", Color.parseColor("#EA7878"));
        moodColorMap.put("comfortable", Color.parseColor("#F7CEBB"));
        moodColorMap.put("exciting", Color.parseColor("#9BF4D5"));
        moodColorMap.put("flutter", Color.parseColor("#FFC9DE"));
        moodColorMap.put("lucky", Color.parseColor("#ADEE97"));
        moodColorMap.put("soso", Color.parseColor("#E5C5E5"));

        // 각 엔트리에 대해 색상 설정
        ArrayList<Integer> colors = new ArrayList<>();
        for (PieEntry entry : yValues) {
            String mood = entry.getLabel();
            if (moodColorMap.containsKey(mood)) {
                colors.add(moodColorMap.get(mood)); // mood에 해당하는 색상 추가
            } else {
                colors.add(Color.GRAY); // 기본 색상으로 회색 추가
            }
        }
        dataSet.setColors(colors); // 데이터셋에 색상 설정

        PieData data = new PieData(dataSet); // PieData 객체 생성
        data.setValueTextSize(10f); // 값 텍스트 크기 설정
        data.setValueTextColor(Color.BLACK); // 값 텍스트 색상 설정


        pieChart.setData(data); // 차트에 데이터 설정
    }

    // 데이터베이스에서 mood 데이터를 로드하는 메서드
    private ArrayList<PieEntry> loadMoodData() {
        ArrayList<PieEntry> yValues = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase(); // 읽기 가능한 데이터베이스 가져오기

        // Mood 데이터를 그룹화하고 카운트
        String query = "SELECT mood, COUNT(*) as count FROM diary GROUP BY mood";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String mood = cursor.getString(0); // mood 값 가져오기
                int count = cursor.getInt(1); // count 값 가져오기
                yValues.add(new PieEntry(count, mood)); // PieEntry에 추가
            } while (cursor.moveToNext());
        }

        cursor.close(); // 커서 닫기
        db.close(); // 데이터베이스 닫기

        return yValues; // 데이터 반환
    }
}
