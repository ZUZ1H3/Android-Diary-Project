package com.example.androidprogramming;

import android.graphics.Bitmap;

public class Diary {
    private int id;
    private String date;
    private String weather;
    private String mood;
    private String content;
    private byte[] image;

    // 생성자
    public Diary(int id, String date, String weather, String mood, String content, byte[] image) {
        this.id = id;
        this.date = date;
        this.weather = weather;
        this.mood = mood;
        this.content = content;
        this.image = image;
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

        // 변경된 형식의 날짜를 문자열로 반환
        return year + "년 " + month + "월 " + day + "일";
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

    // image에 대한 getter와 setter
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
