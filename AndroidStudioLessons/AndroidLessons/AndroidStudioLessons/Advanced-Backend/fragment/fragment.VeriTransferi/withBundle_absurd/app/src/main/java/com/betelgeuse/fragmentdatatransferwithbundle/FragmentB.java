package com.betelgeuse.fragmentdatatransferwithbundle;

import android.os.Bundle;

//import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;


public class FragmentB extends androidx.fragment.app.Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private              String mParam1;
    private              String mParam2;

    TextView textViewB;
    Button   buttonB;

    public FragmentB ( ) {
        // Required empty public constructor
    }

    public static FragmentB newInstance (String param1, String param2) {
        FragmentB fragment = new FragmentB();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        buttonB = view.findViewById(R.id.buttonB);
        textViewB = view.findViewById(R.id.textViewB);
        return view;
    }

    @Override
    public void onStart ( ) {
        super.onStart();
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Toast.makeText(getActivity(), "button b clicked", Toast.LENGTH_SHORT).show();
                Bundle bundle = FragmentB.this.getArguments();
                String data = bundle.getString("key");
             /*   Gson gson = new Gson();
                DataObject dataObject =  gson.fromJson(data,DataObject.class); */
                if (data == null) {data = "data null geldi";}
                textViewB.setText(data);


            }
        });
    }
}