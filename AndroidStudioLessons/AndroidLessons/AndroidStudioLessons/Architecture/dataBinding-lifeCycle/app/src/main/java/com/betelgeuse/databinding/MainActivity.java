package com.betelgeuse.databinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.betelgeuse.databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private  final String TAG = MainActivity.this.getClass().getSimpleName().toString();
    private ActivityMainBinding binding;

    @Override
    /* onCreate methodu altında ilk bağlantılar yapılabilir*/
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        stateListener();
    }

    @Override
    /* onStart methodu altında artık ne işlem yapılacak ise bu methodlar yazılabilir!!*/
    protected void onStart ( ) {
        super.onStart();
        binding.buttonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        binding.PlainTextID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"text tıklandı",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void stateListener(){
        this.getLifecycle().addObserver(new DefaultLifecycleObserver() {
            @Override
            public void onCreate (@NonNull LifecycleOwner owner) {
                Toast.makeText( MainActivity.this,owner.getLifecycle().getCurrentState().toString(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, owner.getLifecycle().getCurrentState().toString() );
            }

            @Override
            public void onStart (@NonNull LifecycleOwner owner) {
                Toast.makeText( MainActivity.this,owner.getLifecycle().getCurrentState().toString(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, owner.getLifecycle().getCurrentState().toString() );
            }

            @Override
            public void onResume (@NonNull LifecycleOwner owner) {
                Toast.makeText( MainActivity.this,owner.getLifecycle().getCurrentState().toString(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, owner.getLifecycle().getCurrentState().toString() );
            }

            @Override
            public void onPause (@NonNull LifecycleOwner owner) {
                Toast.makeText( MainActivity.this,owner.getLifecycle().getCurrentState().toString(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, owner.getLifecycle().getCurrentState().toString() );
            }

            @Override
            public void onStop (@NonNull LifecycleOwner owner) {
                Toast.makeText( MainActivity.this,owner.getLifecycle().getCurrentState().toString(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, owner.getLifecycle().getCurrentState().toString() );
            }

            @Override
            public void onDestroy (@NonNull LifecycleOwner owner) {
                Toast.makeText( MainActivity.this,owner.getLifecycle().getCurrentState().toString(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, owner.getLifecycle().getCurrentState().toString() );
            }
        });
    }
}