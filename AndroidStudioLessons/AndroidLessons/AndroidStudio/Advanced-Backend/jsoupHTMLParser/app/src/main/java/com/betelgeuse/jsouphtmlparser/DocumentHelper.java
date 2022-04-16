package com.betelgeuse.jsouphtmlparser;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DocumentHelper extends AsyncTask<Void, Void, Void> {
    String   data = "";
    Document document;

    public DocumentHelper (Document document) {
        this.document = document;
    }


    @Override
    protected Void doInBackground (Void... voids) {
        try {
            Element e = document.getElementById("usd_header_son_data");
            if (e != null) {
                data = e.data();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "doInBackground: "+e.getLocalizedMessage().toString() );
        }

        return null;
    }
}
