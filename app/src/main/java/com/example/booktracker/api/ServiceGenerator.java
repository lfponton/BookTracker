package com.example.booktracker.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static GoogleBookAPI googleBookAPI;

    public static GoogleBookAPI getGoogleBookAPI() {
        if (googleBookAPI == null) {
            googleBookAPI = new Retrofit.Builder()
                    .baseUrl("https://www.googleapis.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(GoogleBookAPI.class);
        }
        return googleBookAPI;
    }
}
