package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // User external library to create and setup a simple SplashScreen with basic image and text
        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(LoginPage.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#edb007"))
                .withFooterText("Assignment 2")
                .withLogo(R.drawable.monkey);

        config.getFooterTextView().setTextColor(Color.WHITE);
        View splashScreen = config.create();
        setContentView(splashScreen);

    }
}
