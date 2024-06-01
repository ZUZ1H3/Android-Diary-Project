package com.example.androidprogramming;

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
