package com.betelgeuse.firebasecloud2;

import android.util.Log;

import java.util.Date;
import java.util.UUID;

public  class MyHelper {

    public  static  <T>  T IsNullDebug (T t) {
        if (t == null) {
            Log.e("","MyDebug: " + t.getClass().getSimpleName()+"NULL!!"); }
        return  t;
    }
    public  static  String GenerateId(){
        String uniqueID = UUID.randomUUID().toString().substring(0,5);
        Date date = new Date();
        return   uniqueID+String.valueOf(date.getTime());
    }

}
