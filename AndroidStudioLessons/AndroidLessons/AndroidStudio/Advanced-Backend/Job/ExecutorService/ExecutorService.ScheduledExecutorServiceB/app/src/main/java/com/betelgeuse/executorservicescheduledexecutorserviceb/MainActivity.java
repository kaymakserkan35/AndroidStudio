package com.betelgeuse.executorservicescheduledexecutorserviceb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scheduledExecutorService();    }

    private  void scheduledExecutorService(){

        /*
         örnekte ExecutorService yerine ScheduledExecutorService kullanılarak işlemin ileri bir tarihte gerçekleşmesi sağlanır.
        schedule metodu ile verilen iş parçası 5 saniye gecikmeli çalışacaktır.
        Thread.sleep metodu ise ana akışı durdurur ancak MyThread ile tanımlanan worker iş parçası ScheduledExecutorService tarafından çalıştırılmaktadır.
        */

        try {ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
            MyThread worker = new MyThread("Thread", 1000);
            pool.schedule(worker, 5, TimeUnit.SECONDS);
            Thread.sleep(20000);
            pool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    private  void scheduleAtFixedRate(){
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
        MyThread worker = new MyThread("Thread", 0);
        ScheduledFuture workerHandle = pool.scheduleAtFixedRate(worker,2000,1000,TimeUnit.SECONDS);
        pool.schedule((Runnable) ( ) -> workerHandle.cancel(true),60*60,TimeUnit.SECONDS);
        pool.shutdown();
    }
}