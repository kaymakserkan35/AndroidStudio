package com.betelgeuse.fragmentsdatawithbundle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentB#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentB extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static FragmentB cache=null;
    private static Object lock = new Object();
    // TODO: Rename and change types of parameters
    private String   mParam1;
    private String   mParam2;
    private TextView textB;

    public FragmentB ( ) {

    }


    private String catchSentData (String key) {
        Bundle bundle = getArguments();
        return bundle.getString(key);
    }

    public static FragmentB newInstance (String param1, String param2) {
        if (cache==null){
            synchronized (lock){
                if (cache==null){
                    cache = new FragmentB();
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
        textB = view.findViewById(R.id.textB);
       /*
        String data = catchSentData("key");
        textB.setText(data);
        */
        return view;
    }

    @Override
    public void onStart ( ) {
        String data = catchSentData("key");
        textB.setText(data);
        super.onStart();
    }
}