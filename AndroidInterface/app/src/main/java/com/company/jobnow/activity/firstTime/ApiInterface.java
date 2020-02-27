package com.company.jobnow.activity.firstTime;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("login")
    Call<ResponseBody> getToken(@Body HashMap<String, String> body);

    @POST("logout")
    Call<ResponseBody> logout(@Header("Authorization") String authHeader);

    @POST("register")
    Call<ResponseBody> register(@Body HashMap<String, String> body);

    @POST("checkToken")
    Call<ResponseBody> checkToken(@Header("Authorization") String authHeader);

    @GET("api/public/jobs")
    Call<ResponseBody> getJobs(@Header("Authorization") String authHeader);
}
