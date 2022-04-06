package com.betelgeuse.dragcopypaste;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public abstract class Helper {

    public static   <T extends Object> T isNull(T obj , AppCompatActivity context) {
        if (obj==null) {
            Toast.makeText(context,obj.getClass().getSimpleName().toString()+"is Null!!",Toast.LENGTH_SHORT).show();
               new Thread(new Runnable() {
                   @Override
                   public void run ( ) {
                       try {
                           Thread.sleep(1000);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
               }).start();
            return  obj;
        }
        return obj;
    }
}
