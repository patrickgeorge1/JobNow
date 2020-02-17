package com.company.jobnow.activity.firstTime;

import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.company.jobnow.R;
import com.company.jobnow.common.Constant;

public class FirstTimeActivity extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;

    LogInFragment logInFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        logInFragment = new LogInFragment();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((FrameLayout) findViewById(R.id.firstTime_host_fragment)).removeAllViews();
                fragmentTransaction.replace(R.id.firstTime_host_fragment, logInFragment);
                fragmentTransaction.commit();
            }
        }, Constant.LOGO_DELAY_MS);
    }
}
