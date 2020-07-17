package com.delvinstudio.einstournament.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.delvinstudio.einstournament.Common.TurnamenMl;
import com.delvinstudio.einstournament.Interface.ItemClickListener;
import com.delvinstudio.einstournament.R;
import com.delvinstudio.einstournament.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class UserListTurnamen extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference userListTurnamenMl;

    RecyclerView recyclerViewTurnamen;
    RecyclerView.LayoutManager layoutManager;

    //navigation back
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_turnamen);

        //navigation back
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //init firebase
        database = FirebaseDatabase.getInstance();
        userListTurnamenMl = database.getReference("turnamen_ml");

        //Load Menu
        recyclerViewTurnamen = (RecyclerView) findViewById(R.id.recycler_view_turnamen);
        recyclerViewTurnamen.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewTurnamen.setLayoutManager(layoutManager);

        loadMenu();

    }

    private void loadMenu() {

        FirebaseRecyclerAdapter<TurnamenMl, MenuViewHolder> adapter = new FirebaseRecyclerAdapter<TurnamenMl, MenuViewHolder>(TurnamenMl.class, R.layout.menu_item, MenuViewHolder.class, userListTurnamenMl) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, TurnamenMl model, int position) {
                viewHolder.txtListNamaTurnamen.setText(model.getNamaTurnamen());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imgListNamaTurnamen);
                final TurnamenMl clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(UserListTurnamen.this, ""+clickItem.getNamaTurnamen(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        recyclerViewTurnamen.setAdapter(adapter);
    }

    //navigation back
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
    //navigation back
    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}