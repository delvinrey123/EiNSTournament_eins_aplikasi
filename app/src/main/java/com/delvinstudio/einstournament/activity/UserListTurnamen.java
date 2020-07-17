package com.delvinstudio.einstournament.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.delvinstudio.einstournament.Model.DataSetTurnamen;
import com.delvinstudio.einstournament.R;
import com.delvinstudio.einstournament.ViewHolder.FirebaseMLTurnamenViewHolder;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserListTurnamen extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference userListTurnamenMl;

    private RecyclerView recyclerViewTurnamen;
    private ArrayList<DataSetTurnamen> arrayList;
    private FirebaseRecyclerOptions<DataSetTurnamen> options;
    private FirebaseRecyclerOptions<DataSetTurnamen, FirebaseMLTurnamenViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_turnamen);

        //navigation back
        //actionBar = getSupportActionBar();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //init firebase
        database = FirebaseDatabase.getInstance();
        userListTurnamenMl = database.getReference("turnamen_ml");

        //Load Menu
        recyclerViewTurnamen = findViewById(R.id.recycler_view_turnamen);
        recyclerViewTurnamen.setHasFixedSize(true);
        recyclerViewTurnamen.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<DataSetTurnamen>();

        options = new FirebaseRecyclerOptions.Builder<DataSetTurnamen>().setQuery()



    }

}