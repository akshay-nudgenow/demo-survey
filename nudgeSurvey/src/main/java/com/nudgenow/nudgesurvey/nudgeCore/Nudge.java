package com.nudgenow.nudgesurvey.nudgeCore;



import com.nudgenow.nudgesurvey.nudgeCore.model.User;
import com.nudgenow.nudgesurvey.nudgeCore.network.NudgeApiService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Nudge {
    private String apiKey;
    private String token = "UNITIALIZED";
    private String nudgeUrl = "https://pointsystem.api.nudgenow.com/api/v1";
    private CallbackInterface trackcall;
    private UUID uuid = UUID.randomUUID();

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

    public Response<User> initSession(User user, String externalId, Map<String, Object> properties) {
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("externalId", externalId);
            if (properties != null) {
                for (Map.Entry<String, Object> entry : properties.entrySet()) {
                    requestBody.put(entry.getKey(), entry.getValue());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), requestBody.toString());

        Call<User> call = apiService.initSession(body);
        try {
            Response<User> response = call.execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Response<ResponseBody> track(User user, String type, Map<String, Object> properties) {
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("type", type);
            requestBody.put("sessionId", uuid.toString());
            if (properties != null) {
                for (Map.Entry<String, Object> entry : properties.entrySet()) {
                    requestBody.put(entry.getKey(), entry.getValue());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), requestBody.toString());

        Call<ResponseBody> call = apiService.track(body);
        try {
            Response<ResponseBody> response = call.execute();
            return response;
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