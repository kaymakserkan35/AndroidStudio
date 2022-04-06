package com.betelgeuse.adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  RecyclerView recyclerView;
    private ArrayList<String>                           countries;
    private com.betelgeuse.adapter.MyRecycleViewAdapter myRecycleViewAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        countries=new ArrayList<String >();
        for (int i = 0; i <55 ; i++) {
            countries.add("country : "+String.valueOf(i).toString());
        }
        myRecycleViewAdapter = new com.betelgeuse.adapter.MyRecycleViewAdapter(this,countries);
        recyclerView.setAdapter(myRecycleViewAdapter);

    }
}