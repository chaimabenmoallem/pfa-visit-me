package com.example.visitme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class IntroductoryActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT = 5500;

    LottieAnimationView lottieAnimationView ;
    ImageView nomApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        lottieAnimationView = findViewById(R.id.lottie);
        nomApp = findViewById(R.id.nom);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        nomApp.animate().translationY(1400).setDuration(1000).setStartDelay(4000);

        //passer au second screen

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run ()
            {
                Intent intent = new Intent(IntroductoryActivity.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);



    }


}