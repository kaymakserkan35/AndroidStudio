package com.betelgeuse.scheduledexecutorservice;

import android.util.Log;
import android.widget.TextView;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {
    /* 3 farklı iş parçasının aynı anda çalıştırılabileceği belirtilmiştir.Sonradan eklenen işlemler sıraya (queue) sokulur  */
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
    private static Object lock = new Object();
    private  static ScheduledExecutor instance;
    public static ScheduledExecutor getInstance(MainActivity activity){
        if (instance==null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ScheduledExecutor();
                }
            }
        }
        return instance;
    }
    MainActivity activity;
    public void setContext(MainActivity activity){
        this.activity=activity;
    }
    private  int count = 0;


    public void executeForAMinute ( ) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run ( ) {
                Log.e("executeForAMinute", String.valueOf(count) );
                Date date = new Date();
                Log.e("executeForAMinute",String.valueOf( date.getTime()));
                activity.textView.setText(String.valueOf(count));
                activity.textView.invalidate();
                activity.textView.postInvalidate(); // textView i non UI thread den render eder.
                count = count+5;
            }

        };
        final ScheduledFuture executorHandler = scheduler.scheduleAtFixedRate(runnable, 1, 3, TimeUnit.SECONDS);
        scheduler.schedule(( ) -> {
            executorHandler.cancel(true);
        }, 180 , TimeUnit.SECONDS);
    }
}
