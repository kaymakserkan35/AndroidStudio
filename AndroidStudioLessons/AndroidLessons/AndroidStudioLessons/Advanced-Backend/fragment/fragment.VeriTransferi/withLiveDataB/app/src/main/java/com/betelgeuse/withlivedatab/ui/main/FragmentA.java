package com.betelgeuse.withlivedatab.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.betelgeuse.withlivedatab.Helper;
import com.betelgeuse.withlivedatab.R;

public class FragmentA extends Fragment {

    TextView textView;
    private MainViewModel viewModel;

    public static FragmentA newInstance ( ) {
        return new FragmentA();
    }

    @Nullable

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        textView = view.findViewById(R.id.textViewA);
        Helper.IsNull(textView);
        return view;

    }

    @Override
    public void onStart ( ) {
       // UserModel userModel = new ViewModelProvider(requireActivity()).get(UserModel.class);
        super.onStart();

        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count) {

                if (s!=null) { viewModel.setName(s.toString());}
            }

            @Override
            public void afterTextChanged (Editable s) {

            }
        });

    }
}