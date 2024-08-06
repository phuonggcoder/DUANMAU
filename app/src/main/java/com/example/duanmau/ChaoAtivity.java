package com.example.duanmau;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ChaoAtivity extends AppCompatActivity {
    Animation an;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.chaoactivity);
        img = findViewById(R.id.imageView6);
        CountDownTimer tinner = new CountDownTimer(6000,6000) {
            @Override
            public void onTick(long millisUntilFinished) {
                an= AnimationUtils.loadAnimation(ChaoAtivity.this,R.anim.rotation_animation);
                img.startAnimation(an);
            }

            @Override
            public void onFinish() {
                Intent i = new Intent(ChaoAtivity.this, Login.class);
                startActivity(i);
                finish();

            }
        }.start();


    }

}