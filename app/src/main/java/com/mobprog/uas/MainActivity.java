package com.mobprog.uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.mobprog.uas.fragment.ListMovieFragment;
import com.mobprog.uas.fragment.PlaceFragment;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private BottomNavigationView bottomNavBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        bottomNavBar = findViewById(R.id.bottomNavBar);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameParent, new ListMovieFragment());
        fragmentTransaction.commit();
        bottomNavBar.setOnItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.movies:
                fragmentTransaction.replace(R.id.frameParent, new ListMovieFragment());
                fragmentTransaction.commit();
                return true;
            case R.id.place:
                fragmentTransaction.replace(R.id.frameParent, new PlaceFragment());
                fragmentTransaction.commit();
                return true;
        }
        return false;
    }
}