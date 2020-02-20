package com.company.jobnow.activity.main.security;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.company.jobnow.activity.main.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
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

        //SecurityService.clearPreferences(this);

        // Redirects logged and valid users
        Boolean isLogged = redirectLoggedUsers();
        if (!isLogged) Toast.makeText(this, "You have to login", Toast.LENGTH_SHORT).show();

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
                break;
            case R.id.registerButton:
                break;
        }
    }

    Boolean isLogged() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPreferences",  MODE_PRIVATE);
        if (sharedPreferences.contains("token")) Log.i("SECURITY", "stored");
        else Log.i("SECURITY", "not sstored");
        return sharedPreferences.contains("token") && SecurityService.checkToken(this);
    }


     Boolean redirectLoggedUsers() {
        Boolean ok = isLogged();
        if (ok) {
            if (ok) Log.i("SECURITY", "you are already authentificated");
            redirect(this);
        }
        else Log.i("SECURITY", "you must auth first");
        return ok;
    }

    static void redirect(final AppCompatActivity app) {
        Intent intent = new Intent(app.getApplicationContext(), MainActivity.class);
        app.startActivity(intent);
    }
}
