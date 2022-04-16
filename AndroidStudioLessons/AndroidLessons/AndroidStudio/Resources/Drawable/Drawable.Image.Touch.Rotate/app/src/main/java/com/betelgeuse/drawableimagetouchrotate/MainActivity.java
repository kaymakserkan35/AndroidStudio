package com.betelgeuse.drawableimagetouchrotate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int count = 0;
    ConstraintLayout surface;
    TextView          touchedPointCoordinates,imagesDegree,twoPointDegree;
    ImageView image;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConstraintLayout mainLayout =
                (ConstraintLayout) this.getLayoutInflater().inflate(R.layout.activity_main, null);
        Log.e("count", String.valueOf(count));
        count = count + 1;
        mainLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onGlobalLayout ( ) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

                    mainLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {

                    mainLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                applyCodes();
            }
        });
        setContentView(mainLayout);

    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void applyCodes ( ) {
        image = findViewById(R.id.imageCentered);
        touchedPointCoordinates = findViewById(R.id.touchPoint);
        imagesDegree = findViewById(R.id.imagesDegree);
        twoPointDegree = findViewById(R.id.twoPointDegree);
        surface = findViewById(R.id.surface);

        /*------------------------------------------------*/
        surface.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    String _touchedPointCoordinates =
                            String.valueOf((int) event.getX() + "," +(int) event.getY());
                    touchedPointCoordinates.setText(_touchedPointCoordinates);
                }
            }
            return true;
        });
    }





}