package com.delvinstudio.einstournament.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.delvinstudio.einstournament.R;

public class Ff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ff);

        //inisiasi tombol
        ImageView mIv1 = findViewById(R.id.iv1);

        //panggil tombol
        mIv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ff.this, PilihanFf.class);
                startActivity(intent);
            }
        });
    }
}
