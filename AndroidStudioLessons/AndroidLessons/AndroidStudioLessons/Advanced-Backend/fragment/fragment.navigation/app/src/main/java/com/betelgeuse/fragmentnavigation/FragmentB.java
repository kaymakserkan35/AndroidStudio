package com.betelgeuse.fragmentnavigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class FragmentB extends Fragment {

    Button   buttonB;
    TextView textViewB;

    public FragmentB ( ) {
    }


    public static FragmentB newInstance (String param1, String param2) {
        FragmentB fragment = new FragmentB();
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
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        buttonB = view.findViewById(R.id.buttonB);
        textViewB = view.findViewById(R.id.textB);
        return view;

    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonB.setOnClickListener(v -> {
            NavDirections navDirection = FragmentBDirections.actionFragmentBToFragmentA();
            Navigation.findNavController(v).navigate(navDirection);
        });
        getArgumentsFromFragA();
    }
    private void getArgumentsFromFragA ( ) {
        if (getArguments()!=null){
            String data = FragmentBArgs.fromBundle(getArguments()).getData();
            textViewB.setText(data.toString());
        }
    }
}