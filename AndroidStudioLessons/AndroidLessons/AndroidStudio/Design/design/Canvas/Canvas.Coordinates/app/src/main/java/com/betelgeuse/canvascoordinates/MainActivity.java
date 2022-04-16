package com.betelgeuse.canvascoordinates;

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
    TextView         topLeftText, bottomRightText, touchedPointCoordinates,imagesCenterCoordinates;
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
        imagesCenterCoordinates = findViewById(R.id.imagesCenterCoordinates);
        image = findViewById(R.id.imageCentered);
        topLeftText = findViewById(R.id.topLeftText);
        bottomRightText = findViewById(R.id.bottomRightText);
        touchedPointCoordinates = findViewById(R.id.touchedPointCoordinates);
        surface = findViewById(R.id.surface);
        /*--------------------------------------------*/
        int[] topLeftTextLocation = getViewsLocationOnScreen(topLeftText);
        String _topLeftText = String.valueOf(topLeftTextLocation[0] + "," + topLeftText.getY());
        topLeftText.setText(_topLeftText);
        /*--------------------------------------------*/
        int[] bottomRightTextLocation = getViewsLocationOnSurface(bottomRightText);
        String _bottomRightText =
                String.valueOf(bottomRightTextLocation[0] + "," + bottomRightTextLocation[1]);
        bottomRightText.setText(_bottomRightText);
        // bottomRightText.setText(String.valueOf(bottomRightText.getX()+","+bottomRightText.getY()));
        /*--------------------------------------------*/
        int[] imagesCenterLocation = getViewsCenterLocationOnScreen(image);
        String _imagesCenterCoordinates = String.valueOf(imagesCenterLocation[0] + "," +imagesCenterLocation[1]);
        imagesCenterCoordinates.setText(_imagesCenterCoordinates);
        /*------------------------------------------------*/
        surface.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    String _touchedPointCoordinates =
                            String.valueOf(event.getX() + "," + event.getY());
                    touchedPointCoordinates.setText(_touchedPointCoordinates);
                }
            }
            return true;
        });
    }


    private int[] getViewsLocationOnSurface(View view){
        int[] location = new int[2];
        location[0] = (int) view.getX();
        location[1] = (int) view.getY();
        return location;
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private int[] getViewsLocationOnScreen (View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        return location;
    }
    private int[] getViewsCenterLocationOnScreen(View view){
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        location[0] = location[0]+view.getWidth()/2;
        location[1] = location[1]+view.getHeight()/2;
        return location;
    }


}