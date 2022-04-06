package com.betelgeuse.backgroundservice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button startService, stopService;
    private TextView text;


    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService = findViewById(R.id.startService);
        stopService = findViewById(R.id.stopService);
        text = findViewById(R.id.text);

        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                MainActivity.this.startService(new Intent(MainActivity.this, myService.class));
                text.setText("Service Started");
            }
        });
        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Log.e("TAG", "Service Stopped");
                MainActivity.this.stopService(new Intent(MainActivity.this, myService.class));
                text.setText("Service Stopped");
            }
        });


    }
}