package com.company.jobnow.activity.firstTime;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.company.jobnow.R;
import com.company.jobnow.activity.main.MainActivity;
import com.company.jobnow.common.Constant;

public class LogInFragment extends Fragment {
    private static final String TAG = "LogInFragment";
    private AppCompatActivity mainActivity;
    private ViewPager viewPager;
    private SecurityService securityService;
    private SharedPreferences sharedPreferences;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewFeedback;

    public LogInFragment(AppCompatActivity mainActivity, ViewPager viewPager) {
        this.mainActivity= mainActivity;
        this.viewPager = viewPager;
        securityService = SecurityService.getInstance();
        sharedPreferences = mainActivity.getSharedPreferences(Constant.LOGIN_PREFERENCES, Activity.MODE_PRIVATE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

        if (isLogged()) {
            Intent intent = new Intent(mainActivity, MainActivity.class);
            mainActivity.startActivity(intent);
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        editTextEmail = view.findViewById(R.id.email);
        editTextPassword = view.findViewById(R.id.password);
        textViewFeedback = view.findViewById(R.id.login_feedback);

        final Button loginButton = view.findViewById(R.id.login_button);
        final Button goToRegisterButton = view.findViewById(R.id.login_to_register_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setEnabled(false);
                authenticateUser();
                loginButton.setEnabled(true);
            }
        });
        goToRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterButton.setEnabled(false);
                viewPager.setCurrentItem(2);
                goToRegisterButton.setEnabled(true);
            }
        });

        return view;
    }

    public void authenticateUser() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        
        
        String token = securityService.logInUser(email, password);
        Log.e(TAG, "authenticateUser: " + token );

        if (token != null) {
            sharedPreferences.edit().putString(Constant.AUTH_TOKEN, token).apply();
            Intent intent = new Intent(mainActivity, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(mainActivity, "token is null", Toast.LENGTH_SHORT).show();
        }
    }


    Boolean isLogged() {
        return sharedPreferences.contains(Constant.AUTH_TOKEN) && securityService.isTokenValid(sharedPreferences.getString(Constant.AUTH_TOKEN, null));
    }
}
