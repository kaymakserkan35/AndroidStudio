package com.betelgeuse.blockchain.service.freecurrencyapiservice.manager;

import android.content.Context;
import android.icu.text.SimpleDateFormat;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.betelgeuse.blockchain.H;
import com.betelgeuse.blockchain.service.freecurrencyapiservice.CallBackClasses.CallBackJSONArray;
import com.betelgeuse.blockchain.service.freecurrencyapiservice.VolleyService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FreeCurrencyService extends VolleyService implements IFreeCurrencyService {

    public FreeCurrencyService (Context context) {
        super(context);
    }


    @Override // worked
    public void latestCurrenciesStringRequest ( ) {
        StringRequest stringRequest =
                new StringRequest(Request.Method.GET, baseURL+"latest?apikey="+apikey,
                        (String response) -> {
                            H.debugLog(this.getClass().getSimpleName(), "latestCurrenciesStringRequest", response.substring(0, 150));

                        },
                        (VolleyError error) -> {
                            H.errorLog(this.getClass().getSimpleName(),"latestCurrenciesStringRequest",error.getLocalizedMessage());
                        }) {


                };
        requestQueue.add(stringRequest);
    }
    @Override  //does not working
    public void latestCurrenciesJsonArrayRequest () {
        // does not work
        JsonArrayRequest jsObjRequest =
                new JsonArrayRequest(Request.Method.GET, baseURL+"latest?apikey="+apikey, null,
                        (JSONArray response) -> {
                            H.debugLog(this.getClass().getSimpleName(), "getTickersJSON", response.toString().substring(0, 150));

                        },
                        (VolleyError error) -> {
                            H.errorLog(this.getClass().getSimpleName(), "getTickersJSON", error.getLocalizedMessage());
                        });

                    jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                            3000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    requestQueue.add(jsObjRequest);


        H.debugLog(this.getClass().getSimpleName(), "getUrl", jsObjRequest.getUrl());
    }
    @Override // worked
    public  void  latestCurrenciesJsonObjectRequest(){
        JsonObjectRequest stringRequest =
                new JsonObjectRequest(Request.Method.GET, baseURL+"latest?apikey="+apikey,null,
                        (JSONObject response) -> {
                            H.debugLog(this.getClass().getSimpleName(), "getTickers", response.toString().substring(0,25));

                        },
                        (VolleyError error) -> {
                            H.errorLog(this.getClass().getSimpleName(),"getTickersJSON",error.getLocalizedMessage());
                        }) {
                    @Override
                    public Map getHeaders() throws AuthFailureError
                    {
                        HashMap headers = new HashMap();
                        headers.put("Content-Type", "application/json");
                        headers.put("apikey", apikey);
                        return headers;
                    }
                };
        requestQueue.add(stringRequest);
    }

    @Override //worked
    public void getTickersStringRequest (String baseCurrency) {
        StringRequest stringRequest =
                new StringRequest(Request.Method.GET, baseURL+"latest?apikey="+apikey+"&base_currency="+baseCurrency,
                        (String response) -> {
                            H.debugLog(this.getClass().getSimpleName(), "getTickersStringRequest", response.substring(0, 150));

                        },
                        (VolleyError error) -> {
                            H.errorLog(this.getClass().getSimpleName(),"getTickersStringRequest",error.getLocalizedMessage());
                        }) {
                    @Override
                    public Map getHeaders() throws AuthFailureError
                    {
                        HashMap headers = new HashMap();
                        headers.put("Content-Type", "application/json");
                        headers.put("apiKey", apikey);
                        headers.put("base_currency", baseCurrency);
                        return headers;
                    }


                };
        requestQueue.add(stringRequest);
    }

    @Override //worked
    public void getTickersWithHistory (String baseCurrency, String fromDate,String toDate) {
        StringRequest stringRequest =
                new StringRequest(Request.Method.GET, baseURL+"historical?apikey="+apikey+"&base_currency="+baseCurrency+"&date_from="+fromDate+"&date_to="+toDate,
                        (String response) -> {
                            H.debugLog(this.getClass().getSimpleName(), "getTickersWithHistory", response.substring(0, 150));

                        },
                        (VolleyError error) -> {
                            H.errorLog(this.getClass().getSimpleName(),"getTickersWithHistory",error.getLocalizedMessage());
                        }) {


                };
        requestQueue.add(stringRequest);
    }
}
