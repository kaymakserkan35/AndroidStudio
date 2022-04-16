package com.betelgeuse.fragmentsdatawithbundle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentA extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView textA;
    private static FragmentA cache=null;
    private static Object lock = new Object();

    public FragmentA ( ) {
    }
    public  Fragment sendDataThisToFragment (String key, String value, Fragment dataDestinationFragment) {
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }
        args.putString(key, value);
        dataDestinationFragment.setArguments(args);
        return dataDestinationFragment;
    }

    public static FragmentA newInstance (String param1, String param2) {
        if (cache==null){
            synchronized (lock){
                if (cache==null){
                    cache = new FragmentA();
                    Bundle args = new Bundle();
                    args.putString(ARG_PARAM1, param1);
                    args.putString(ARG_PARAM2, param2);
                    cache.setArguments(args);
                }
            }
        }
        return cache;
    }

    @Override
    public void onStop ( ) {
        super.onStop();
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
        View view= inflater.inflate(R.layout.fragment_a, container, false);
        textA=view.findViewById(R.id.textA);
        textA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count) {
                sendDataThisToFragment("key",s.toString(),FragmentB.newInstance("",""));
            }

            @Override
            public void afterTextChanged (Editable s) {

            }
        });
        return  view;
    }

}