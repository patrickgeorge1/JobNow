package com.company.jobnow.activity.firstTime;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.company.jobnow.R;
import com.company.jobnow.common.Constant;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterFragment extends Fragment {
    private static final String TAG = "RegisterFragment";
    private AppCompatActivity mainActivity;
    private ViewPager viewPager;
    private SecurityService securityService;
    private SharedPreferences sharedPreferences;

    private TextInputLayout inputEmail;
    private TextInputLayout inputFullName;
    private TextInputLayout inputPassword;

    public RegisterFragment(AppCompatActivity mainActivity, ViewPager viewPager) {
        this.mainActivity = mainActivity;
        this.viewPager = viewPager;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

        securityService = SecurityService.getInstance();
        sharedPreferences = mainActivity.getSharedPreferences(Constant.LOGIN_PREFERENCES, Activity.MODE_PRIVATE);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        inputFullName = view.findViewById(R.id.layout_fullName);
        inputEmail = view.findViewById(R.id.layout_email);
        inputPassword = view.findViewById(R.id.layout_password);

        final Button registerButton = view.findViewById(R.id.register_button);
        final Button goToLoginButton = view.findViewById(R.id.register_to_login_button);
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
        String fullName = inputFullName.getEditText().getText().toString();
        String email = inputEmail.getEditText().getText().toString();
        String password = inputPassword.getEditText().getText().toString();
        securityService.registerUser(fullName, email, password);
    }
//        boolean success = SingletonDatabase.getInstance().registerUser(fullName, email, password);

//        // TODO Connect with OldSecurityService
//
//        if (success) {
//            Intent intent = new Intent(getActivity(), MainActivity.class);
//            startActivity(intent);
//        } else {
//            Toast.makeText(getActivity(), getString(R.string.register_unknow_error), Toast.LENGTH_SHORT).show();
//        }
//    }
}
