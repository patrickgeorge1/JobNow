package com.company.jobnow.activity.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.company.jobnow.R;
import com.company.jobnow.activity.main.chatPage.ChatFragment;
import com.company.jobnow.activity.main.helpPage.HelpFragment;
import com.company.jobnow.activity.main.jobSearchPage.JobSearchFragment;
import com.company.jobnow.activity.main.settingsPage.SettingsFragment;
import com.company.jobnow.activity.main.sharePage.ShareFragment;
import com.company.jobnow.activity.main.statsPage.StatsFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private FragmentTransaction fragmentTransaction;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_jobSearch, R.id.nav_chat, R.id.nav_stats, R.id.nav_settings, R.id.nav_help, R.id.nav_share)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        currentFragment = new JobSearchFragment();
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
