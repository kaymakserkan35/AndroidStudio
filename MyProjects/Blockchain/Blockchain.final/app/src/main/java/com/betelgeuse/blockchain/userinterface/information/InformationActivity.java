package com.betelgeuse.blockchain.userinterface.information;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.betelgeuse.blockchain.R;

public class InformationActivity extends AppCompatActivity {
    InformationController informationController;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        informationController = new InformationController();
       // informationController.getData(); veriyi adaptere yolla
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        InformationAdapter adapter = new InformationAdapter(null);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener((View view) -> {
            if (view.getId()==R.id.toCurrency) {toCurrencyClicked(view);return;}
            if (view.getId()==R.id.fromCurrency) {fromCurrencyClicked(view);return;}
        });
        final ImageButton addInfoModel = findViewById(R.id.addInfoModel);
        addInfoModel.setOnClickListener((View v) -> {

        });
    }
    private void fromCurrencyClicked(View view){ }
    private void toCurrencyClicked(View view){ }
}