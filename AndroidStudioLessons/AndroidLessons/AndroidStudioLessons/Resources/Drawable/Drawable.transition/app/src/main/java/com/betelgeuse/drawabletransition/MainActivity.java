package com.betelgeuse.drawabletransition;

import static com.betelgeuse.drawabletransition.R.drawable.lamp1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

  //  View v;
    Button button;
    Button button2;
    ImageView imageView,imageView2;
    boolean turnedOn=false;

    @Nullable
    @Override
    public View onCreateView (@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        View v = super.onCreateView(name, context, attrs);
        return v;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView5);
        imageView2=findViewById(R.id.imageView6);
         button=findViewById(R.id.button9);
         button2=findViewById(R.id.button10);
        button.setOnClickListener(view -> ((TransitionDrawable)imageView.getDrawable()).startTransition(3000));
        button2.setOnClickListener(view -> {
            ((TransitionDrawable)imageView2.getDrawable()).startTransition(1500);
            if(!turnedOn){
                imageView2.setImageResource(R.drawable.transition_off_to_on);
                ((TransitionDrawable)imageView2.getDrawable()).startTransition(1500);
                turnedOn=true;
            }
            else {
                imageView2.setImageResource(R.drawable.transition_on_to_off);
                ((TransitionDrawable)imageView2.getDrawable()).startTransition(1500);
                turnedOn=false;
            }
        });
    }
}