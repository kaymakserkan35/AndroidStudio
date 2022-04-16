package com.betelgeuse.gridviewrecyclerhorizontal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   List<Model> seedModel(){
       List<Model> models = new ArrayList<>();
       for (int i = 0; i <10 ; i++) {
           Model model = new Model("Picture : "+String.valueOf(i),i);
           models.add(model);
       }
       return  models;
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        AdapterMain adapterMain = new AdapterMain(this,seedModel());
        recyclerView.setAdapter(adapterMain);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Log.d(v.getTag().toString(), "Clicked: ");
            }
        });



    }
}