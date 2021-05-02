package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.newsapp.fragment.HospitalsFragment;
import com.example.newsapp.fragment.NewsFragment;
import com.example.newsapp.fragment.ProfileFragment;
import com.example.newsapp.fragment.RecreationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private Animation slide_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slide_up = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
//        bottomNavigation.inflateMenu(R.menu.bottom_navigation_items);
        fragmentManager = getSupportFragmentManager();

        //inisialisasi fragment
        fragmentManager.beginTransaction().replace(R.id.main_container, new NewsFragment()).commit();

        //Memberikan listener saat menu item di bottom navigation diklik
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                View icNews = findViewById(R.id.news_menu);
                View icCulinary = findViewById(R.id.hospitals_menu);
                View icRecreation = findViewById(R.id.recreation_menu);
                View icProfile = findViewById(R.id.profile_menu);
                View[] icMenus = new View[] { icNews, icCulinary, icRecreation, icProfile };
                switch (id) {
                    case R.id.news_menu:
                        animatedItemMenu(icNews, icMenus);
                        fragment = new NewsFragment();
                        break;
                    case R.id.hospitals_menu:
                        animatedItemMenu(icCulinary, icMenus);
                        fragment = new HospitalsFragment();
                        break;
                    case R.id.recreation_menu:
                        animatedItemMenu(icRecreation, icMenus);
                        fragment = new RecreationFragment();
                        break;
                    case R.id.profile_menu:
                        animatedItemMenu(icProfile, icMenus);
                        fragment = new ProfileFragment();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                return true;
            }
        });
    }

    public void animatedItemMenu(View v, View[] icMenus) {
        v.startAnimation(slide_up);
        for (View icMenu : icMenus) {
            if (icMenu != v) {
                icMenu.clearAnimation();
            }
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.bottom_navigation_items, menu);
//        Animation slide_up = AnimationUtils.loadAnimation(this, R.anim.slide_up);
//
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                View view = findViewById(R.id.news_menu);
//                view.startAnimation(slide_up);
//            }
//        });
//        return true;
//    }
}