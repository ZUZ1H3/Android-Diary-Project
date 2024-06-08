package com.example.androidprogramming;

public class Book {
    private int id;
    private String title;
    private String author;
    private String review;
    private byte[] image; // 이미지 데이터를 저장하는 byte 배열

    // 기본 생성자
    public Book() {
    }

    // 매개변수가 있는 생성자
    public Book(int id, String title, String author, String review, byte[] image) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.review = review;
        this.image = image;
    }

    // getter 및 setter 메소드
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
