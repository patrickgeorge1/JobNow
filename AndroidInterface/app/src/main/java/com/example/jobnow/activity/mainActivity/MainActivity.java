package com.example.jobnow.activity.mainActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.jobnow.R;
import com.example.jobnow.activity.mainActivity.chatPage.ChatFragment;
import com.example.jobnow.activity.mainActivity.helpPage.HelpFragment;
import com.example.jobnow.activity.mainActivity.jobSearchPage.JobSearchFragment;
import com.example.jobnow.activity.mainActivity.settingsPage.SettingsFragment;
import com.example.jobnow.activity.mainActivity.sharePage.ShareFragment;
import com.example.jobnow.activity.mainActivity.statsPage.StatsFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_jobSearch, R.id.nav_chat, R.id.nav_stats,
                R.id.nav_settings, R.id.nav_help, R.id.nav_share)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        ((FrameLayout) findViewById(R.id.nav_host_fragment)).removeAllViews();

        int id = item.getItemId();
        if (id == R.id.nav_jobSearch) {
            currentFragment = new JobSearchFragment();
        } else if (id == R.id.nav_chat) {
            currentFragment = new ChatFragment();
        } else if (id == R.id.nav_stats) {
            currentFragment = new StatsFragment();
        } else if (id == R.id.nav_settings) {
            currentFragment = new SettingsFragment();
        } else if (id == R.id.nav_share) {
            currentFragment = new ShareFragment();
        } else if (id == R.id.nav_help) {
            currentFragment = new HelpFragment();
        } else {
            currentFragment = null;
        }
        drawer.closeDrawer(GravityCompat.START);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, currentFragment).commit();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
        if (currentFragment instanceof JobSearchFragment) {
            finish();
            return;
        }
        currentFragment = new JobSearchFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, currentFragment).commit();
    }
}
