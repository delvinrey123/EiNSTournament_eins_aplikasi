package com.delvinstudio.einstournament.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.delvinstudio.einstournament.Fragment.main_menu_dashboard;
import com.delvinstudio.einstournament.R;
import com.delvinstudio.einstournament.pref.UserHelperClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;


public class SignUp extends AppCompatActivity {

    EditText etUsername, etPassword, etNama, etSquad, etNoWa;
    Button btnDaftar;

    FirebaseDatabase rootnode;
    DatabaseReference reference;

    //awesome validation
    AwesomeValidation awesomeValidation;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_pass);
        etNama = findViewById(R.id.et_nama);
        etSquad = findViewById(R.id.et_squad);
        etNoWa = findViewById(R.id.et_nowa);

        //initialize validation style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //add validation username
        awesomeValidation.addValidation(this, R.id.et_username,
                RegexTemplate.NOT_EMPTY,R.string.tidak_boleh_kosong);
        //add validation password
        awesomeValidation.addValidation(this, R.id.et_pass,
                ".{6,}",R.string.invalid_pass);
        //add validation nama
        awesomeValidation.addValidation(this, R.id.et_nama,
                RegexTemplate.NOT_EMPTY,R.string.tidak_boleh_kosong);
        //add validation squad/tim
        awesomeValidation.addValidation(this, R.id.et_squad,
                RegexTemplate.NOT_EMPTY,R.string.tidak_boleh_kosong);
        //add validation nowa
        awesomeValidation.addValidation(this, R.id.et_nowa,
                ".{10,}",R.string.lebih_dari_10_angka);


        //progress bar
        dialog = new ProgressDialog(this);
        dialog.setTitle("Login");
        dialog.setMessage("Silahkan Tunggu ...");
        dialog.setCanceledOnTouchOutside(true);

        btnDaftar = findViewById(R.id.btn_daftar);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()){
                dialog.show();
                rootnode = FirebaseDatabase.getInstance();
                reference = rootnode.getReference("login");

                //Get all values from EditText
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String as = ("user").toString();
                String nama = etNama.getText().toString();
                String squad = etSquad.getText().toString();
                String nowa = etNoWa.getText().toString();

                UserHelperClass helperClass = new UserHelperClass(username, password, as, nama, squad, nowa);

                reference.child(username).setValue(helperClass);
                suksesMendaftar();
                startActivity(new Intent(SignUp.this, main_menu_dashboard.class));
                dialog.dismiss();
                }
            }
        });
    }

    public void suksesMendaftar(){
        FancyToast.makeText(this, "Sukses Mendaftar !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
    }
}