package com.delvinstudio.einstournament.activity.UserListTurnamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.delvinstudio.einstournament.Model.ModelJenisTurnamenMl;
import com.delvinstudio.einstournament.R;
import com.delvinstudio.einstournament.ViewHolder.TurnamenMlViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class UserListTurnamenDetail extends AppCompatActivity {

    RecyclerView recyclerViewTurnamenMl;
    DatabaseReference ref;
    FirebaseRecyclerOptions<ModelJenisTurnamenMl> options;
    FirebaseRecyclerAdapter<ModelJenisTurnamenMl, TurnamenMlViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_turnamen_detail);

        recyclerViewTurnamenMl = findViewById(R.id.recycler_view_turnamen_detail);
        recyclerViewTurnamenMl.setHasFixedSize(true);

        ref = FirebaseDatabase.getInstance().getReference().child("turnamenMl");

        options = new FirebaseRecyclerOptions.Builder<ModelJenisTurnamenMl>()
                .setQuery(ref, ModelJenisTurnamenMl.class).build();

        adapter = new FirebaseRecyclerAdapter<ModelJenisTurnamenMl, TurnamenMlViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull TurnamenMlViewHolder holder, int position, @NonNull ModelJenisTurnamenMl model) {

                Picasso.get().load(model.getImageTurnamen()).into(holder.imageTurnamen, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                        Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

                holder.namaTurnamen.setText(model.getNamaTurnamen());
            }

            @NonNull
            @Override
            public TurnamenMlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_jenis_turnamen_item, parent, false);

                return new TurnamenMlViewHolder(view);
            }
        };

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewTurnamenMl.setLayoutManager(linearLayoutManager);
        adapter.startListening();
        recyclerViewTurnamenMl.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(adapter!=null)
            adapter.startListening();
    }

    @Override
    protected void onStop() {
        if(adapter!=null)
            adapter.stopListening();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null)
            adapter.startListening();
    }
}