package com.betelgeuse.scheduledexecutorservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    Button button;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView.invalidate(); // textView i UIThread den render eder
        button=findViewById(R.id.button);


    }

    @Override
    protected void onResume ( ) {
        super.onResume();
        button.setOnClickListener(v -> {
            ScheduledExecutor  scheduledExecutor = ScheduledExecutor.getInstance(MainActivity.this);
            scheduledExecutor.setContext(MainActivity.this);
            scheduledExecutor.executeForAMinute();
            //  Toast.makeText(MainActivity.this,"Main thread...",Toast.LENGTH_SHORT).show();
        });
    }
}