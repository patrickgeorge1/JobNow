package com.company.jobnow.activity.firstTime;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.company.jobnow.R;
import com.company.jobnow.activity.main.MainActivity;
import com.company.jobnow.common.Constant;

public class LogInFragment extends Fragment {
    private AppCompatActivity mainActivity;
    private ViewPager viewPager;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewFeedback;

    public LogInFragment(AppCompatActivity mainActivity, ViewPager viewPager) {
        this.mainActivity= mainActivity;
        this.viewPager = viewPager;
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
        OldSecurityService.login(mainActivity);


//        // TODO do logic here
            // TODO make with textinputLayout
//        String userEmail = editTextEmail.getText().toString();
//        String userHashPassword = String.valueOf(editTextPassword.getText().toString().hashCode());
//        boolean success = SingletonDatabase.getInstance().authenticateUser(userEmail, userHashPassword);
//
//        Toast.makeText(getActivity(), userEmail + " " + editTextPassword.getText().toString(), Toast.LENGTH_SHORT).show();
//        if (success) {
//            Intent intent = new Intent(getActivity(), MainActivity.class);
//            startActivity(intent);
//        } else {
//            Toast.makeText(getActivity(), getString(R.string.login_unknow_error), Toast.LENGTH_SHORT).show();
//            DrawableCompat.setTint(editTextEmail.getBackground(), getResources().getColor(R.color.red));
//            DrawableCompat.setTint(editTextPassword.getBackground(), getResources().getColor(R.color.red));
//            textViewFeedback.setText(getString(R.string.login_email_pasword_no_match));
//        }
    }

    Boolean isLogged() {
        SharedPreferences sharedPreferences = mainActivity.getSharedPreferences(Constant.LOGIN_PREFERENCES, Activity.MODE_PRIVATE);
        return sharedPreferences.contains("token") && OldSecurityService.checkToken(mainActivity);
    }
}
