package com.betelgeuse.budgiesoundgame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private boolean isPlayingSound = false;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onTouchEvent (MotionEvent event) {
        Log.e("onTouchEvent", MotionEvent.actionToString(event.getAction()));
        playSound();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            playSound();
        }
        return true;
    }

    private void playSound ( ) {
        if (isPlayingSound==true) return;
        if (!isPlayingSound){
            MediaPlayer mp = MediaPlayer.create(this, R.raw.bird_sound_mp3);
            mp.start();
            isPlayingSound=true;
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion (MediaPlayer mp) {
                    mp.release();
                    mp = null;
                    isPlayingSound=false;
                }
            });
        }

    }

}