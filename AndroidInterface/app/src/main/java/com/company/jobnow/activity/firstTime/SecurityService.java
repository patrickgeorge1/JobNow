package com.company.jobnow.activity.firstTime;

import android.os.AsyncTask;

import com.company.jobnow.common.Constant;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

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

    public static Retrofit getRetrofitSecurity() {
        return retrofitSecurity;
    }

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



    public void logOutUser(String token) {
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
                        String status = (String) responseJSON.get(Constant.STATUS);
                        if (status.equals("success")) requestStatus = true;
                    } catch (Exception e) {}
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {}
        });
        return requestStatus;
    }
















    public void logInUser(String email, String password, AsynclogInUser.OnAyncTaskListener listener) {
        new AsynclogInUser(retrofitSecurity, listener).execute(email, password);
    }

    public void checkTokenIntegrity(String token, AsyncCheckTokenIntegrity.OnAyncTaskListener listener) {
        new AsyncCheckTokenIntegrity(retrofitSecurity, listener).execute(token);
    }

    public static class AsynclogInUser extends AsyncTask<String, Void, Void> {
        private Retrofit retrofit;
        private OnAyncTaskListener listener;

        public interface OnAyncTaskListener {
            void onProcessFinish(String output);
        }

        public AsynclogInUser(Retrofit retrofit, OnAyncTaskListener listener) {
            this.retrofit = retrofit;
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(String... strings) {
            HashMap<String, String> body = new HashMap<>();
            body.put("username", strings[0]);
            body.put("password", strings[1]);
            Call<ResponseBody> call = retrofit.create(ApiInterface.class).getToken(body);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        listener.onProcessFinish(response.headers().get("Authorization"));
                    } else {
                        listener.onProcessFinish("NULL");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    listener.onProcessFinish("NULL");
                }
            });
            return null;
        }
    }

    public static class AsyncCheckTokenIntegrity extends AsyncTask<String, Void, Void> {
        private Retrofit retrofit;
        private OnAyncTaskListener listener;

        public interface OnAyncTaskListener {
            void onProcessFinish(Boolean output);
        }

        public AsyncCheckTokenIntegrity(Retrofit retrofit, OnAyncTaskListener listener) {
            this.retrofit = retrofit;
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(String... strings) {
            Call<ResponseBody> call = retrofitSecurity.create(ApiInterface.class).checkToken(strings[0]);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        try {
                            Map<String, Object> responseJSON = new Gson().fromJson(response.body().string(), HashMap.class);
                            String status = (String) responseJSON.get(Constant.AUTH_TOKEN);
                            if (status.equals("success")) {
                                listener.onProcessFinish(true);
                            }
                        } catch (Exception e) {
                            listener.onProcessFinish(false);
                        }
                    } else {
                        listener.onProcessFinish(false);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    listener.onProcessFinish(false);
                }
            });
            return null;
        }
    }

}
