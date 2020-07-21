package com.delvinstudio.einstournament.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.delvinstudio.einstournament.Model.User;
import com.delvinstudio.einstournament.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;


public class SignUp extends AppCompatActivity {

    EditText etUsernameSignup, etPasswordSignup, etNamaSignup, etSquadSignup, etNoWaSignup;
    Button btnDaftar;

    //awesome validation
    AwesomeValidation awesomeValidation;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //string yang akan masuk ke signup
        etUsernameSignup = findViewById(R.id.et_username_signup);
        etPasswordSignup = findViewById(R.id.et_pass_signup);
        etNamaSignup = findViewById(R.id.et_nama_signup);
        etSquadSignup = findViewById(R.id.et_squad_signup);
        etNoWaSignup = findViewById(R.id.et_nowa_signup);

        //initialize validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //add validation username
        awesomeValidation.addValidation(this, R.id.et_username_signup,
                RegexTemplate.NOT_EMPTY, R.string.tidak_boleh_kosong);
        //add validation password
        awesomeValidation.addValidation(this, R.id.et_pass_signup,
                ".{6,}", R.string.invalid_pass);
        //add validation nama
        awesomeValidation.addValidation(this, R.id.et_nama_signup,
                RegexTemplate.NOT_EMPTY, R.string.tidak_boleh_kosong);
        //add validation squad/tim
        awesomeValidation.addValidation(this, R.id.et_squad_signup,
                RegexTemplate.NOT_EMPTY, R.string.tidak_boleh_kosong);
        //add validation nowa
        awesomeValidation.addValidation(this, R.id.et_nowa_signup,
                ".{10,}", R.string.lebih_dari_10_angka);

        //progress bar
        dialog = new ProgressDialog(this);
        dialog.setTitle("Mendaftar");
        dialog.setMessage("Silahkan Tunggu ...");
        dialog.setCanceledOnTouchOutside(true);

        btnDaftar = findViewById(R.id.btn_daftar);

        //init firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_login = database.getReference("login");

        //btn daftar onclick
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    dialog.show();

                    table_login.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //Cek jika user telah terdaftar atau belum
                            if (dataSnapshot.child(etUsernameSignup.getText().toString()).exists()) {
                                dialog.dismiss();
                                telahTerdaftar();
                            }
                            else
                            {
                                dialog.dismiss();
                                User user = new User(etUsernameSignup.getText().toString(),
                                        etNamaSignup.getText().toString(),
                                        etPasswordSignup.getText().toString(),
                                        etSquadSignup.getText().toString(),
                                        etNoWaSignup.getText().toString());
                                table_login.child(etUsernameSignup.getText().toString()).setValue(user);
                                suksesMendaftar();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

    public void suksesMendaftar() {
        FancyToast.makeText(this, "Sukses Mendaftar !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
    }

    public void telahTerdaftar() {
        FancyToast.makeText(this, "Username sudah digunakan, silahkan coba lagi", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
    }
}