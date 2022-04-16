package com.betelgeuse.blockchain.userinterface.information;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betelgeuse.blockchain.R;
import com.betelgeuse.blockchain.userinterface.Model.InformationModel;

import java.util.ArrayList;
import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationViewHolder> {

    View.OnClickListener onClickListener;

    public InformationAdapter (List<InformationModel> infos) {
        if (infos!=null) this.infos = infos;
        else infos = new ArrayList<>();

    }
    List<InformationModel> infos;
    public void   setItemClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    @NonNull @Override
    public InformationViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View info = LayoutInflater.from(parent.getContext()).inflate(R.layout.information,parent,false);
         return  new InformationViewHolder(info);
    }

    @Override
    public void onBindViewHolder (@NonNull InformationViewHolder holder, int position) {
            InformationModel infoModel = infos.get(position);
            int infoModelPosition = holder.getLayoutPosition();
            holder.fromCurrency.setText(infoModel.fromCurrency);
            holder.toCurrency.setText(infoModel.toCurrency);
            holder.fromCurrency.setOnClickListener(this.onClickListener);
            holder.toCurrency.setOnClickListener(this.onClickListener);
    }

    @Override
    public int getItemCount ( ) {
        return infos.size();
    }
}
