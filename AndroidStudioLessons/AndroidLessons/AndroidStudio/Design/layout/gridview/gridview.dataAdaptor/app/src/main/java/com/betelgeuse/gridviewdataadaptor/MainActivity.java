package com.betelgeuse.gridviewdataadaptor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView             gridView;
    private ArrayAdapter<String> dataArrayAdaptor;
    ArrayList<String> dataArrayList;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridview);
        gridView.setNumColumns(GridView.AUTO_FIT);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setGravity(View.TEXT_ALIGNMENT_CENTER);
        gridView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);

        dataArrayList = new ArrayList<String>();
        for (int i = 0; i < 25; i++) {
            dataArrayList.add("Data" + String.valueOf(i));
        }
        dataArrayAdaptor =
                new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, dataArrayList);
        gridView.setAdapter(dataArrayAdaptor);

        /*birşeyler denedim olmadı.... sonra yine dene*/
        gridView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange (View v, boolean hasFocus) {
                Toast.makeText(MainActivity.this, "focus : " + String.valueOf(hasFocus), Toast.LENGTH_SHORT).show();
            }
        });



    }
}