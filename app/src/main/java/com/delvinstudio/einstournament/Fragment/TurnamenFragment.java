package com.delvinstudio.einstournament.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.delvinstudio.einstournament.R;
import com.delvinstudio.einstournament.activity.UserListTurnamen;

public class TurnamenFragment extends Fragment {
    Button btnRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_turnamen, container, false);


        Button btnKeRecycler = (Button) view.findViewById(R.id.btn_recycler_view);

        btnKeRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), UserListTurnamen.class);
                startActivity(in);
            }
        });

        return view;
    }
}