package com.company.jobnow.activity.firstTime;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.company.jobnow.common.Constant;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class SecurityService {
    private static final SecurityService ourInstance = new SecurityService();
    private static final Retrofit retrofitSecurity = new Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .build();

    private Boolean requestStatus = false;
    private String token;

    public static SecurityService getInstance() {
        return ourInstance;
    }

    private SecurityService() { }


    public boolean isTokenValid(String token) {
        requestStatus = false;
        Call<ResponseBody> call = retrofitSecurity.create(ApiInterface.class).checkToken(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Map<String, Object> responseJSON = new Gson().fromJson(response.body().string(), HashMap.class);
                        String status = (String) responseJSON.get(Constant.AUTH_TOKEN);
                        if (status.equals("success")) requestStatus = true;
                    } catch (Exception e) {}
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {}
        });
        return requestStatus;
    }

    public String logInUser(String email, String password) {
        token = null;
        HashMap<String, String> body = new HashMap<>();
        body.put("username", email);
        body.put("password", password);
        Call<ResponseBody> call = retrofitSecurity.create(ApiInterface.class).getToken(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) { token = response.headers().get("Authorization");}
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {}
        });
        return token;
    }

    public void logOutUser(String tokem) {
        Call<ResponseBody> call = retrofitSecurity.create(ApiInterface.class).logout(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {}
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {}
        });
    }

    public boolean registerUser(String fullName, String email, String password) {
        requestStatus = false;
        HashMap<String, String> body = new HashMap<>();
        body.put("username", email);
        body.put("password", password);
        body.put("real_name", fullName);
        Call<ResponseBody> call = retrofitSecurity.create(ApiInterface.class).register(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Map<String, Object> responseJSON = new Gson().fromJson(response.body().string(), HashMap.class);
                        String status = (String) responseJSON.get(Constant.AUTH_TOKEN);
                        if (status.equals("success")) requestStatus = true;
                    } catch (Exception e) {}
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {}
        });
        return requestStatus;
    }

}
