package com.betelgeuse.fragmentlararasveritransferi;

import android.app.Fragment;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentB#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentB extends Fragment {
    TextView              textViewB;
    Button                buttonB;

    public FragmentB ( ) {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentB newInstance ( ) {
        FragmentB fragment = new FragmentB();
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
        textViewB = view.findViewById(R.id.painTextB);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

            }
        });
        return view;
    }
}