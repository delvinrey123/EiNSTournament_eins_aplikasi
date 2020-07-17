package com.delvinstudio.einstournament.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.delvinstudio.einstournament.R;
import com.delvinstudio.einstournament.pref.preferences;
import com.shashank.sony.fancytoastlib.FancyToast;

public class User extends AppCompatActivity {

    Button  btnUserLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btnUserLogout = findViewById(R.id.btn_user_logout);
        btnUserLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogout();
            }
        });

    }

    public void userLogout(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Keluar");
        alertDialogBuilder.setIcon(R.drawable.ic_baseline_exit);
        alertDialogBuilder.setMessage("Apakah Anda Yakin Mau Keluar ?");
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(User.this, Login.class));
                Toast.makeText(User.this, "Anda Berhasil Keluar", Toast.LENGTH_SHORT).show();
                preferences.clearData(User.this);
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();
    }

    public void onBackPressed(){

    }

    public void toastAndaBerhasilKeluar(){
        FancyToast.makeText(this, "Anda Berhasil Keluar  !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
    }
}