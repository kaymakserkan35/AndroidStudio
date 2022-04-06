package com.betelgeuse.backgroundservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class myService extends Service {
    MediaPlayer mediaPlayer;

    @Override
    public void onCreate ( ) {
        super.onCreate();
        mediaPlayer = MediaPlayer.create((Context) this, R.raw.john_powell_taxi_ride);
    }

    @Override
    public IBinder onBind (Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startId) {
        Log.e("myService", "onStartCommand: " );
        Toast.makeText(this, "Service started by user.", Toast.LENGTH_LONG).show();
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy ( ) {
        super.onDestroy();
        Log.e("myService", "onDestroy: " );
        mediaPlayer.stop();
        Toast.makeText(this, "Service destroyed by user.", Toast.LENGTH_LONG).show();
    }
}