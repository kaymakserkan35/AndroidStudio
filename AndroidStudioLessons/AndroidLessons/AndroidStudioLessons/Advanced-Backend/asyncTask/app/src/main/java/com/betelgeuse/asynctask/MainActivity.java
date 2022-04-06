package com.betelgeuse.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button      button;
    TextView    textView;
    ProgressBar progressBar;
    MyAsyncTask myAsyncTask;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        textView=findViewById(R.id.textView);
        progressBar=findViewById(R.id.progressBar);
        myAsyncTask=new MyAsyncTask(MainActivity.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myAsyncTask.getStatus()== AsyncTask.Status.RUNNING){}
                else if(myAsyncTask.getStatus()==AsyncTask.Status.FINISHED){}
                else {
                    myAsyncTask.execute(new BackgroundObject());
                }
            }
        });
    }
}