package com.betelgeuse.canvastouchingdraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class CustomViewTouchingDraw  extends View {
    private final int         paintColor = Color.BLACK;
    private       Paint       drawPaint = new Paint();
    private       List<Point> circlePoints;

    public CustomViewTouchingDraw(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setBackgroundColor(Color.YELLOW);
        drawPaint.setStyle(Paint.Style.FILL);
        circlePoints = new ArrayList<Point>();
    }

    // Draw each circle onto the view
    @Override
    protected void onDraw(Canvas canvas) {
        for (Point p : circlePoints) {
            canvas.drawCircle(p.x, p.y, 5, drawPaint);//nokta halnde cemberler
        }
    }

    // Append new circle each time user presses on screen
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        Point point = new Point(Math.round(touchX), Math.round(touchY));
        circlePoints.add(point);
        // indicate view should be redrawn
        postInvalidate();
        return true;
    }


}