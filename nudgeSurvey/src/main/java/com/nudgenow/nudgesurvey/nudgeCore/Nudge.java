package com.nudgenow.nudgesurvey.nudgeCore;



import com.nudgenow.nudgesurvey.nudgeCore.model.User;
import com.nudgenow.nudgesurvey.nudgeCore.network.NudgeApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Nudge {
    private String apiKey;
    private static String token = "UNITIALIZED";
    private static String nudgeUrl = "https://staging.api.nudgenow.com/api/v1/";
    private CallbackInterface trackcall;
    private static UUID uuid = UUID.randomUUID();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(nudgeUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private NudgeApiService apiService = retrofit.create(NudgeApiService.class);

    public Nudge(String apiKey) {
        if (apiKey.isEmpty()) {
            throw new IllegalArgumentException("Nudge API key is required");
        }
        this.apiKey = apiKey;
    }

    public String initSession(String externalId, JSONObject properties) {

        JSONObject requestBody = new JSONObject();
        try {
            try {
                requestBody.put("externalId", externalId);
                requestBody.put("sessionId", uuid.toString());

                if (properties != null) {
                    for (Iterator<String> it = properties.keys(); it.hasNext(); ) {
                        String entry = it.next();
                        requestBody.put(entry, properties.get(entry));
                    }
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");

            RequestBody body = RequestBody.create(mediaType, requestBody.toString());

            Request request = new Request.Builder()
                    .url(nudgeUrl + "users/u/create")
                    .method("POST", body)
                    .addHeader("authorization", apiKey)
                    .addHeader("Content-Type", "application/json")
                    .build();

            okhttp3.Response response = client.newCall(request).execute();
            String data = response.body().string();
            System.out.println(data);

            try {
                JSONObject jsonObject = new JSONObject(data);

                this.token = jsonObject.getJSONObject("data").getString("token");
                return data;
            }
            catch (JSONException e){
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String track(String type, JSONObject properties) {
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("type", type);
            requestBody.put("sessionId", uuid.toString());



            if (properties != null) {
                for (Iterator<String> it = properties.keys(); it.hasNext(); ) {
                    String entry = it.next();
                    requestBody.put(entry, properties.get(entry));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, requestBody.toString());
            Request request = new Request.Builder()
                    .url(nudgeUrl + "/events/e/track")
                    .method("POST", body)
                    .addHeader("authorization", token)
                    .addHeader("Content-Type", "application/json")
                    .build();
            okhttp3.Response response = client.newCall(request).execute();

            String data = response.body().string();

            try {
                JSONObject jsonObject = new JSONObject(data);

                return jsonObject.toString();
            }
            catch (JSONException e){
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Object availableRewards(User user) {
        Call<Object> call = apiService.availableRewards(user.getData().getToken());
        try {
            Response<Object> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User.Data getRewardsData(User user) {
        Call<User.Data> call = apiService.getRewardsData(user.getData().getId());
        try {
            Response<User.Data> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User.Data getRewardsDataByUser(User user) {
        Call<User.Data> call = apiService.getRewardsData(user.getData().getId());
        try {
            Response<User.Data> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    public Call<Object> availableRewards() {
        return apiService.availableRewards(token);
    }


    public void setTrackCall(CallbackInterface trackcall) {
        this.trackcall = trackcall;
    }


    public interface CallbackInterface {
        void onTrackDataReceived(Object trackData);
    }
    public String getToken() {
        return token;
    }

    public interface InitSessionCallback {
        void onSessionInitialized(String token);
    }
}
/*
This Java code defines a class named "Nudge" that interacts with a remote API service using the Retrofit library for Android.
 The class facilitates session initialization, tracking user actions, and retrieving reward-related data.
 It's designed for integrating a loyalty or points system into an Android app.
 The "initSession" method initializes a user session,
 "track" method sends tracking data to the server, and methods like "availableRewards"
  retrieve reward-related information. Callbacks are used to handle asynchronous responses.
   The class aims to simplify interactions with the Nudge API for developers integrating these features into their
    Android application.
 */