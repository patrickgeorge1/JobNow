package com.company.jobnow.activity.firstTime;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.company.jobnow.R;
import com.company.jobnow.SingletonDatabase;
import com.company.jobnow.activity.main.MainActivity;

public class LogInFragment extends Fragment {
    ViewPager viewPager;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewFeedback;
    private Button loginButton;
    private Button goToRegisterButton;

    public LogInFragment(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        editTextEmail = view.findViewById(R.id.email);
        editTextPassword = view.findViewById(R.id.password);
        textViewFeedback = view.findViewById(R.id.login_feedback);
        loginButton = view.findViewById(R.id.login_button);
        goToRegisterButton = view.findViewById(R.id.login_to_register_button);

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
        String userEmail = editTextEmail.getText().toString();
        String userHashPassword = String.valueOf(editTextPassword.getText().toString().hashCode());
        boolean success = SingletonDatabase.getInstance().authenticateUser(userEmail, userHashPassword);

        Toast.makeText(getActivity(), userEmail + " " + editTextPassword.getText().toString(), Toast.LENGTH_SHORT).show();
        if (success) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getActivity(), getString(R.string.login_unknow_error), Toast.LENGTH_SHORT).show();
            DrawableCompat.setTint(editTextEmail.getBackground(), getResources().getColor(R.color.red));
            DrawableCompat.setTint(editTextPassword.getBackground(), getResources().getColor(R.color.red));
            textViewFeedback.setText(getString(R.string.login_email_pasword_no_match));
        }
    }
}
