package com.betelgeuse.mpandroidchartlinechart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Entry> entries;
    LineData         lineData;
    LineDataSet      lineDataSet;
    LineChart        lineChart;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lineChart = findViewById(R.id.lineChart);
        entries = drawCustom();

        lineDataSet = new LineDataSet(entries, "myLabel");
        lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.setBorderColor(Color.RED);
        lineChart.setDrawGridBackground(true);
        lineChart.setDrawMarkers(true);



    }

    private ArrayList<Entry> drawCustom ( ) {
        ArrayList<Entry> entries = new ArrayList<>();

        for (double t = 0; t <= 6.28; t = t + 0.01) {
            double x = Math.cos(t);
            double x_abs = Math.abs(x);
            double y = Math.sin(t) + Math.pow(x_abs, 1 / 2);
            entries.add(new Entry((float) x, (float) y*-1));
        }
        for (double t = 0; t <= 6.28; t = t + 0.01) {
            double x = Math.cos(t);
            double x_abs = Math.abs(x);
            double y = Math.sin(t) + Math.pow(x_abs, 1 / 2);
            entries.add(new Entry((float) x, (float) y));
        }
        return entries;
    }

}