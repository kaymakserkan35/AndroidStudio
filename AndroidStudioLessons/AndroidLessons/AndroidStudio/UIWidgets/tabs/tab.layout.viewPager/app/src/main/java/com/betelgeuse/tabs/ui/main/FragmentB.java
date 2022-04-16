package com.betelgeuse.tabs.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betelgeuse.tabs.R;
import com.betelgeuse.tabs.databinding.FragmentABinding;
import com.betelgeuse.tabs.databinding.FragmentBBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentB#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentB extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private FragmentBBinding binding;

    public static FragmentB newInstance () {
        FragmentB fragment = new FragmentB();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView (
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentBBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView ( ) {
        super.onDestroyView();
        binding = null;
    }
}