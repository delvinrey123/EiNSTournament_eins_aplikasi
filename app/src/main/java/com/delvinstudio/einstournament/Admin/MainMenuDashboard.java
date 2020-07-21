package com.delvinstudio.einstournament.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.delvinstudio.einstournament.Admin.Fragment.AdminDashboardFragment;
import com.delvinstudio.einstournament.Admin.Fragment.AdminTurnamenFragment;
import com.delvinstudio.einstournament.Fragment.DashboardFragment;
import com.delvinstudio.einstournament.Fragment.ExitFragment;
import com.delvinstudio.einstournament.Fragment.ProfileFragment;
import com.delvinstudio.einstournament.Fragment.TurnamenFragment;
import com.delvinstudio.einstournament.R;
import com.delvinstudio.einstournament.activity.Login;
import com.delvinstudio.einstournament.preferences.preferences;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainMenuDashboard extends AppCompatActivity {


    ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        chipNavigationBar = findViewById(R.id.bottom_nav_menu);
        chipNavigationBar.setItemSelected(R.id.bottom_nav_dashboard, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
        bottomMenu();
    }

    public void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch (i) {
                    case R.id.bottom_nav_dashboard:
                        fragment = new AdminDashboardFragment();
                        break;

                    case R.id.bottom_nav_profile:
                        fragment = new ProfileFragment();
                        break;

                    case R.id.bottom_nav_turnamen:
                        fragment = new AdminTurnamenFragment();
                        break;

                    case R.id.bottom_nav_logout:
                        fragment = new ExitFragment();
                        logout();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
    }

    private void logout() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Keluar");
        alertDialogBuilder.setIcon(R.drawable.ic_baseline_exit);
        alertDialogBuilder.setMessage("Apakah Anda Yakin Mau Keluar ?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainMenuDashboard.this, Login.class));
                preferences.clearData(MainMenuDashboard.this);
                toastBerhasilKeluar();
            }
        });
        alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void onBackPressed() {

    }

    public void toastBerhasilKeluar() {
        FancyToast.makeText(this, "Berhasil Keluar !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
    }
}
