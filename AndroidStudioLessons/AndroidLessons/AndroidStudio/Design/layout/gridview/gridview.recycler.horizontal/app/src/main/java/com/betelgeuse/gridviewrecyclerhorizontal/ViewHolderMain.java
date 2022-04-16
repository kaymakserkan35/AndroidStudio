package com.betelgeuse.gridviewrecyclerhorizontal;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderMain  extends RecyclerView.ViewHolder{
    TextView  name;
    ImageView image;
    public ViewHolderMain (@NonNull View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name);
        image = (ImageView) itemView.findViewById(R.id.image);
    }
}
