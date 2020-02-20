package com.company.jobnow.activity.firstTime;

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


public class SecurityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);
    }

    static void redirect(final AppCompatActivity app) {
        Intent intent = new Intent(app.getApplicationContext(), MainActivity.class);
        app.startActivity(intent);
    }
}
