package com.betelgeuse.spinneradaptor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayList<String> dataArrayList;
    private ArrayAdapter<String> dataArrayAdaptor;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        dataArrayList = new ArrayList<String>();
        for (int i = 0; i < 10 ; i++) {
            dataArrayList.add("Data : "+String.valueOf(i));
        }
        dataArrayAdaptor = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,android.R.id.text1,dataArrayList);
        spinner.setAdapter(dataArrayAdaptor);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"data --> "+ parent.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected (AdapterView<?> parent) {

            }
        });
    }
}