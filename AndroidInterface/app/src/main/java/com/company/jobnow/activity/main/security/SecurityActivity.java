package com.company.jobnow.activity.main.security;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.company.jobnow.activity.main.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.company.jobnow.R;

import retrofit2.Retrofit;


public class SecurityActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Redirects logged and valid users
        redirectLoggedUsers();
        if (!isLogged()) Toast.makeText(this, "You have to login", Toast.LENGTH_SHORT).show();

        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);


        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                SecurityService.login(this);
                Boolean logged = isLogged();

                if (logged) {
                    redirectLoggedUsers();
                }
                else Toast.makeText(this, "Invalid username/ password", Toast.LENGTH_SHORT).show();
                break;
            case R.id.registerButton:
                break;
        }
    }

    Boolean isLogged() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences",  MODE_PRIVATE);
        return sharedPreferences.contains("token") && SecurityService.checkToken(this);
    }


    void redirectLoggedUsers() {
        if (isLogged()) {
            Intent intent = new Intent(SecurityActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
