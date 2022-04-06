package com.betelgeuse.apivolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Service service;
    private Button button;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        service=Service.getInstance();
        service.setContext(MainActivity.this);
        button.setOnClickListener(v -> {
            service.getTickers();
            service.getTicker("BTC-USD");
        });
    }
}