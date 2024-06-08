package com.example.androidprogramming;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookRepository {
    private static final String BASE_URL = "https://openapi.naver.com/";
    private NaverApiService apiService;

    public BookRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(NaverApiService.class);
    }

    public void searchBooks(String query, final MutableLiveData<List<BookResponse.Item>> bookItems) {
        apiService.searchBooks(query).enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    bookItems.postValue(response.body().getItems());
                } else {
                    bookItems.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                bookItems.postValue(null);
            }
        });
    }
}
