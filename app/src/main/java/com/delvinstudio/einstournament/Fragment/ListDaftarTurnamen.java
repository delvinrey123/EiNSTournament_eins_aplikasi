package com.delvinstudio.einstournament.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.delvinstudio.einstournament.Interface.ItemClickListener;
import com.delvinstudio.einstournament.Model.List_Turnamen;
import com.delvinstudio.einstournament.R;
import com.delvinstudio.einstournament.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.squareup.picasso.Picasso;


public class ListDaftarTurnamen extends Fragment {

    FirebaseDatabase database;
    DatabaseReference list_turnamen;

    RecyclerView recycler_list_turnamen;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //attach on create
        View view = inflater.inflate(R.layout.fragment_list_daftar_turnamen, container, false);

        //init firebase
        database = FirebaseDatabase.getInstance();
        list_turnamen = database.getReference("list_turnamen");

        //load list turnamen
        recycler_list_turnamen = (RecyclerView) view.findViewById(R.id.recycler_view_list_daftar_turnamen);
        recycler_list_turnamen.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recycler_list_turnamen.setLayoutManager(layoutManager);

        loadListTurnamen();

        // Inflate the layout for this fragment
        return view;

    }

    private void loadListTurnamen() {
        FirebaseRecyclerAdapter<List_Turnamen, MenuViewHolder> adapter = new FirebaseRecyclerAdapter<List_Turnamen, MenuViewHolder>(List_Turnamen.class, R.layout.menu_item, MenuViewHolder.class, list_turnamen) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, List_Turnamen list_turnamen, int position) {
                viewHolder.txtListNamaTurnamen.setText(list_turnamen.getNamaTurnamen());
                Picasso.with(getContext()).load(list_turnamen.getImage())
                        .into(viewHolder.imgListNamaTurnamen);
                List_Turnamen clickItem = list_turnamen;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Toast.makeText(this, ""+clickItem.getNamaTurnamen(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        recycler_list_turnamen.setAdapter(adapter);
    }

}