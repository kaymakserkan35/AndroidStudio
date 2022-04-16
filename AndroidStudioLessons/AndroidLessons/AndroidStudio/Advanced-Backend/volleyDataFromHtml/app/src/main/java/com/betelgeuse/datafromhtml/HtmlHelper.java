package com.betelgeuse.datafromhtml;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class HtmlHelper {
    RequestQueue      queue;
    AppCompatActivity context;

    public HtmlHelper (AppCompatActivity context) {
        this.context = context;
        queue = Volley.newRequestQueue(this.context);
    }

    public void httpRequest (String url) {

        StringRequest stringRequest =
                new StringRequest(Request.Method.GET, url, (Response.Listener<String>) response -> {

                }, error -> {
                    Log.e("TAG", "httpRequest:" + error.toString());
                });
        queue.add(stringRequest);

    }
}
