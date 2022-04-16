package com.betelgeuse.fragmentsdatawithbundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int         count = 0;
    ImageView   imageView;
    FrameLayout frameLayout;

    private void count ( ) {
        Log.d("count--> ", String.valueOf(count));
        count = count + 1;
    }

    private void toggleFragmentToLayout ( ) {
        count();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (count%2==0) ft.replace(frameLayout.getId(), FragmentB.newInstance("", ""));
        else ft.replace(R.id.frameLayout,FragmentA.newInstance("",""));
        ft.commit();
    }


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.frameLayout);
        imageView = findViewById(R.id.imageView);


        imageView.setOnClickListener((v) -> {
            MainActivity.this.toggleFragmentToLayout();
        });
    }

}