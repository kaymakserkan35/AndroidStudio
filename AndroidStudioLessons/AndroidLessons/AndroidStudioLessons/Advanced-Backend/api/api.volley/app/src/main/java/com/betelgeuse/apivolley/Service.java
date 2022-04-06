package com.betelgeuse.apivolley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Service {
    private Context context;
    private   String Tickers = "https://api.blockchain.com/v3/exchange/tickers";
    private  static Object lockObj = new Object();
    private  static  Service instance=null;
    public  static  Service getInstance(){
        if (instance==null){
            synchronized (lockObj){
                instance = new Service();
            }
        }
        return instance;
    }
    public void setContext (Context context) {
        this.context = context;
    }

    public  void getTickers()  {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Tickers, new Response.Listener<String>() {
            @Override
            public void onResponse (String response) {
                Log.e("onResponse", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse (VolleyError error) {
                Log.e("onErrorResponse", error.toString());
            }
        });
        Volley.newRequestQueue(context).add(stringRequest);
    }
    public  void getTicker(String symbol)  {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Tickers+"/"+symbol, new Response.Listener<String>() {
            @Override
            public void onResponse (String response) {
                Log.e("onResponse", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse (VolleyError error) {
                Log.e("onErrorResponse", error.toString());
            }
        });
        Volley.newRequestQueue(context).add(stringRequest);
    }

}
