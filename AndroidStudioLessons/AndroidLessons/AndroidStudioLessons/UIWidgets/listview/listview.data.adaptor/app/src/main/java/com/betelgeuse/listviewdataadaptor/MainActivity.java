package com.betelgeuse.listviewdataadaptor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> dataArrayList;
    ArrayAdapter<String> dataArrayAdapter;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        dataArrayList = new ArrayList<String>();
        for (int i = 1; i <50 ; i++) {
            dataArrayList.add( "Data : "+ String.valueOf(i));
        }
        dataArrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,android.R.id.text1,dataArrayList);
        listView.setAdapter(dataArrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"item --> Id :"+id + " Position : "+String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });
    }
}