package com.betelgeuse.drawablewalkinggif;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    GifImageView gifImageView;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addGif();
    }

    private void addGif(){
        gifImageView = findViewById(R.id.gifImageView);
        Glide.with(this).load(R.raw.walking_gif).into(gifImageView);
    }
    private void moveGifToTouchPoint(float _x , float _y) {
        gifImageView.animate().x(_x).y(_y).setDuration(0).start();
    }
    private void moveGifToTouchPoint(View view, MotionEvent event){
        float dX=0,dY=0;
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

              dX = view.getX() - event.getRawX();
              dY = view.getY() - event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:

                view.animate()
                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;

        }

    }
    private  void  moveGifFromCenterPointToTouchPoint(View view, MotionEvent event){
        float dX=0, dY=0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                view.animate()
                        .x(event.getRawX() + dX - (view.getWidth() / 2))
                        .y(event.getRawY() +dY - (view.getHeight() / 2) *(5/2) )
                        .setDuration(0)
                        .start();
                break;
        }
    }
    private double findTwoPointAngle(float x1,float y1,float x2, float y2){
        float egim = (y2-y1)/(x2-x1);
        double radian = Math.atan(egim);
        double derece = radian*(180/Math.PI);
     //   if (derece<0){derece = 180 - derece;}
        Log.e("findTwoPointAngle", String.valueOf(derece));
        return derece;
    }
    private  void rotateGif(float firstAngle, float secondAngle){
        ObjectAnimator.ofFloat(gifImageView,"rotation",firstAngle,secondAngle).start();
    }
    private  void  rotateGif(MotionEvent event,View view){

    }
    @Override
    public boolean onTouchEvent (MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:{
                Log.e("TAG", String.valueOf( event.getX())+String.valueOf(event.getY()) );
                double twoPointAngle = findTwoPointAngle(event.getX(),event.getY(),gifImageView.getX(),gifImageView.getY());
                rotateGif( gifImageView.getRotation (),(float) twoPointAngle);
                // moveGifFromCenter(gifImageView,event);
            }
        }

        return true;
    }

}