package com.example.booktracker.api;

import com.example.booktracker.models.GoogleBookResponse;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Query;

public interface GoogleBookAPI {
    @GET("/books/v1/volumes?langRestrict=en&maxResults=40&printType=books")
    public Call<GoogleBookResponse> searchBooks(@Query("key") String key,
                                                @Query("q") String searchText);
}
