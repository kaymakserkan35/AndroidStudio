package com.betelgeuse.canvasdrawbitmap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {
    private Paint drawPaint;

    public CustomView (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupAttributes();
        setupPaint(new Paint());
    }

    private void setupAttributes ( ) {
        setFocusable(true);
        setFocusableInTouchMode(true);
        setBackgroundColor(Color.YELLOW);
    }

    private void setupPaint (Paint drawPaint) {
        this.drawPaint = drawPaint;
        drawPaint.setColor(Color.BLACK);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw (Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bitmap);
        int cX = canvas.getWidth();
        int cY = canvas.getHeight();
        canvas.drawCircle(cX/2,cY/2,20,drawPaint);
        canvas.drawBitmap(bitmap,0,0,null);

    }
}
