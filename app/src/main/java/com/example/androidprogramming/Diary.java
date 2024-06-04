package com.example.androidprogramming;

import java.util.Calendar;

public class Diary {
    private int id;
    private String date;
    private String weather;
    private String mood;
    private String content;

    // 생성자
    public Diary(int id, String date, String weather, String mood, String content) {
        this.id = id;
        this.date = date;
        this.weather = weather;
        this.mood = mood;
        this.content = content;
    }

    // id에 대한 getter와 setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // date에 대한 getter와 setter
    public String getDate() {
        return date;
    }

    public String getDate2(String date) {
        String[] dateParts = date.split("-"); // "yyyy-MM-dd" 형식의 날짜를 "-"로 분리
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        // Calendar 객체를 사용하여 날짜 형식 변경
        //Calendar calendar = Calendar.getInstance();
        //calendar.set(month - 1, day); // month는 0부터 시작하므로 -1 해줍니다.

        // 변경된 형식의 날짜를 문자열로 반환
        return year + "년 "
                + month + "월 "
                + day + "일";
    }

    public void setDate(String date) {
        this.date = date;
    }



    // weather에 대한 getter와 setter
    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    // mood에 대한 getter와 setter
    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    // content에 대한 getter와 setter
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
