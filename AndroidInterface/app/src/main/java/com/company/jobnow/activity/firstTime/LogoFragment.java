package com.company.jobnow.activity.firstTime;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.company.jobnow.R;
import com.company.jobnow.activity.main.MainActivity;
import com.company.jobnow.common.Constant;

public class LogoFragment extends Fragment {
    private AppCompatActivity mainActivity;
    private ViewPager viewPager;
    private SecurityService securityService;
    private SharedPreferences sharedPreferences;

    public LogoFragment(AppCompatActivity mainActivity, ViewPager viewPager) {
        this.mainActivity = mainActivity;
        this.viewPager = viewPager;

        this.securityService = SecurityService.getInstance();
        this.sharedPreferences = mainActivity.getSharedPreferences(Constant.LOGIN_PREFERENCES, Activity.MODE_PRIVATE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        String token = sharedPreferences.getString(Constant.AUTH_TOKEN, null);
        if (token == null) {
            viewPager.setCurrentItem(1);
        } else {
            securityService.checkTokenIntegrity(token, new SecurityService.AsyncCheckTokenIntegrity.OnAyncTaskListener() {
                @Override
                public void onProcessFinish(Boolean output) {
                    if (output == true) {
                        Toast.makeText(mainActivity, "Welcome Back!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(mainActivity, MainActivity.class));
                    } else {
                        Toast.makeText(mainActivity, "Your authentication token has expired!\nPlease log in again!", Toast.LENGTH_SHORT).show();
                        sharedPreferences.edit().remove(Constant.AUTH_TOKEN).apply();
                        viewPager.setCurrentItem(1);
                    }
                }
            });
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logo, container, false);
        return view;
    }
}
