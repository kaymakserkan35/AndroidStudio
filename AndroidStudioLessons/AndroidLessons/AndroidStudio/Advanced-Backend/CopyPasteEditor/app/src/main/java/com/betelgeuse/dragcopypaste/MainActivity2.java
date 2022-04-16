package com.betelgeuse.dragcopypaste;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private ClipboardManager clipboardManager;
    ClipData clipData;
    Button   paste;
    TextView textView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        clipboardManager = (ClipboardManager) getSystemService(Service.CLIPBOARD_SERVICE);
        paste = findViewById(R.id.pasteText);
        textView = findViewById(R.id.textView);

        paste.setOnClickListener(v -> {
            clipData = clipboardManager.getPrimaryClip();
            ClipData.Item item = clipData.getItemAt(0);
           String kopyalanmisText = item.getText().toString();
           textView.setText(kopyalanmisText);
            Toast.makeText(MainActivity2.this, "pasted to board", Toast.LENGTH_SHORT).show();
        });
    }
}