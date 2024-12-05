package com.example.bsuir_schedule_app.network;

import com.google.gson.Gson;
import com.example.bsuir_schedule_app.model.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ApiClient {
    private static final String BASE_URL = "https://iis.bsuir.by/api/v1/schedule?studentGroup=";
    private static final String WEEK_URL = "https://iis.bsuir.by/api/v1/schedule/current-week";

    private final OkHttpClient client;
    private final Gson gson;

    public ApiClient() {
        client = new OkHttpClient();
        gson = new Gson();
    }

    public Schedule getSchedule(String groupNumber) throws IOException {
        String url = BASE_URL + groupNumber;
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String jsonResponse = response.body().string();
            return gson.fromJson(jsonResponse, Schedule.class);
        }
    }

    public Integer getCurrentWeek() throws IOException {
        Request request = new Request.Builder()
                .url(WEEK_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String jsonResponse = response.body().string();
            return gson.fromJson(jsonResponse, Integer.class);
        }
    }
}