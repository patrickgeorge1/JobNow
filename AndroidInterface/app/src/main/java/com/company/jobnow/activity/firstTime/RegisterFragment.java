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
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.company.jobnow.R;
import com.company.jobnow.SingletonDatabase;
import com.company.jobnow.activity.main.MainActivity;

public class RegisterFragment extends Fragment {
    View view;
    ViewPager viewPager;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button registerButton;
    private Button goToLoginButton;

    public RegisterFragment(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register, container, false);

        editTextEmail = view.findViewById(R.id.email);
        editTextPassword = view.findViewById(R.id.password);
        registerButton = view.findViewById(R.id.register_button);
        goToLoginButton = view.findViewById(R.id.register_to_login_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerButton.setEnabled(false);
                registerUser();
                registerButton.setEnabled(true);
            }
        });
        goToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginButton.setEnabled(false);
                viewPager.setCurrentItem(1);
                goToLoginButton.setEnabled(true);
            }
        });

        return view;
    }

    public void registerUser() {
        String userEmail = editTextEmail.getText().toString();
        String userHashPassword = String.valueOf(editTextPassword.getText().toString().hashCode());
        boolean success = SingletonDatabase.getInstance().registerUser(userEmail, userHashPassword);

        if (success) {
            Toast.makeText(getActivity(), userEmail + " " + editTextPassword.getText().toString(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getActivity(), getString(R.string.register_unknow_error), Toast.LENGTH_SHORT).show();
        }
    }
}
