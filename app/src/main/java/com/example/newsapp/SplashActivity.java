package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    Animation app_splash, btt;
    ImageView AppLogo;
    TextView TextApp;
    TextView AppJargon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);
        AppLogo = findViewById(R.id.logoApp);
        TextApp = findViewById(R.id.textApp);
        AppJargon = findViewById(R.id.textJargon);

        AppLogo.startAnimation(app_splash);
        TextApp.startAnimation(app_splash);
        AppJargon.startAnimation(btt);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        },3000);
    }
}