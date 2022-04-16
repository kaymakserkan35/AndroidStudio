package com.betelgeuse.fragmentlararasveritransferi;

import android.app.Fragment;
import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class FragmentA extends Fragment {

    TextView textViewA ;
    Button buttonA;
  private   DataObject _dataObject;
    DataTransferInterface dataTransferInterface;

    public  DataObject   getDataFromFragmentA_UI(String txt) {
        _dataObject = new DataObject();
        _dataObject.Name=txt;
        return _dataObject;
    }

    public FragmentA ( ) {
        // Required empty public constructor
    }



    public static FragmentA newInstance () {
        FragmentA fragment = new FragmentA();
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        textViewA = view.findViewById(R.id.painTextA);
        buttonA = view.findViewById(R.id.buttonB);

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
             DataObject d=   getDataFromFragmentA_UI(textViewA.getText().toString());
                dataTransferInterface = (DataTransferInterface)getActivity();
                dataTransferInterface.transferDataObjectTo_B(d);
            }
        });
        return view;
    }
}