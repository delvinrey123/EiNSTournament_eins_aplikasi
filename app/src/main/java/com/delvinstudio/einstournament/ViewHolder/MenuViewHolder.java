package com.delvinstudio.einstournament.ViewHolder;

import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.delvinstudio.einstournament.Interface.ItemClickListener;
import com.delvinstudio.einstournament.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements
        View.OnCreateContextMenuListener,
        View.OnClickListener {

    public TextView txtListNamaTurnamen;
    public ImageView imgListNamaTurnamen;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(View itemView){
        super(itemView);

        txtListNamaTurnamen = (TextView)itemView.findViewById(R.id.menu_namaturnamen);
        imgListNamaTurnamen = (ImageView)itemView.findViewById(R.id.menu_imageturnamen);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }


    //kodingan stackoverlflaw
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

    }
}
