package com.betelgeuse.workmanager;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyDatabaseWorker extends Worker {
    private  static  String databaseName = "com.betelgeuse.workmanager";

    public MyDatabaseWorker (@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    private int getData(String data_key){
        Data data = getInputData();
        int _data = data.getInt(data_key,0);
        return  _data;
    }
    @NonNull
    @Override
    public Result doWork ( ) {
        refreshDatabase(databaseName,"data_id", getData("data_key"));
        return Result.success();
    }
    private  void  refreshDatabase(String databaseName, String data_id,int _data){
        SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences(databaseName,Context.MODE_PRIVATE);
        int mySavedNumber  = sharedPreferences.getInt(data_id,0);
        mySavedNumber = mySavedNumber +_data;
        sharedPreferences.edit().putInt(data_id,mySavedNumber).apply();
        System.out.println(mySavedNumber);
    }
}
