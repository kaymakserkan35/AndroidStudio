package com.betelgeuse.fragmentexample1;

import android.app.Fragment;
import android.os.Bundle;

// import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FragmentA extends Fragment {




    public FragmentA ( ) {
    }

    public static FragmentA newInstance () {

        FragmentA fragment = new FragmentA();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume ( ) {
        Log.e("FragmentA", "onResume: ");
        super.onResume();
    }

    @Override
    public void onPause ( ) {
        Log.e("FragmentA", "onPause: ");
        super.onPause();
    }

    @Override
    public void onDestroyView ( ) {
        Log.e("FragmentA", "onDestroyView: ");
        super.onDestroyView();
    }

    @Override
    public void onDestroy ( ) {
        Log.e("FragmentA", "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("FragmentA", "onCreate: ");
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        Log.e("FragmentA", "onCreateView: ");
        return inflater.inflate(R.layout.fragment_a, container, false);
    }
}