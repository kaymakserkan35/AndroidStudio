package com.betelgeuse.recyclerviewonclickdata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.betelgeuse.recyclerviewonclickdata.main.TickerAdapter;
import com.betelgeuse.recyclerviewonclickdata.main.TickerModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<TickerModel> seedTickerModels ( ) {
        List<TickerModel> tickerModels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TickerModel tc = new TickerModel();
            tc.setFromCurrency("A");
            tc.setToCurrency("B");
            tc.setRsiResult(String.valueOf(i));
            tc.setMacdResult(String.valueOf(i));
            tc.setBollingBandResult(String.valueOf(i));
            tickerModels.add(tc);
        }
        return tickerModels;
    }

    RecyclerView recyclerView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.items_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        TickerAdapter adapter = new TickerAdapter(seedTickerModels());
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(view -> {

                    if (view.getTag().toString().equalsIgnoreCase("toCurrency")) {
                        Log.d(view.getTag().toString(), "onClicked: ");
                        return;
                    }
                    if (view.getTag().toString().equalsIgnoreCase("rsiResult_TEXT")) {
                        Log.d(view.getTag().toString(), "onClicked: ");
                        return;
                    }
                    if (view.getTag().toString().equalsIgnoreCase("fromCurrency")) {
                        Log.d(view.getTag().toString(), "onClicked: ");
                        return;
                    }
                }
        );
    }

}