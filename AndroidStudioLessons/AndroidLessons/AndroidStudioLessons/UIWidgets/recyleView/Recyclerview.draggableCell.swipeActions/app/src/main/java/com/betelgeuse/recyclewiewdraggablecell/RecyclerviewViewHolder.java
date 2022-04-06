package com.betelgeuse.recyclewiewdraggablecell;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerviewViewHolder extends  RecyclerView.ViewHolder{
    public ImageView imageView;
    public TextView  textView;
    public CardView  cardView;

    public RecyclerviewViewHolder (View v){
        super(v);
        imageView=v.findViewById(R.id.imageView);
        textView=v.findViewById(R.id.textView);
        cardView=v.findViewById(R.id.cardview);

    }
}
