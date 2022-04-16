package com.betelgeuse.executorservicethreadmanagment;

import android.util.Log;

public class MyThread extends Thread {
    public MyThread (String threadName, int sleepingTime) {
        this.threadName = threadName;
        this.sleepingTime = sleepingTime;
    }

    String threadName;
    int    sleepingTime;

    @Override
    public void run ( ) {
        try {
            Log.e(threadName, threadName + "running...");
            Thread.sleep(sleepingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
