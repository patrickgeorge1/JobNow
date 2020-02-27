package com.company.jobnow.activity.firstTime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.company.jobnow.R;
import com.company.jobnow.activity.adapter.FragmentPagerAdapterFirstTime;
import com.company.jobnow.activity.main.MainActivity;
import com.company.jobnow.common.Constant;

public class FirstTimeActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);

        setUpViewPager();
        startActivity(new Intent(FirstTimeActivity.this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 2) {
            viewPager.setCurrentItem(1);
        }
    }

    public void setUpViewPager() {
        viewPager = findViewById(R.id.firstTime_viewPager);
        FragmentPagerAdapterFirstTime viewPagerAdapter = new FragmentPagerAdapterFirstTime(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new LogoFragment(this, viewPager));
        viewPagerAdapter.addFragment(new LogInFragment(this, viewPager));
        viewPagerAdapter.addFragment(new RegisterFragment(this, viewPager));
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(1); // Change to 0  to make logo apear
    }
}
