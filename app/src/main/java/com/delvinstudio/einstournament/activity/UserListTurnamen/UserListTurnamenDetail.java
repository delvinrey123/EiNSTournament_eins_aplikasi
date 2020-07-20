package com.delvinstudio.einstournament.activity.UserListTurnamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.delvinstudio.einstournament.Model.ModelJenisTurnamenMl;
import com.delvinstudio.einstournament.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shreyaspatil.MaterialDialog.MaterialDialog;
import com.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import com.squareup.picasso.Picasso;

public class UserListTurnamenDetail extends AppCompatActivity {

    TextView namaTurnamen, hargaTurnamen, authorTurnamen, deskripsiTurnamen,
            tanggalTurnamen, kontakTurnamen, instagramTurnamen, namaJudulTurnamen;
    PhotoView imgTurnamen;
    ImageView ivBack;

    Button arrowBtn;
    CardView cardView, expandableView;

    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_turnamen_detail);

        //Firebase
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("listTurnamen");

        //init views
        // fab = (FloatingActionButton) findViewById(R.id.btnHubungiWa);

        namaTurnamen = (TextView) findViewById(R.id.detail_nama_turnamen);
        hargaTurnamen = (TextView) findViewById(R.id.detail_harga_turnamen);
        deskripsiTurnamen = (TextView) findViewById(R.id.detail_deskripsi_turnamen);
        tanggalTurnamen = (TextView) findViewById(R.id.detail_tanggal_turnamen);
        authorTurnamen = (TextView) findViewById(R.id.detail_author_turnamen);
        imgTurnamen = (PhotoView) findViewById(R.id.detail_img_turnamen);
        instagramTurnamen = (TextView) findViewById(R.id.detail_instagram_turnamen);
        namaJudulTurnamen = (TextView) findViewById(R.id.nama_judul_turnamen);

        kontakTurnamen = (TextView) findViewById(R.id.detail_kontak_turnamen);

        //Button back atas
        ivBack = (ImageView) findViewById(R.id.iv_back_parent);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //layout
        expandableView = findViewById(R.id.expandable_menu);
        cardView = findViewById(R.id.cardview1);
        //btn arrow
        arrowBtn = findViewById(R.id.btn_bawah);
        arrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_arrow_up_black);
                    tampilkanSnackbar(v);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    arrowBtn.setBackgroundResource(R.drawable.ic_arrow_down);
                }
            }
        });

        getListTurnamenDetail();
    }

    private void getListTurnamenDetail() {
        String turnamenKey = getIntent().getStringExtra("listTurnamenKey");
        ref.child(turnamenKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelJenisTurnamenMl model = snapshot.getValue(ModelJenisTurnamenMl.class);

                if (snapshot.exists()) {

                    namaJudulTurnamen.setText(model.getNamaTurnamen());
                    namaTurnamen.setText(model.getNamaTurnamen());
                    hargaTurnamen.setText(model.getHargaTurnamen());
                    authorTurnamen.setText(model.getAuthorTurnamen());
                    deskripsiTurnamen.setText(model.getDeskripsiTurnamen());
                    tanggalTurnamen.setText(model.getTanggalTurnamen());
                    kontakTurnamen.setText(model.getKontakTurnamen());
                    instagramTurnamen.setText(model.getInstagramTurnamen());

                    Picasso.get().load(model.getImageTurnamen()).into(imgTurnamen);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void tampilkanSnackbar(View v) {
        Snackbar snackbar = Snackbar.make(v, "Tekan 2x pada gambar untuk memperbesar", Snackbar.LENGTH_LONG);
        snackbar.setDuration(5000);
        snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE);
        snackbar.show();
    }
}