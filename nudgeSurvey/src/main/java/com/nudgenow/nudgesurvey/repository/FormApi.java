package com.nudgenow.nudgesurvey.repository;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import okhttp3.ResponseBody;


public class FormApi {
    private final String id;
    private final String token;
    private final ApiService apiService;

    public FormApi(String id, String token) {
        this.id = id;
        this.token = token;

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(ApiService.class);
    }


    public Call<ResponseBody> getPage(int pageNo) {
        return apiService.getPage(id, pageNo, token);
    }

    public Call<ResponseBody> skipPage(String surveyId, String userStatId) {
        return apiService.skipPage(surveyId, userStatId, token);
    }

    public Call<ResponseBody> answerPage(String surveyId, int pageId, Map<String, Object> pageData, String userStatId) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), new Gson().toJson(pageData));
        return apiService.answerPage(surveyId, pageId, userStatId, token, requestBody);
    }
}
