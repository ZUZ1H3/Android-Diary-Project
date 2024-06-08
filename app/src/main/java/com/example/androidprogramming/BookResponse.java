package com.example.androidprogramming;

import java.util.List;

public class BookResponse {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public static class Item {
        private String title;
        private String image;
        private String author;

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }

        public String getAuthor() {
            return author;
        }
    }
}
