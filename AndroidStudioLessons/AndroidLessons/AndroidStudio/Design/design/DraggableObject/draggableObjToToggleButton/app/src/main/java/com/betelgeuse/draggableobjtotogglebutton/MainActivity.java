package com.betelgeuse.draggableobjtotogglebutton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.media.metrics.Event;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.chip.Chip;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnDragListener, CompoundButton.OnCheckedChangeListener{

    private LinearLayout chipContainer,container;
    private ToggleButton toggleButton;
    private Chip chipA,chipB;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = findViewById(R.id.container);
        toggleButton = findViewById(R.id.toggleButton);
        chipA=findViewById(R.id.chipA);
        chipB=findViewById(R.id.chipB);
        chipContainer=findViewById(R.id.chipContainer);

        toggleButton.setOnCheckedChangeListener(MainActivity.this);
        toggleButton.setOnDragListener(MainActivity.this);
        chipA.setOnLongClickListener(MainActivity.this);
        chipB.setOnLongClickListener(MainActivity.this);

    }

    @Override
    public boolean onDrag (View view, DragEvent event) {
        switch (event.getAction()){
            case DragEvent.ACTION_DRAG_ENTERED:{
                if(view instanceof ToggleButton)
                {
                    CompoundButton toggleButton = (ToggleButton) view;
                    onCheckedChanged(toggleButton,true);
                     Chip chip =   (Chip) event.getLocalState();
                     chip.setVisibility(View.VISIBLE);
                }
                break;
            }
        }
        return  true;

    }
    @Override
    public boolean onLongClick (View view) {
        ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData clipData = new ClipData((view.getTag()).toString(), mimeTypes, item);
        // objenin gölgesini aldık
        View.DragShadowBuilder myObject_dragShadowBuilder =
                new View.DragShadowBuilder((view));
        view.startDrag(clipData, myObject_dragShadowBuilder, view, 0);
        // objenin gölgesi var => orjinalini görünmez yapalım
        view.setVisibility(View.INVISIBLE);
        return true;
    }
    @Override
    public void onCheckedChanged (CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            Log.e("CompoundButton", "CheckedChanged! " );
            Toast.makeText(getApplicationContext(), "checked!", Toast.LENGTH_SHORT).show();
        }
        if (!isChecked) { }
    }

}