package com.betelgeuse.fragmentdatatransferwithbundle;


import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout context ;
    private   void   placeFragmentToLayout(int layoutNumber , Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(layoutNumber,fragment);
        ft.commit();
    }
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = findViewById(R.id.context);
        context.setClickable(true);
        context.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                placeFragmentToLayout(R.id.frameLayoutForFragmentA,new FragmentA());
                placeFragmentToLayout(R.id.frameLayoutForFragmentB,FragmentB.newInstance("",""));
                return true;
            }
        });
        placeFragmentToLayout(R.id.frameLayoutForFragmentA,new FragmentA());
        placeFragmentToLayout(R.id.frameLayoutForFragmentB,FragmentB.newInstance("",""));
    }
    @Override
    protected void onStart ( ) {
        super.onStart();

    }
}