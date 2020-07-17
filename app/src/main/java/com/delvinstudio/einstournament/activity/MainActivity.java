package com.delvinstudio.einstournament.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.delvinstudio.einstournament.R;

public class MainActivity extends AppCompatActivity {
    // deklarasi tombol
    private ImageView mIvPubg, mIvFf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisiasi tombol
        ImageView mIvPubg = findViewById(R.id.ivPubg);
        ImageView mIvFf = findViewById(R.id.ivFf);

        //panggil tombol
        mIvPubg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Pubg.class);
                startActivity(intent);
            }
        });

        mIvFf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Ff.class);
                startActivity(intent);
            }
        });
    }
}