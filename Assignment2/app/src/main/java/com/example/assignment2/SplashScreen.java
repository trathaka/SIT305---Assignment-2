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
        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(LanguageSelection.class)
                .withSplashTimeOut(2000)
                .withBackgroundColor(Color.parseColor("#1a1b28"))
                .withHeaderText("Header")
                .withFooterText("Footer")
                .withBeforeLogoText("Before Logo Text")
                .withAfterLogoText("After Logo Text")
                .withLogo(R.mipmap.ic_launcher_round);

        config.getHeaderTextView().setTextColor(Color.WHITE);
        config.getFooterTextView().setTextColor(Color.WHITE);
        config.getBeforeLogoTextView().setTextColor(Color.WHITE);
        config.getAfterLogoTextView().setTextColor(Color.WHITE);

        View splashScreen = config.create();
        setContentView(splashScreen);

    }
}
