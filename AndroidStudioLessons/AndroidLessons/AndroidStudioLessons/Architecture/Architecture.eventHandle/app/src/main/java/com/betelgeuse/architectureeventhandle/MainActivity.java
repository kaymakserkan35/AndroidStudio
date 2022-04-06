package com.betelgeuse.architectureeventhandle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.betelgeuse.architectureeventhandle.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView data ;

    public ActivityMainBinding activityMainBinding;
    public String myMethod(String name){
        Log.d("myMethod","sent string -->"+ name);
        return name.toUpperCase(Locale.ROOT);
    }
    public void myMethod(){
        Log.d("myMethod","Hola!");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      activityMainBinding =  DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
      activityMainBinding.setMainActivityInstance(this);
    }
}