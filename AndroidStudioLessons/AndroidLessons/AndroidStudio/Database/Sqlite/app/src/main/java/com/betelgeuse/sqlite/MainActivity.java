package com.betelgeuse.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final   Button create = findViewById(R.id.createTable);
        final  Button read = findViewById(R.id.read);
        create.setOnClickListener(view -> write());
        read.setOnClickListener(view -> {
            Option option = read();
            Log.d("TAG", option.toString());
        });

    }

    public boolean write(){

        SqLiteContext sqLiteContext = new SqLiteContext(getApplicationContext(),Option.class);
        SqlDatabaseManager sqlDatabaseManager = new SqlDatabaseManager(sqLiteContext);
        sqlDatabaseManager.create("kaymak","A/B",Period.fourteen);
        return  true;
    }
    public  Option  read(){
        SqLiteContext sqLiteContext = new SqLiteContext(getApplicationContext(),Option.class);
        SqlDatabaseManager sqlDatabaseManager = new SqlDatabaseManager(sqLiteContext);
        Option option =  sqlDatabaseManager.read("kaymak");
        return  option;
    }
}