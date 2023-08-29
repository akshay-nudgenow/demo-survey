package com.nudgenow.nudgesurvey.nudgeCore.network;


import com.nudgenow.nudgesurvey.nudgeCore.model.User;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NudgeApiService {


/* initSession: Sends a POST request to create a user session, expecting a User object in response.*/
    @Headers({
            "Content-Type: application/json; charset=UTF-8"})

    @POST("users/u/create")
    Call<User> initSession(@Body RequestBody requestBody);

    /*track: Sends a POST request to track events, without expecting a specific response body.*/
    @Headers({
            "Content-Type: application/json; charset=UTF-8"})

    @POST("events/e/track")
    Call<ResponseBody> track(@Body RequestBody requestBody);



/*getRewardsData: Sends a GET request to fetch rewards data for a specific user ID,
   expecting a User.Data object in response.*/
    @GET("users/u/get/{id}")
    Call<User.Data> getRewardsData(@Path("id") String id);

    /*availableRewards: Sends a GET request to retrieve available rewards, using an authorization token in the header.*/
    @GET("rewards/r/get/available")
    Call<Object> availableRewards(@Header("authorization") String userToken);
}
