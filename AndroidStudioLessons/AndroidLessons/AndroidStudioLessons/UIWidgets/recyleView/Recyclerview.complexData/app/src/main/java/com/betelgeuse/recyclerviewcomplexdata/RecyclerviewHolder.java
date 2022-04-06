package com.betelgeuse.recyclerviewcomplexdata;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerviewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView textViewTop,textViewBottom;
    public ImageButton imageButton;
    public RecyclerviewHolder (@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.imageView);
        textViewTop=itemView.findViewById(R.id.textViewTop);
        textViewBottom=itemView.findViewById(R.id.textViewBottom);
        imageButton=itemView.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Toast.makeText(v.getContext(), "codes...",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
