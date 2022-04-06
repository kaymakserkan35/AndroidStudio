package com.betelgeuse.dragcopypaste;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    Button copyText,changeActivity;
    TextView textView;

    private ClipboardManager clipboardManager;
    private ClipData clipData;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        copyText=findViewById(R.id.copyText);
        changeActivity=findViewById(R.id.changeActivity);
        textView = findViewById(R.id.textForCopy);
        clipboardManager = (ClipboardManager) getSystemService(Service.CLIPBOARD_SERVICE);

        copyText.setOnClickListener(v -> {
            String myStr = textView.getText().toString();
            if (myStr==null){myStr="";}
            clipData = ClipData.newPlainText("txt",myStr);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(getApplicationContext(), "panoya kopyalandı", Toast.LENGTH_SHORT).show();
        });

        changeActivity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);
        });

    }
}