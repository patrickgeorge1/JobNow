package com.company.jobnow.activity.firstTime;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import com.company.jobnow.R;
import com.company.jobnow.SingletonDatabase;
import com.company.jobnow.activity.main.MainActivity;

public class LogInFragment extends Fragment {
    View view;

    private TextView textViewEmail;
    private TextView textViewPassword;
    private Button loginButton;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_log_in, container, false);

        textViewEmail = view.findViewById(R.id.login_email);
        textViewPassword = view.findViewById(R.id.login_password);
        loginButton = view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setEnabled(false);
                authenticateUser();
                loginButton.setEnabled(true);
            }
        });

        return view;
    }

    public void authenticateUser() {
        String userEmail = textViewEmail.getText().toString();
        String userHashPassword = String.valueOf(textViewPassword.getText().toString().hashCode());
        boolean correctCredentials = SingletonDatabase.getInstance().authenticateUser(userEmail, userHashPassword);

        DrawableCompat.setTint(textViewPassword.getBackground(), getResources().getColor(android.R.color.holo_red_light));

        if (correctCredentials) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getActivity(), "asdasd", Toast.LENGTH_SHORT).show();
        }
    }
}
