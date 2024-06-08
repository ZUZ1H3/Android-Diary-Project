package com.example.androidprogramming;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class BookViewModel extends ViewModel {
    private MutableLiveData<List<BookResponse.Item>> bookItems;
    private BookRepository bookRepository;

    public BookViewModel() {
        bookItems = new MutableLiveData<>();
        bookRepository = new BookRepository();
    }

    public LiveData<List<BookResponse.Item>> getBookItems() {
        return bookItems;
    }

    public void searchBooks(String query) {
        bookRepository.searchBooks(query, bookItems);
    }
}
