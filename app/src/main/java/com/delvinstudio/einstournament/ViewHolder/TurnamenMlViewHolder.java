package com.delvinstudio.einstournament.ViewHolder;

import android.media.Image;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.delvinstudio.einstournament.Common.Common;
import com.delvinstudio.einstournament.Interface.ItemClickListener;
import com.delvinstudio.einstournament.R;

public class TurnamenMlViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener,
        View.OnCreateContextMenuListener{

    public TextView namaTurnamen;
    public ImageView imageTurnamen;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public TurnamenMlViewHolder(@NonNull View itemView) {
        super(itemView);

        //nanti untuk jenisturnamen
        //namaTurnamen = (TextView)itemView.findViewById(R.id.menu_namaturnamen);
       //imageTurnamen = (ImageView)itemView.findViewById(R.id.menu_imageturnamen);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select the Action");
        menu.add(0,0,getAdapterPosition(), Common.UPDATE);
        menu.add(0,1,getAdapterPosition(), Common.DELETE);

    }
}
