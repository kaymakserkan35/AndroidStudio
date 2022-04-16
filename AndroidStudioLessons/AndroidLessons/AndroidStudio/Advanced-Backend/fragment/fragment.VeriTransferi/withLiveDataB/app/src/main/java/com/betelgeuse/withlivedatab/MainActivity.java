package com.betelgeuse.withlivedatab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.betelgeuse.withlivedatab.ui.main.FragmentA;
import com.betelgeuse.withlivedatab.ui.main.FragmentB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
public class MainActivity extends AppCompatActivity {
    private   void   placeFragmentToLayout(int layoutNumber , Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(layoutNumber,fragment);
        ft.commit();
    }
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        placeFragmentToLayout(R.id.frameLayoutForFragmentA,new FragmentA());
        placeFragmentToLayout(R.id.frameLayoutForFragmentB,new FragmentB());
        if (savedInstanceState == null) {
        }
    }
}