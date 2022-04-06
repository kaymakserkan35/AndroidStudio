package com.betelgeuse.draggableoneobject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private static final String                      draggableObject = "DRAGGABLE_OBJECT";
    private              View                        myObject;
    private              RelativeLayout              surface;
    private              RelativeLayout.LayoutParams surfaceParams_Of_myObject;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myObject = findViewById(R.id.myObject);
        myObject.setTag(draggableObject);
        surface = findViewById(R.id.surface);

        myObject.setOnLongClickListener(myObject -> {
            ClipData.Item item = new ClipData.Item((CharSequence) myObject.getTag());
            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
            ClipData clipData = new ClipData((myObject.getTag()).toString(), mimeTypes, item);
            // objenin gölgesini aldık
            View.DragShadowBuilder myObject_dragShadowBuilder =
                    new View.DragShadowBuilder((myObject));
            myObject.startDrag(clipData, myObject_dragShadowBuilder, myObject, 0);
            // objenin gölgesi var => orjinalini görünmez yapalım
            myObject.setVisibility(View.INVISIBLE);
            return true;
        });

        surface.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag (View surfaceLayout, DragEvent dragEvent) {

                switch (dragEvent.getAction()) {

                    case DragEvent.ACTION_DRAG_STARTED: {
                        surfaceParams_Of_myObject =
                                (RelativeLayout.LayoutParams) myObject.getLayoutParams();
                        break;
                    }
                    case DragEvent.ACTION_DROP: {
                        /*---------------------sürüklenebilir nesnenin yeni koordinatlarını girelim----------------------*/

                        surfaceParams_Of_myObject.leftMargin = (int) dragEvent.getX();
                        surfaceParams_Of_myObject.topMargin = (int) dragEvent.getY();
                        /*-----sürüklenebilir nesnenin eski view i ni destroy edip yeni view i layouta ekleelim----------*/
                        View myObjectCurrentState = (View) dragEvent.getLocalState();
                        ViewGroup surfaceOldState = (ViewGroup) myObjectCurrentState.getParent();
                        surfaceOldState.removeView(myObjectCurrentState);
                        RelativeLayout surfaceNewState = (RelativeLayout) surfaceLayout;
                        surfaceNewState.addView(myObjectCurrentState, surfaceParams_Of_myObject);
                        myObject.setVisibility(View.VISIBLE);
                       break;

                    }
                    default: {
                        break;
                    }

                }


                return true;
            }
        });


    }
}