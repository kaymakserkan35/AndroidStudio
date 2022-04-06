package com.betelgeuse.blockchain.service.freecurrencyapiservice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.betelgeuse.blockchain.H;

public abstract class VolleyService {
    protected  String apikey = "af16ae00-9416-11ec-84ba-c5e8c4460348";
    protected String baseURL = "https://freecurrencyapi.net/api/v2/";
    protected RequestQueue requestQueue;
    protected Context      context;

    public VolleyService (Context context) {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    @SuppressLint("MissingPermission")
    protected boolean isNetworkConnected ( ) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean result =
                cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
        H.debugLog(this.getClass().getSimpleName(), "isNetworkConnected", String.valueOf(result));
        return result;
    }

    protected boolean isWifiConnected ( ) {

        boolean result = false;
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI")) {
                result = ni.isConnected();
            }
        }
        return result;
    }

    protected boolean isMobileConnected ( ) {
        boolean result = false;
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("MOBILE")) {
                result = ni.isConnected();
            }
        }
        return result;
    }

}
