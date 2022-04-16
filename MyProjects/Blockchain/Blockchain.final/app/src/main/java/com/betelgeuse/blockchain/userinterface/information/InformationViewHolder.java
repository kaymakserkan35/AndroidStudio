package com.betelgeuse.blockchain.userinterface.information;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betelgeuse.blockchain.R;

public class InformationViewHolder extends RecyclerView.ViewHolder {
    TextView fromCurrency,toCurrency;
    public InformationViewHolder (@NonNull View information) {
        super(information);
         fromCurrency =  information.findViewById(R.id.fromCurrency);
         toCurrency = information.findViewById(R.id.toCurrency);
    }
}

