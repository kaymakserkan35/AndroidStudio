package com.betelgeuse.architectureviewmodel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button myButton;
    private TextView textView;
    private MainActivityVM viewModel;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);


        viewModel = new ViewModelProvider(MainActivity.this).get(MainActivityVM.class);
        textView = findViewById(R.id.textView);
        myButton = findViewById(R.id.myButton);
        /*-----------------------------------------------------------------------*/
        /*
            Orientation degistiginde MainActivity yeniden instance lanır . ama ViewModel singleton gibi ama değil, sürekli aynı instance korur.
            Program tamamı ile sonlanmadan, nesne yitirilmez ve data lar kaybolmaz!!

         */
        textView.setText(viewModel.getData());
        /*-------------------------------------------------*/
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String data = viewModel.getData();
                textView.setText(data);
            }
        });
    }
}