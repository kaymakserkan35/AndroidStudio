package com.betelgeuse.events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ConstraintLayout innerContainer;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        innerContainer = findViewById(R.id.innerContainer);
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick (View v) {
                ClipData.Item item = new ClipData.Item((CharSequence) button.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData clipData = new ClipData((button.getTag()).toString(), mimeTypes, item);
                // objenin gölgesini aldık
                View.DragShadowBuilder myObject_dragShadowBuilder =
                        new View.DragShadowBuilder((button));
                button.startDrag(clipData, myObject_dragShadowBuilder, button, 0);
                // objenin gölgesi var => orjinalini görünmez yapalım
                button.setVisibility(View.INVISIBLE);
                return true;
            }
        });
        button.setOnDragListener((View _button, DragEvent event) -> {

           // Log.d("getLocalState", event.getLocalState().getClass().getSimpleName());
           // Log.d("_button", (String) _button.getTag());
            return true;
        });
        innerContainer.setOnTouchListener((View _innerContainer, MotionEvent event) -> {
           // Log.d("eventAction", MotionEvent.actionToString(event.getAction()));
           // Log.d("_innerContainer", _innerContainer.getTag().toString());
           // Log.d("getDevice", event.getDevice().getName());
            String a = MotionEvent.actionToString(event.getFlags());
            Log.d("a", a  );
            return true;
        });
    }
}