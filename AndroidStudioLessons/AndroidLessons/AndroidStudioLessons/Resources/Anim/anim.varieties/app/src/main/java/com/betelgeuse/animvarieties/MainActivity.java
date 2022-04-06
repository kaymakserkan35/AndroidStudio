package com.betelgeuse.animvarieties;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button fadein,fadeout,zoomin,zoomout,rotateclockwise,rotateanticlockwise,moveup,movedown,moveleft,moveright,
            slideup,slidedown,slideleft,slideright,bounce,blink,sequential,together;
    ImageView imageView;
   public Animation animation;

public  void  setViewComponents(){

    fadein=findViewById(R.id.fadein);
    fadeout=findViewById(R.id.fadeout);
    zoomin=findViewById(R.id.zoomin);
    zoomout=findViewById(R.id.zoomout);
    rotateclockwise=findViewById(R.id.rotateclockwise);
    rotateanticlockwise=findViewById(R.id.rotateanticlockwise);
    moveup=findViewById(R.id.moveup);
    movedown=findViewById(R.id.movedown);
    moveleft=findViewById(R.id.moveleft);
    moveright=findViewById(R.id.moveright);
    slideup=findViewById(R.id.slideup);
    slidedown=findViewById(R.id.slidedown);
    slideleft=findViewById(R.id.slideleft);
    slideright=findViewById(R.id.slideright);
    bounce=findViewById(R.id.bounce);
    blink=findViewById(R.id.blink);
    sequential=findViewById(R.id.sequential);
    together=findViewById(R.id.together);
    imageView=findViewById(R.id.image);
}

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewComponents();


        fadein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                imageView.startAnimation(animation);
            }
        });
        fadeout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
                imageView.startAnimation(animation);
            }
        });
        zoomin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
                imageView.startAnimation(animation);
            }
        });
        zoomout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
                imageView.startAnimation(animation);
            }
        });
        rotateclockwise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotateclockwise);
                imageView.startAnimation(animation);
            }
        });
        rotateanticlockwise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotateanticlockwise);
                imageView.startAnimation(animation);
            }
        });
        moveup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_up);
                imageView.startAnimation(animation);
            }
        });
        movedown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_down);
                imageView.startAnimation(animation);
            }
        });
        moveleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_left);
                imageView.startAnimation(animation);
            }
        });
        moveright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_right);
                imageView.startAnimation(animation);
            }
        });
        slideup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
                imageView.startAnimation(animation);
            }
        });
        slidedown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
                imageView.startAnimation(animation);
            }
        });
        slideleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_left);
                imageView.startAnimation(animation);
            }
        });
        slideright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_right);
                imageView.startAnimation(animation);
            }
        });
        bounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
                imageView.startAnimation(animation);
            }
        });
        blink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
                imageView.startAnimation(animation);
            }
        });
        sequential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.sequential);
                imageView.startAnimation(animation);
            }
        });
        together.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.together);
                imageView.startAnimation(animation);
            }
        });

    }
}