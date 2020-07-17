package com.delvinstudio.einstournament.activity;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.delvinstudio.einstournament.R;

public class SplashActivity extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        img = findViewById(R.id.eins);
        img.animate().alpha(2000).setDuration(0);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent dsp = new Intent(SplashActivity.this, Login.class);
                startActivity(dsp);
                finish();
            }
        }, 2000);
    }
}
