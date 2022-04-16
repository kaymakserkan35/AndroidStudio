package com.betelgeuse.withlivedatab.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.betelgeuse.withlivedatab.Helper;
import com.betelgeuse.withlivedatab.R;


public class FragmentB extends Fragment {
    TextView      textView;
    MainViewModel viewModel;

    public FragmentB ( ) {
        // Required empty public constructor
    }

    public static FragmentB newInstance ( ) {
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
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        textView = view.findViewById(R.id.textViewB);  Helper.IsNull(textView);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
       // FragmentActivity fragmentActivity = requireActivity();
       // Log.e("TAG", fragmentActivity.getClass().getSimpleName());
        viewModel.getName().observe(getViewLifecycleOwner(), new Observer<String>() {

            @Override
            public void onChanged (String s) {
              //  Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();
                textView.setText(s);
            }
        });
        return view;
    }


}