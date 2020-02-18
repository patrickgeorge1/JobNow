package com.company.jobnow.activity.firstTime;

import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.company.jobnow.R;
import com.company.jobnow.activity.firstTime.adapter.FragmentPagerAdapterFirstTime;
import com.company.jobnow.common.Constant;

public class FirstTimeActivity extends AppCompatActivity {
    ViewPager viewPager;
    FragmentPagerAdapterFirstTime viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);

        viewPager = findViewById(R.id.firstTime_viewPager);
        viewPagerAdapter = new FragmentPagerAdapterFirstTime(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new LogoFragment());
        viewPagerAdapter.addFragment(new LogInFragment(viewPager));
        viewPagerAdapter.addFragment(new RegisterFragment(viewPager));
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(1);
            }
        }, Constant.LOGO_DELAY_MS);
    }
}
