package com.example.androidprogramming;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NaverApiService {
    @Headers({
            "X-Naver-Client-Id: FtnKrD9uEgqXV0tO02c2",
            "X-Naver-Client-Secret: QaYqy0ijGE"
    })
    @GET("v1/search/book.json")
    Call<BookResponse> searchBooks(@Query("query") String query);
}
