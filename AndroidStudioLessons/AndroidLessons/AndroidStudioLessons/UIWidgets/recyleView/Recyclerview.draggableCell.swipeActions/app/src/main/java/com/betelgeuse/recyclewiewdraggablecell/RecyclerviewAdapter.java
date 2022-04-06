package com.betelgeuse.recyclewiewdraggablecell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewViewHolder> {

    ArrayList<Item> list;
    public Context context;

    RecyclerviewAdapter(Context context,ArrayList<Item> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public RecyclerviewViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_custom,parent,false);
        return new RecyclerviewViewHolder(v);
    }

    @Override
    public void onBindViewHolder (@NonNull RecyclerviewViewHolder holder, int position) {
         final Item currentItem=list.get(position);
        final int itemPosition = holder.getAdapterPosition();
        holder.imageView.setImageResource(R.drawable.knight_online);
        holder.textView.setText(currentItem.getText());
        final String a=holder.textView.getText().toString();
        holder.cardView.setOnClickListener(view -> Toast.makeText(context,"Cardview clicked!"+ itemPosition
                +"\n"+a,Toast.LENGTH_SHORT).show());
    }


    public void removeItem(int position){
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,list.size());}

    public void restoreItem(Item i, int position){
        list.add(position,i);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,list.size());
    }

    public void itemAction(int position){
        Toast.makeText(context,"itemAction -->"+position+"\n"+context.getText(R.string.app_name),Toast.LENGTH_SHORT).show();
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,list.size());
    }

    public void itemMove(int position_drag,int position_target){
        notifyItemMoved(position_drag,position_target);
    }
    @Override
    public int getItemCount ( ) {
     return  list.size();
    }
}
