package com.betelgeuse.recyclerviewonclickdata.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.betelgeuse.recyclerviewonclickdata.R;

import java.util.List;

public class TickerAdapter extends RecyclerView.Adapter<TickerAdapter.TickerViewHolder> {

    View.OnClickListener onClickListenerdeleteTickerModel;

    public void deleteTickerModelsetOnClickListener (View.OnClickListener onClickListener) {
        this.onClickListener = onClickListenerdeleteTickerModel;
    }

    public TickerAdapter (List<TickerModel> tickerModels) {
        this.tickerModels = tickerModels;
    }

    List<TickerModel> tickerModels;

    @NonNull
    @Override
    public TickerViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticker, parent, false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                View view = v;
                String tag = v.getTag().toString();
                ViewParent viewParent = v.getParent();
                View root = v.getRootView();

            }
        });
        return new TickerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder (@NonNull TickerViewHolder holder, int position) {
        TickerModel currentModel = tickerModels.get(position);
        int itemPosition = holder.getLayoutPosition();
        holder.fromCurrency_TEXT.setText(currentModel.getFromCurrency());
        holder.toCurrency_TEXT.setText(currentModel.getToCurrency());
        holder.rsiResult_TEXT.setText(currentModel.getRsiResult());
        /*---------------------------------------------------------*/
       
        holder.deleteTickerModel.setOnClickListener(onClickListenerdeleteTickerModel);


    }

    @Override
    public int getItemCount ( ) {
        return tickerModels.size();
    }

    protected static class TickerViewHolder extends RecyclerView.ViewHolder {
        protected TextView fromCurrency_TEXT, toCurrency_TEXT, rsiResult_TEXT, bollingBand_TEXT,
                macd_TEXT;
        protected Button deleteTickerModel;

        public TickerViewHolder (@NonNull View itemView) {
            super(itemView);
            fromCurrency_TEXT = itemView.findViewById(R.id.fromCurrency);
            toCurrency_TEXT = itemView.findViewById(R.id.toCurrency);
            rsiResult_TEXT = itemView.findViewById(R.id.rsi_result);
            bollingBand_TEXT = itemView.findViewById(R.id.bollingBand_result);
            macd_TEXT = itemView.findViewById(R.id.macd_result);
            deleteTickerModel = itemView.findViewById(R.id.delete_tickerModel);
        }
    }
}
