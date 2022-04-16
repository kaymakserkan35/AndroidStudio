package com.betelgeuse.fragmentdatatransferwithbundle;

import android.app.Fragment;
import android.os.Bundle;

//import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class FragmentA extends androidx.fragment.app.Fragment {
    TextView textViewA ;
    Button buttonA;
    public FragmentA ( ) {
        // Required empty public constructor
    }

    public static FragmentA newInstance () {
        FragmentA fragment = new FragmentA();
        Bundle args = new Bundle();
        return fragment;
    }
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_a, container, false);
        buttonA = view.findViewById(R.id.buttonA);
        textViewA = view.findViewById(R.id.textViewA);
        return view;

    }

    @Override
    public void onStart ( ) {
        super.onStart();
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
               FragmentB fragmentb = new FragmentB();
               Bundle bundle = new Bundle();
               DataObject dataObject = new DataObject();
              /* dataObject.Name = textViewA.getText().toString();
                Gson gson = new Gson();
                String data = gson.toJson(dataObject); */
                bundle.putString("key","data");
                fragmentb.setArguments(bundle);
            }
        });
    }

    private void MakeToastMessage(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
        //getactiviti ile fragmentin bağlı olduğu context i yakalıoruz
    }
}