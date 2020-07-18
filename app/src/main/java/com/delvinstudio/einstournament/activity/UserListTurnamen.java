package com.delvinstudio.einstournament.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.delvinstudio.einstournament.Model.ModelTurnamenMl;
import com.delvinstudio.einstournament.R;
import com.delvinstudio.einstournament.ViewHolder.TurnamenMlViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class UserListTurnamen extends AppCompatActivity {

    DatabaseReference ref;
    FirebaseRecyclerOptions<ModelTurnamenMl> options;
    FirebaseRecyclerAdapter<ModelTurnamenMl, TurnamenMlViewHolder> adapter;

    RecyclerView recyclerViewTurnamenMl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_turnamen);

        //navigation back
        //actionBar = getSupportActionBar();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ref = FirebaseDatabase.getInstance().getReference().child("turnamenMl");

        recyclerViewTurnamenMl = findViewById(R.id.recycler_view_turnamen);
        recyclerViewTurnamenMl.setAdapter(adapter);

        recyclerViewTurnamenMl.setHasFixedSize(true);
        recyclerViewTurnamenMl.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        options = new FirebaseRecyclerOptions.Builder<ModelTurnamenMl>().setQuery(ref, ModelTurnamenMl.class).build();
        adapter = new FirebaseRecyclerAdapter<ModelTurnamenMl, TurnamenMlViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull TurnamenMlViewHolder holder, int position, @NonNull ModelTurnamenMl model) {

                holder.namaTurnamen.setText(model.getNamaTurnamen());
                Picasso.get().load(model.getImage())
                        .into(holder.image);
            }

            @NonNull
            @Override
            public TurnamenMlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);


                return new TurnamenMlViewHolder(v);
            }
        };


    }
}