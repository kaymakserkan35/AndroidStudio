package com.betelgeuse.draggableobjectmultiblesurface;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.chip.Chip;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnDragListener {

    private Integer onDraggedCount = 0;
    private Button  buttonA, buttonB, buttonC;
    private LinearLayout lineerLayoutContainer, linearLayoutTop, linearLayoutBottom,
            linearLayoutBottomRight,
            linearLayoutBottomLeft;

    private static final String DRAGGABLE_OBJECT = "DRAGGABLE_OBJECT";
    private static final String LINEAR_LAYOUT    = "LINEAR_LAYOUT";

    public void init ( ) {
        buttonA = findViewById(R.id.buttonA);
        buttonA.setTag(DRAGGABLE_OBJECT + "_A");
        buttonA.setOnLongClickListener(MainActivity.this);
        buttonA.setOnDragListener(MainActivity.this);
        buttonB = findViewById(R.id.buttonB);
        buttonB.setTag(DRAGGABLE_OBJECT + "_B");
        buttonB.setOnLongClickListener((View.OnLongClickListener) MainActivity.this);
        buttonB.setOnDragListener(MainActivity.this);
        buttonC = findViewById(R.id.buttonC);
        buttonC.setTag(DRAGGABLE_OBJECT + "_C");
        buttonC.setOnLongClickListener((View.OnLongClickListener) MainActivity.this);
        buttonC.setOnDragListener(MainActivity.this);

        lineerLayoutContainer = findViewById(R.id.lineerLayoutContainer);
        lineerLayoutContainer.setTag(LINEAR_LAYOUT + "CONTAINER");
        linearLayoutTop = findViewById(R.id.lineerLayoutTop);
        linearLayoutTop.setTag(LINEAR_LAYOUT + "_TOP");
        linearLayoutTop.setOnDragListener(MainActivity.this);
        linearLayoutBottom = findViewById(R.id.lineerLayoutBottom);
        linearLayoutBottom.setTag(LINEAR_LAYOUT + "_BOTTOM");
        linearLayoutBottom.setOnDragListener(MainActivity.this);
        linearLayoutBottomLeft = findViewById(R.id.lineerLayoutBottomLeft);
        linearLayoutBottomLeft.setTag(LINEAR_LAYOUT + "_BOTTOM_LEFT");
        linearLayoutBottomLeft.setOnDragListener(MainActivity.this);
        linearLayoutBottomRight = findViewById(R.id.lineerLayoutBottomRight);
        linearLayoutBottomRight.setTag(LINEAR_LAYOUT + "_BOTTOM_RIGHT");
        linearLayoutBottomRight.setOnDragListener(MainActivity.this);

    }


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void toggleColorFilter (View view, DragEvent event) {
        //   if (view == null && event == null) return;
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_ENTERED: {
                setColorFilterInvalidate(view);
            }
            case DragEvent.ACTION_DRAG_EXITED: {
                clearColorFilter(view);
            }

            case DragEvent.ACTION_DRAG_ENDED: {
                clearColorFilter(view);
            }
            case DragEvent.ACTION_DROP: {
                clearColorFilter(view);
            }
        }
    }

    private void setColorFilterInvalidate (View view) {
        Drawable drawable = view.getBackground();
        if (drawable != null) {
            drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            view.invalidate();
        }
    }

    private void clearColorFilter (View view) {
        Drawable drawable = view.getBackground();
        if (drawable != null) {
            drawable.clearColorFilter();
            drawable.invalidateSelf();
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onDrag (View view, DragEvent event) {
        View draggableObject = (View) event.getLocalState(); // we can get "draggableObject "!!
        View cursorView =
                view; // cursor un uzerinde durdugu layout, veya draggable objenin kendisi artık cast edip edemeyeceğine göre karar ver
        View parentView =
                (View) draggableObject.getParent();// we can get backgroundView(linearLayout) of draggableObject!!
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_ENTERED: {
                Log.d("draggableObject->", draggableObject.getTag().toString());
                Log.d("cursorView->", cursorView.getTag().toString());
                if (parentView.getTag() != null) {
                    Log.d("viewParent->", parentView.getTag().toString());
                } else {
                    Log.d("viewParent->", getString(parentView.getId()));
                }
                setColorFilterInvalidate(view);
                return true;
            }
            case DragEvent.ACTION_DROP: {
                clearColorFilter(view);
                if (parentView instanceof LinearLayout) {
                    ((ViewGroup) parentView).removeView(draggableObject);
                }
                if (cursorView instanceof LinearLayout) {
                   ((LinearLayout) cursorView).addView(draggableObject);
                }


                //   ViewGroup eskitasarimalni = (ViewGroup) görselNesne.getParent();
                //   eskitasarimalni.removeView(görselNesne);
                //   eskitasarimalni.notify();
                return true;
            }
            case DragEvent.ACTION_DRAG_EXITED: {
                clearColorFilter(view);
                return true;
            }
            default:
                return true;
        }
    }

    @Override
    public boolean onLongClick (View view) {
        Log.d("onLongClick ->", view.getTag().toString());
        ClipData.Item itemClipData = new ClipData.Item((CharSequence) view.getTag());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData clipData = new ClipData(view.getTag().toString(), mimeTypes, itemClipData);
        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(clipData, dragShadowBuilder, view, 0);
        view.setVisibility(View.VISIBLE);
        return true;
    }
}