package com.betelgeuse.canvasdrawandpaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SimpleDrawingView extends View {

    private final int   paintColor = Color.BLACK;
    private       Paint drawPaint;

    public SimpleDrawingView (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setAttributes();
        setupDrawPaint(new Paint());
    }

    private void setAttributes ( ) {
        setFocusable(true);
        setFocusableInTouchMode(true);
        setBackgroundColor(Color.rgb(204, 204, 204));
    }

    private void setupDrawPaint (Paint drawPaint) {
        this.drawPaint = drawPaint;
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    // this method is automatically called when a view is rendered
    @Override
    protected void onDraw (Canvas canvas) {
        drawPaint.setColor(Color.BLUE);
        canvas.drawCircle(1, 1, 20, drawPaint);
        drawPaint.setColor(Color.BLACK);
        canvas.drawCircle(50, 50, 30, drawPaint);
        drawPaint.setColor(Color.WHITE);
        canvas.drawCircle(100, 100, 10, drawPaint);
        super.onDraw(canvas);
    }
}
