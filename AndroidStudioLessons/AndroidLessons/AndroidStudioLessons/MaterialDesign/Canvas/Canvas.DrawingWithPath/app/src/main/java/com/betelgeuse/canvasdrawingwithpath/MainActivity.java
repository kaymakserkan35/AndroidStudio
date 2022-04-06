package com.betelgeuse.canvasdrawingwithpath;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    CustomViewDrawingWithPaths   customView;
    Button clear ;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clear =findViewById(R.id.clear);
        customView=findViewById(R.id.customView);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                customView.ClearDrawedFrame();
            }
        });
    }
}