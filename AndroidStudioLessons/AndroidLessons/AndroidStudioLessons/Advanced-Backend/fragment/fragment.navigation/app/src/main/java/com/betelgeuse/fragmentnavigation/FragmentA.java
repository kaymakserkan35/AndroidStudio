package com.betelgeuse.fragmentnavigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavAction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class FragmentA extends Fragment {


    Button buttonA;
    TextView textViewA;

    public FragmentA ( ) { }

    public static FragmentA newInstance (String param1, String param2) {
        FragmentA fragment = new FragmentA();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_a, container, false);
       buttonA = view.findViewById(R.id.buttonA);
       textViewA = view.findViewById(R.id.textA);
        return view;
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonA.setOnClickListener(v -> {
           changeFragmentWithArgument(v);
        });
    }

    public void changeFragmentWithNoArgument(View v){
        NavDirections action = FragmentADirections.actionFragmentAToFragmentB();
        Navigation.findNavController(v).navigate(action);
    }

    public  void  changeFragmentWithArgument(View v){
        FragmentADirections.ActionFragmentAToFragmentB action = FragmentADirections.actionFragmentAToFragmentB();
        action.setData(textViewA.getText().toString());
       Navigation.findNavController(v).navigate(action);
    }
}