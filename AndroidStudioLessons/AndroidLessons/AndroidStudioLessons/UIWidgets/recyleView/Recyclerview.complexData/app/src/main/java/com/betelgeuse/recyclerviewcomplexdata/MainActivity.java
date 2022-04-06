package com.betelgeuse.recyclerviewcomplexdata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item>            itemArrayList;
    CardView                   cardView;
    RecyclerView               recyclerView;
    RecyclerView.LayoutManager recyclerLayoutManager;
    RecyclerviewAdapter        adapter;

    @Nullable
    @Override
    public View onCreateView (@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemArrayList = new ArrayList<Item>();
        for (int i = 0; i < 50; i++) {
            Item item =
                    new Item(String.valueOf(i), "Item:" + String.valueOf(i), UUID.randomUUID().toString().substring(0,5));
            itemArrayList.add(item);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setVisibility(View.VISIBLE);
        /*
      recyclerLayoutManager=new RecyclerView.LayoutManager() {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams ( ) {
               return null  ;
            }

            @Override
            public void onLayoutChildren (RecyclerView.Recycler recycler, RecyclerView.State state) {
                super.onLayoutChildren(recycler, state);
            }
        };
        recyclerView.setLayoutManager(recyclerLayoutManager);
        */
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new RecyclerviewAdapter(MainActivity.this, itemArrayList);
        recyclerView.setAdapter(adapter);
        Log.d("TAG", recyclerView.toString());


    }
}