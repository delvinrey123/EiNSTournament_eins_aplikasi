package com.delvinstudio.einstournament.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.delvinstudio.einstournament.Admin.MainMenuDashboard;
import com.delvinstudio.einstournament.Common.Common;
import com.delvinstudio.einstournament.Fragment.main_menu_dashboard;
import com.delvinstudio.einstournament.Model.User;
import com.delvinstudio.einstournament.R;
import com.delvinstudio.einstournament.preferences.preferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;

public class Login extends AppCompatActivity {

    //Login, Signup, SharedPreferences
    AwesomeValidation awesomeValidation;

    Button btnLogin, btnSignUp, keFragment;
    Switch janganLogout;
    EditText username, password;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Login,
        btnLogin = findViewById(R.id.btn_login);


        //signup
        btnSignUp = findViewById(R.id.btn_signup);

        //Edit Text username et password
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_pass);

        //Debug Fragment User
        keFragment = findViewById(R.id.ke_fragment);
        keFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fragmentKuy = new Intent(Login.this, MainMenuDashboard.class);
                startActivity(fragmentKuy);
            }
        });

        //Switch jangan logout
        janganLogout = findViewById(R.id.jangan_logout);

        //initialize validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //add validation name
        awesomeValidation.addValidation(this, R.id.et_username,
                RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        //add validation password
        awesomeValidation.addValidation(this, R.id.et_pass,
                ".{6,}", R.string.invalid_pass);

        //Progress Dialog
        dialog = new ProgressDialog(this);
        dialog.setTitle("Login");
        dialog.setMessage("Silahkan Tunggu ...");
        dialog.setCanceledOnTouchOutside(true);

        //btn Sign up onclick
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent daftar = new Intent(Login.this, SignUp.class);
                startActivity(daftar);
            }
        });

        //btn login onclick
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (
                        awesomeValidation.validate()){
                    dialog.show();
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("login").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String input1 = username.getText().toString();
                            String input2 = password.getText().toString();

                            if (dataSnapshot.child(input1).exists()) {
                                if (dataSnapshot.child(input1).child("password").getValue(String.class).equals(input2)) {
                                    if (janganLogout.isChecked()) {
                                        if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("admin")) {
                                            preferences.setDataLogin(Login.this, true);
                                            preferences.setDataAs(Login.this, "admin");
                                            startActivity(new Intent(Login.this, MainMenuDashboard.class));

                                        } else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("member")) {
                                            preferences.setDataLogin(Login.this, true);
                                            preferences.setDataAs(Login.this, "member");
                                            startActivity(new Intent(Login.this, main_menu_dashboard.class));
                                        }

                                    } else {
                                        if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("admin")) {
                                            preferences.setDataLogin(Login.this, false);
                                            startActivity(new Intent(Login.this, MainMenuDashboard.class));

                                        } else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("member")) {
                                            preferences.setDataLogin(Login.this, false);
                                            startActivity(new Intent(Login.this, main_menu_dashboard.class));
                                        }
                                    }
                                } else {
                                    toastError();
                                }

                            } else {
                                toastDataBelumTerdaftar();
                            }
                            dialog.dismiss();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else
                {

                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (preferences.getDataLogin(this)) {
            if (preferences.getDataAs(this).equals("admin")) {
                startActivity(new Intent(Login.this, Dashboard.class));
                finish();
            } else if (preferences.getDataAs(this).equals("user"))
                startActivity(new Intent(Login.this, User.class));
            finish();
        }
    }
    public void onBackPressed(){
    }

    public void toastError(){
        FancyToast.makeText(this,"Kata Sandi Salah !", FancyToast.LENGTH_LONG, FancyToast.ERROR,false).show();
    }

    public void toastBerhasil(){
        FancyToast.makeText(this, "Success Toast !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
    }

    public void toastDataBelumTerdaftar(){
        FancyToast.makeText(this,"Data Belum Terdaftar !", FancyToast.LENGTH_LONG, FancyToast.ERROR,false).show();
    }
}