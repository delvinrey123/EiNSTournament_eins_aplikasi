package com.delvinstudio.einstournament.ViewHolder;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.delvinstudio.einstournament.R;

public class TurnamenMlViewHolder extends RecyclerView.ViewHolder {

    public TextView namaTurnamen;
    public ImageView image;
    public TurnamenMlViewHolder(@NonNull View itemView){
        super(itemView);

        namaTurnamen = itemView.findViewById(R.id.menu_namaturnamen);
        image = itemView.findViewById(R.id.menu_imageturnamen);
    }
}
