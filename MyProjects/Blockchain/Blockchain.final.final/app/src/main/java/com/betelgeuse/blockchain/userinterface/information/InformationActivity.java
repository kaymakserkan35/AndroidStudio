package com.betelgeuse.blockchain.userinterface.information;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.betelgeuse.blockchain.H;
import com.betelgeuse.blockchain.R;
import com.betelgeuse.blockchain.Testing;
import com.betelgeuse.blockchain.userinterface.Model.InformationModel;
import com.betelgeuse.blockchain.userinterface.Model.InformationModelListListener;

import java.util.List;

public class InformationActivity extends AppCompatActivity {
    InformationController informationController;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        String userEmail = getIntent().getStringExtra("email");
        informationController = new InformationController(InformationActivity.this);
        informationController.getData(userEmail);

        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final InformationAdapter adapter = new InformationAdapter(informationController.infos.getValue());
        recyclerView.setAdapter(adapter);

        new Testing(this).readTickerFromDateToNow().readTickerOfDay();


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