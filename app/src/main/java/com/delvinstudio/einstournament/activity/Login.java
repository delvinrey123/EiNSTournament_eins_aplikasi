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
import com.delvinstudio.einstournament.Common.Common;
import com.delvinstudio.einstournament.Fragment.main_menu_dashboard;
import com.delvinstudio.einstournament.Model.User;
import com.delvinstudio.einstournament.R;
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
    EditText username, password;
    Switch janganLogout;

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
                Intent fragmentKuy = new Intent(Login.this, main_menu_dashboard.class);
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
                if (awesomeValidation.validate()) {
                    dialog.show();
                    //Init firebase
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference table_login = database.getReference("login");

                    table_login.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //cek data apakah sudah tersedia di database atau belum
                            if (dataSnapshot.child(username.getText().toString()).exists()) {
                                //Get User Information
                                User user = dataSnapshot.child(username.getText().toString()).getValue(User.class);
                                if (user.getPassword().equals(password.getText().toString())) {
                                    startActivity(new Intent(Login.this, main_menu_dashboard.class));
                                    Common.currentUser = user;
                                    toastBerhasil();
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
            }
        });
    }

    public void onBackPressed() {
    }

    //memunculkan toast error
    public void toastError() {
        FancyToast.makeText(this, "Kata Sandi Salah", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
    }

    //memunculkan toast berhasil
    public void toastBerhasil() {
        FancyToast.makeText(this, "Login Berhasil", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
    }


    // memunculkan toast data belum terdaftar
    public void toastDataBelumTerdaftar() {
        FancyToast.makeText(this, "Data Belum Terdaftar di Database Kami", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
    }
}