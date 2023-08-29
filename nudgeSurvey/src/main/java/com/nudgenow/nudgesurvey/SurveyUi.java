package com.nudgenow.nudgesurvey;
import android.content.Context;

import android.content.Context;

import com.nudgenow.nudgesurvey.nudgeCore.NudgeProvider;
import com.nudgenow.nudgesurvey.nudgeCore.NudgeUi;

import java.util.function.Function;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public class SurveyUi extends NudgeUi {
    private final String token;
    private final String id;
    private Function<String, Void> ctacall;
    private String currentPosition;

    public SurveyUi(String token, String id, Function<String, Void> ctacall) {
        this.token = token;
        this.id = id;
        this.ctacall = ctacall;
    }


    public void getCallback(Function<String, Void> callback) {
        ctacall = callback;
    }

    public void fetchSurveyData(String userStatId) {
        String url = "https://pointsystem.api.nudgenow.com/api/v1/form/enduser/survey/get/" + id + "/" + userStatId;

        NudgeApiService apiService = NudgeApiClient.getClient().create(NudgeApiService.class);
        Call<String> call = apiService.getSurveyData(url, token);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String responseData = response.body();

                } else {

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
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
        return new SurveyUi(token, id, ctacall);
    }

    @Override
    public void trigger(Context context, String userStatId, String position) {
        currentPosition = position;
        fetchSurveyData(userStatId);
    }

    @Override
    public void getCallback(NudgeProvider.NudgeCallback callback) {

    }

    public interface NudgeApiService {
        @GET
        Call<String> getSurveyData(@Url String url, @Header("Authorization") String token);
    }

    public static class NudgeApiClient {
        private static Retrofit retrofit = null;

        public static Retrofit getClient() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://pointsystem.api.nudgenow.com/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }
}
