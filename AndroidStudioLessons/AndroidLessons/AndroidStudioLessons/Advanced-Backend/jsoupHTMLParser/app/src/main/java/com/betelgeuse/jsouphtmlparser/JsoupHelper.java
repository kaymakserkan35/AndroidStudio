package com.betelgeuse.jsouphtmlparser;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.RecursiveTask;

public class JsoupHelper extends  AsyncTask<Void, Void, Void>
{
    TextView _dolar ;
    public  Document document;
    String url;
   protected AppCompatActivity context;
    ProgressDialog progressDialog;
    public JsoupHelper (String url, AppCompatActivity context) {
        this.url = url;
        this.context = context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("BAŞLIK");
        progressDialog.setMessage("html verisi çekiliyor...");
        progressDialog.setIndeterminate(false);
        progressDialog.show();
    }
    @Override
    protected Void doInBackground (Void... voids) {
        try{
             this.document = Jsoup.connect(url).get();
            Element e = document.getElementById("usd_header_son_data");
            if (e != null) {
                _dolar.setText(e.text());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute (Void unused) {
        super.onPostExecute(unused);
        progressDialog.dismiss();
    }
}
