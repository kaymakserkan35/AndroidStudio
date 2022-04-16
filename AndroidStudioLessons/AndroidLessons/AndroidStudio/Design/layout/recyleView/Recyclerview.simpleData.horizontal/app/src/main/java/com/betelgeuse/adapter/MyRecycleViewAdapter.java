package com.betelgeuse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyRecycleViewAdapter  extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> {

    public  class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView cardText;
        CardView myCardView;
        ImageView cardImage;

        public MyViewHolder (@NonNull View itemView) {
            super(itemView);
            cardText = itemView.findViewById(R.id.cardText);
            myCardView = itemView.findViewById(R.id.myCard);
            cardImage = itemView.findViewById(R.id.cardImage);

        }
    }


    private Context context;
    private List<String> countries;

    public MyRecycleViewAdapter (Context context,List<String> countries) {
        this.context = context;
        this.countries = countries;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.my_card,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder, int position) {
        String country = countries.get(position);
        holder.cardText.setText(country);
        holder.cardImage.setImageResource(R.drawable.turkey);
        holder.myCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Toast.makeText(context,"Seçtiğiniz ülke : "+country,Toast.LENGTH_SHORT).show();
            }
        });

        holder.cardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Toast.makeText(context,"....codes....",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount ( ) {
        return countries.size();
    }


}
