package com.betelgeuse.adaptivetablelayout;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cleveroad.adaptivetablelayout.AdaptiveTableLayout;

public class MainActivity extends AppCompatActivity {
    AdaptiveTableLayout mTableAdapter ;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTableAdapter = findViewById(R.id.tableLayout);
        //mTableAdapter.set


    }
}