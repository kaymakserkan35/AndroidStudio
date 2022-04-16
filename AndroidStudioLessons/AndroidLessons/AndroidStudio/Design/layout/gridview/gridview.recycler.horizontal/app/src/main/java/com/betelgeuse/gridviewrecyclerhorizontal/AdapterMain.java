package com.betelgeuse.gridviewrecyclerhorizontal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterMain extends RecyclerView.Adapter<ViewHolderMain> {
    ViewHolderMain holder;
    private Context     context;
    private List<Model> models;

    public AdapterMain (Context context, List<Model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolderMain onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        holder = new ViewHolderMain(view);
        Log.d("onCreateViewHolder-->", " executed ");
        Log.d("holder.Value-->", holder.toString());
        return holder;
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolderMain holder, int position) {
        Model model = models.get(position);
        int modelPosition = holder.getAdapterPosition();
        //  holder.image.setImageResource(model.getImageId());
        holder.image.setImageResource(R.drawable.person3);
        holder.name.setText(model.getName());

        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Log.d(v.getTag().toString(), "listener lar bu sınıfta olmamalı!! onClicked! ");
            }
        });

         */
    }

    @Override
    public int getItemCount ( ) {
        return models.size();
    }


}
