package com.nudgenow.nudgesurvey.repository;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiService {

    String BASE_URL = "https://pointsystem.api.nudgenow.com/api/v1";

    @GET("/form/enduser/survey/{id}/page/{pageNo}")
    Call<ResponseBody> getPage(
            @Path("id") String id,
            @Path("pageNo") int pageNo,
            @Header("authorization") String token
    );

    @GET("/form/enduser/survey/{surveyId}/skip/{userStatId}")
    Call<ResponseBody> skipPage(
            @Path("surveyId") String surveyId,
            @Path("userStatId") String userStatId,
            @Header("authorization") String token
    );

    @POST("/form/enduser/survey/{surveyId}/page/{pageId}/{userStatId}")
    Call<ResponseBody> answerPage(
            @Path("surveyId") String surveyId,
            @Path("pageId") int pageId,
            @Path("userStatId") String userStatId,
            @Header("authorization") String token,
            @Body RequestBody pageData
    );
}
