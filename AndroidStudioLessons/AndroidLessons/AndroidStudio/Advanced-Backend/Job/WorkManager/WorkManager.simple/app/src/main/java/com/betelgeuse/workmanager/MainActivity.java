 package com.betelgeuse.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

 public class MainActivity extends AppCompatActivity {
     private  static  String databaseName = "com.betelgeuse.workmanager";
     TextView textView;
     Button   workButton;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        workButton = findViewById(R.id.workButton);

        workButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                work();
                SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences(databaseName, Context.MODE_PRIVATE);
                int mySavedNumber  = sharedPreferences.getInt("data_id",0);
                textView.setText(String.valueOf(mySavedNumber));
            }
        });

    }

    private void work(){
        Data data = new Data.Builder().putInt("data_key",1).build();

        Constraints constraints = new Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .build();

        WorkRequest workRequest = new OneTimeWorkRequest.Builder(MyDatabaseWorker.class)
                .setConstraints(constraints)
                .setInputData(data)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build();
        WorkManager.getInstance(MainActivity.this).enqueue(workRequest);
    }
}