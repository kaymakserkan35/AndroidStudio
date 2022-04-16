package com.betelgeuse.recyclerviewcomplexdata;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewHolder> {

    public RecyclerviewAdapter (Context context, ArrayList<Item> itemArrayList) {
        this.context = context;
        this.itemArrayList = itemArrayList;
    }

    RecyclerviewHolder holder;
    Context         context;
    ArrayList<Item> itemArrayList;
    @NonNull
    @Override
    public RecyclerviewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        holder=new RecyclerviewHolder(view);

        Log.d("onCreateViewHolder-->", " executed ");
        Log.d("holder.Value-->", holder.toString());
        return holder;
    }

    @Override
    public void onBindViewHolder (@NonNull RecyclerviewHolder holder, int position) {

        Item currentItem = itemArrayList.get(position);
        int itemPosition = holder.getLayoutPosition();
        holder.imageView.setImageResource(R.drawable.image);
        holder.textViewTop.setText(currentItem.getName());
        holder.textViewBottom.setText(currentItem.getUrl());


    }

    @Override
    public int getItemCount ( ) {
     return   itemArrayList.size();
    }
}
