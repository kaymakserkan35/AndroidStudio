package com.betelgeuse.internalstoragewriteread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView yazilacakDeger, okunanDeger;
    Button saveButton, readButton;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveButton = findViewById(R.id.writeBTN);
        readButton = findViewById(R.id.readBTN);
        yazilacakDeger = findViewById(R.id.textForWrite);
        okunanDeger = findViewById(R.id.textForReaded);


        saveButton.setOnClickListener(v -> {
            String data = yazilacakDeger.getText().toString();
            FileOutputStream fos;
            try {
                fos = openFileOutput("myFile", Context.MODE_PRIVATE);
                fos.write(data.getBytes());
                fos.close();
                Toast.makeText(getApplicationContext(), "myFile" + " saved", Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        readButton.setOnClickListener(v -> {
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader inputReader = null;
            try {
                inputReader = new BufferedReader(new InputStreamReader(openFileInput("myFile")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String inputString="";
            while (true) {
                try {
                    if (!((inputString = inputReader.readLine()) != null)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stringBuffer.append(inputString + "\n");
            }
            okunanDeger.setText(stringBuffer.toString());
            Toast.makeText(getApplicationContext(),stringBuffer.toString(),Toast.LENGTH_LONG).show();
        });

    }


}