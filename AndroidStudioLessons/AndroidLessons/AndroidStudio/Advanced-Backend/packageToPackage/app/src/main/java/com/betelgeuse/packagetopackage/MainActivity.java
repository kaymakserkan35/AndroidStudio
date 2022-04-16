package com.betelgeuse.packagetopackage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import data.firebase.FirebaseHelper;

public class MainActivity extends AppCompatActivity {

    private FirebaseHelper firebaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}