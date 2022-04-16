package com.betelgeuse.daggerinject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMongoRepo().getAll();
        getFirebaseRepo().getAll();

    }

    public Repository getMongoRepo(){
        Repository repository;
        repository = new MongoRepo();
        return  repository;
    }

    public Repository getFirebaseRepo(){
        Repository repository;
        repository = new FirebaseRepo();
        return  repository;
    }
}