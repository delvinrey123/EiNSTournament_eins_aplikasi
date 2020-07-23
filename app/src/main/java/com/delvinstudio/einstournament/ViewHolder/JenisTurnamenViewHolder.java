package com.delvinstudio.einstournament.ViewHolder;

import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.delvinstudio.einstournament.Common.Common;
import com.delvinstudio.einstournament.Interface.ItemClickListener;
import com.delvinstudio.einstournament.R;

public class JenisTurnamenViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {

    public ImageView ivJenisTurnamen;
    public TextView tvJenisTurnamen;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public JenisTurnamenViewHolder(@NonNull View itemView) {
        super(itemView);

        ivJenisTurnamen = itemView.findViewById(R.id.iv_menu_turnamen_ml);
        tvJenisTurnamen = itemView.findViewById(R.id.tv_menu_turnamen_ml);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}

