package com.delvinstudio.einstournament.activity.UserListTurnamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.delvinstudio.einstournament.Model.ModelJenisTurnamenMl;
import com.delvinstudio.einstournament.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class UserListTurnamenDetail extends AppCompatActivity {

    TextView namaTurnamen, hargaTurnamen, authorTurnamen, deskripsiTurnamen, tanggalTurnamen;
    ImageView imgTurnamen;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton fab;

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
        fab = (FloatingActionButton) findViewById(R.id.btnHubungiWa);

        namaTurnamen = (TextView) findViewById(R.id.detail_nama_turnamen);
        hargaTurnamen = (TextView) findViewById(R.id.detail_harga_turnamen);
        deskripsiTurnamen = (TextView) findViewById(R.id.detail_deskripsi_turnamen);
        tanggalTurnamen = (TextView) findViewById(R.id.detail_tanggal_turnamen);
        authorTurnamen = (TextView) findViewById(R.id.detail_author_turnamen);
        imgTurnamen = (ImageView) findViewById(R.id.detail_img_turnamen);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        getListTurnamenDetail();
    }

    private void getListTurnamenDetail() {
        String turnamenKey = getIntent().getStringExtra("listTurnamenKey");
        ref.child(turnamenKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelJenisTurnamenMl model = snapshot.getValue(ModelJenisTurnamenMl.class);

                if (snapshot.exists()) {


                    collapsingToolbarLayout.setTitle(model.getNamaTurnamen());

                    hargaTurnamen.setText(model.getHargaTurnamen());

                    deskripsiTurnamen.setText(model.getDeskripsiTurnamen());

                    tanggalTurnamen.setText(model.getTanggalTurnamen());

                    authorTurnamen.setText(model.getAuthorTurnamen());

                    Picasso.get().load(model.getImageTurnamen()).into(imgTurnamen);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}