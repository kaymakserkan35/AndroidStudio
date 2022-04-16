package com.betelgeuse.blockchain.userinterface.information;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.betelgeuse.blockchain.R;
import com.betelgeuse.blockchain.userinterface.Model.InformationModel;
import com.betelgeuse.blockchain.userinterface.Model.InformationModelListListener;

import java.util.List;

public class InformationActivity extends AppCompatActivity {
    InformationController informationController;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        String userEmail = getIntent().getStringExtra("email");
        informationController = new InformationController(InformationActivity.this);
        InformationAdapter adapter = new InformationAdapter(null);
        informationController.getData(userEmail, (List<InformationModel> infos) -> {
            adapter.infos = infos;
            recyclerView.setAdapter(adapter);
        });

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