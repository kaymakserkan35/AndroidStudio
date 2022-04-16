package com.betelgeuse.jsouphtmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    TextView    dolar;
    Button      getHtmlData;
    Button      getDolarValue;
    Document document;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getHtmlData = findViewById(R.id.getHtmlData);
        getDolarValue = findViewById(R.id.getDolarValue);
        dolar = findViewById(R.id.DolarText);
        getHtmlData.setOnClickListener(v -> {
            JsoupHelper jsoupHelper =
                    (JsoupHelper) new JsoupHelper("https://uzmanpara.milliyet.com.tr/dolar-kuru/", MainActivity.this);
            jsoupHelper._dolar=dolar;
            jsoupHelper.execute();

        });

        getDolarValue.setOnClickListener(v -> {

        });


    }
}