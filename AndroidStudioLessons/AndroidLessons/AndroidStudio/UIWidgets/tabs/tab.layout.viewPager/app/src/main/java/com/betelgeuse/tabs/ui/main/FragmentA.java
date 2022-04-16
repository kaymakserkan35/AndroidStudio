package com.betelgeuse.tabs.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.betelgeuse.tabs.databinding.FragmentABinding;

public class FragmentA extends Fragment {

    private FragmentABinding binding;

    public static FragmentA newInstance () {
        FragmentA fragment = new FragmentA();
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

        binding = FragmentABinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView ( ) {
        super.onDestroyView();
        binding = null;
    }
}