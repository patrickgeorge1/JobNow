package com.company.jobnow.activity.main.security;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.company.jobnow.R;
import com.company.jobnow.activity.main.Api.ApiInterface;
import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class SecurityService {
    public static Retrofit retrofit = null;
    public static final String BASE_URL = "https://app-jobnow.herokuapp.com/";

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }

    public static Boolean checkToken(final AppCompatActivity app) {
        SharedPreferences pref = app.getApplicationContext().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        String token = pref.getString("token", null);

        final ArrayList<Boolean> isValid = new ArrayList<>();
        isValid.add(true);

        Call<ResponseBody> call = retrofit.create(ApiInterface.class).checkToken(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
//                    Headers headers = response.headers();
//                    headers.get("Authorization")

                    String json = new Gson().toJson(response.body());
                    Type listType = new TypeToken<HashMap<String,String>>(){}.getType();
                    String status = ((HashMap<String,String>)(new Gson().fromJson(json, listType))).get("token");
                    if (!status.equals("success"))
                    {
                        isValid.remove(0);
                        isValid.add(false);
                    }

                }
                else {
                    Toast.makeText(app, "error response checkToken", Toast.LENGTH_SHORT).show();
                    isValid.remove(0);
                    isValid.add(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(app, "error failure checkToken", Toast.LENGTH_SHORT).show();
                isValid.remove(0);
                isValid.add(false);
            }
        });

        return isValid.get(0);
    }

    public static void login(final AppCompatActivity app) {
        SharedPreferences pref = app.getApplicationContext().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        editor.clear();  // TODO atentie ca sterg aici celelalte preferinte

        String username = ((EditText) app.findViewById(R.id.usernameField)).getText().toString();
        String password = ((EditText) app.findViewById(R.id.passwordField)).getText().toString();

        Retrofit retrofit = getRetrofit();
        Call<ResponseBody> call = retrofit.create(ApiInterface.class).getToken(username, password);;
//        Call<ResponseBody> call = retrofit.create(ApiInterface.class).test();;




        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Headers headers = response.headers();
                    String token = headers.get("Authorization");


                    try {
                        Toast.makeText(app, response.body().string(), Toast.LENGTH_LONG).show();

                    } catch (IOException e) {

                    }

                    editor.putString("token", token);
                }
                else {
                    Toast.makeText(app, "error response login", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(app, "error failure login", Toast.LENGTH_SHORT).show();
            }
        });
        editor.apply();
    }
}
