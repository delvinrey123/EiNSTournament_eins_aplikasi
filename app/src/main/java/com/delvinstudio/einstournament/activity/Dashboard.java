package com.delvinstudio.einstournament.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.delvinstudio.einstournament.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dashboard extends AppCompatActivity {


    TextView tvUserAdmin;

    Button mBtnInput, mBtnLihat, mBtnPoint, mBtnSignout;
    ProgressDialog progressDialog;

    //variabel database firebase
    FirebaseDatabase database;
    DatabaseReference login;


    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mBtnPoint = findViewById(R.id.btnPoint);
        mBtnSignout = findViewById(R.id.btn_signout);


        //init firebase
        database = FirebaseDatabase.getInstance();
        login = database.getReference("login");

        tvUserAdmin = findViewById(R.id.tv_user_admin);


        //button point
        mBtnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kePoint = new Intent(Dashboard.this, MainActivity.class);
                startActivity(kePoint);
            }
        });

    }


    public void signOut(View v){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Keluar");
        alertDialogBuilder.setIcon(R.drawable.ic_baseline_exit);
        alertDialogBuilder.setMessage("Apakah Anda Yakin Mau Keluar ?");
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent (Dashboard.this, Login.class));
                Toast.makeText(Dashboard.this, "Anda Berhasil Keluar", Toast.LENGTH_SHORT).show();
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

    //biar ga bisa pencet tombol back
    public void onBackPressed(){

    }

}