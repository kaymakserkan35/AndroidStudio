package com.betelgeuse.pickers;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TimePicker timePickerclock,timePickerspinner;
    DatePicker datePicker;
    NumberPicker numberPicker;
    Button buttonread;
    TextView textView,textapi;
    String a;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePickerclock=findViewById(R.id.timepicker);
        timePickerspinner=findViewById(R.id.timepickerspinner);
        datePicker=findViewById(R.id.datepicker);
        numberPicker=findViewById(R.id.numberpicker);
        buttonread=findViewById(R.id.readdate);
        buttonread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=datePicker.getMonth();
                int i1=datePicker.getDayOfMonth();
                int i2=datePicker.getYear();
                Toast.makeText(getApplicationContext(),i+"/"+i1+"/"+i2,Toast.LENGTH_SHORT).show();
            }
        });
        textapi=findViewById(R.id.textapi);
        textView=findViewById(R.id.textchanges);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(20);
        timePickerclock.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                Toast.makeText(getApplicationContext(),i+":"+i1,Toast.LENGTH_SHORT).show();
            }
        });
        timePickerspinner.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                Toast.makeText(getApplicationContext(),i+":"+i1,Toast.LENGTH_SHORT).show();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            buttonread.setVisibility(View.INVISIBLE);
            textapi.setVisibility(View.INVISIBLE);
            datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                    Toast.makeText(getApplicationContext(),i+"/"+i1+"/"+i2,Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            buttonread.setVisibility(View.VISIBLE);
            textapi.setVisibility(View.VISIBLE);
        }
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                a=getText(R.string.old)+""+i+"\n"+getText(R.string.news)+""+i1;
                textView.setText(a);
            }
        });

    }



}