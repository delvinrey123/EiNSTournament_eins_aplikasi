package com.delvinstudio.einstournament.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.delvinstudio.einstournament.Common.Common;
import com.delvinstudio.einstournament.Interface.ItemClickListener;
import com.delvinstudio.einstournament.Model.ModelJenisTurnamenMl;
import com.delvinstudio.einstournament.R;
import com.delvinstudio.einstournament.ViewHolder.JenisTurnamenViewHolder;
import com.delvinstudio.einstournament.activity.UserListTurnamen.UserListTurnamenDetail;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TurnamenFragment extends Fragment {
    RecyclerView recyclerViewJenisTurnamen;

    FirebaseRecyclerOptions<ModelJenisTurnamenMl> options;
    FirebaseRecyclerAdapter<ModelJenisTurnamenMl, JenisTurnamenViewHolder> adapter;
    DatabaseReference DataRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_turnamen, container, false);

        DataRef = FirebaseDatabase.getInstance().getReference().child("listTurnamen");

        recyclerViewJenisTurnamen = view.findViewById(R.id.recycler_view_list_turnamen);
        recyclerViewJenisTurnamen.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewJenisTurnamen.setHasFixedSize(true);

        loadData();

        return view;
    }

    private void loadData() {

        options = new FirebaseRecyclerOptions.Builder<ModelJenisTurnamenMl>().setQuery(DataRef, ModelJenisTurnamenMl.class).build();
        adapter = new FirebaseRecyclerAdapter<ModelJenisTurnamenMl, JenisTurnamenViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull JenisTurnamenViewHolder holder, int position, @NonNull ModelJenisTurnamenMl model) {
                holder.tvJenisTurnamen.setText(model.getNamaTurnamen());
                Picasso.get().load(model.getImageTurnamen()).into(holder.ivJenisTurnamen);

                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent detailTurnamen = new Intent(getActivity(), UserListTurnamenDetail.class);
                        detailTurnamen.putExtra("listTurnamenKey", getRef(position).getKey());

                        startActivity(detailTurnamen);
                    }
                });

            }

            @NonNull
            @Override
            public JenisTurnamenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_turnamen_ml_item, parent, false);

                return new JenisTurnamenViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerViewJenisTurnamen.setAdapter(adapter);
    }

    public void showDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("Silahkan Tunggu");
        builder.setMessage("Sedang memuat, silahkan tunggu");
        builder.setBackground(getResources().getDrawable(R.drawable.alert_dialog_bg, null));
        builder.show();
    }
}