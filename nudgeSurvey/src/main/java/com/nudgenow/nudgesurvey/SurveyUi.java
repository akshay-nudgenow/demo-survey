package com.nudgenow.nudgesurvey;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.nudgenow.nudgesurvey.nudgeCore.NudgeProvider;
import com.nudgenow.nudgesurvey.nudgeCore.NudgeUi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SurveyUi extends NudgeUi {

    public interface ApiCallback {
        void onResponseReceived(String response);
    }

    private ApiCallback apiCallback;

    public void setApiCallback(ApiCallback apiCallback) {
        this.apiCallback = apiCallback;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public NudgeUi copyWith(String id, String token) {
        return null;
    }

    @Override
    public void trigger(Context context, String userStatId, String position) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"name\": \"Kokadwar\",\r\n    \"email\": \"pawan@gmail.com\",\r\n    \"phoneNumber\": \"8083385209\",\r\n    \"externalId\": \"16da5f22ee8f892d2b51ebbf\"\r\n}");
        Request request = new Request.Builder()
                .url("https://pointsystem.api.nudgenow.com/api/v1/users/u/create")
                .method("POST", body)
                .addHeader("authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NDA3MzYwMjU0NzBiNzVlNmFlOTIwMjAiLCJuYW1lIjoiTmFiYWppdCIsImVtYWlsIjoibmFiYWppdEBudWRnZW5vdy5pbiIsImlhdCI6MTY3ODE5NDE3OH0.nE3IFf2Mg4bnr8dRcj38mK6H6-utNREQOTTRi95OsF0")
                .addHeader("Content-Type", "application/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    System.out.println("Unsuccessful response: " + response);
                } else {
                    String responseData = response.body().string();
                    System.out.println("Response Data: " + responseData);
                    if (apiCallback != null) {

                        new Handler(Looper.getMainLooper()).post(() -> apiCallback.onResponseReceived(responseData));
                    }
                }
            }
        });
    }






    public void triggerSecondApi(String surveyId, String userStatId, int pageNo) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()

                .url("https://pointsystem.api.nudgenow.com/api/v1/form/enduser/survey/64f1c4ed9d210740de43f6ca/page/"+pageNo)
                .method("GET", null)
                .addHeader("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY0ZjIwMmZlYmNhNmNiOTVhMjEzMjZiMiIsImNsaWVudElkIjoiNjQwNzM2MDI1NDcwYjc1ZTZhZTkyMDIwIiwicm9sZSI6InVzZXIiLCJpYXQiOjE2OTM1ODIwNzgsImV4cCI6MTc3MTM0MjA3OH0.Ch4DF4kdvsUzYeFGfZJR_gJbyYYKl2u4XinvqBQYPls")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    System.out.println("Unsuccessful response: " + response);
                } else {
                    String responseData = response.body().string();
                    System.out.println("Response Data: " + responseData);
                    if (apiCallback != null) {
                        new Handler(Looper.getMainLooper()).post(() -> apiCallback.onResponseReceived(responseData));
                    }
                }
            }
        });
    }


    @Override
    public void getCallback(NudgeProvider.NudgeCallback callback) {

    }

    @Override
    public void trigger(String userStatId, String position) {

    }
}