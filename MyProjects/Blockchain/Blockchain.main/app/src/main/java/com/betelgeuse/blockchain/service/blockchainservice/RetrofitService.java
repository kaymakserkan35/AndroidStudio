package com.betelgeuse.blockchain.service.blockchainservice;

import android.content.Context;

import androidx.annotation.Nullable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitService {

    private String apikey ;
    protected String  baseURL ;
    protected Context context;

    public RetrofitService (Context context, String baseURL, @Nullable String apikey) {
        this.apikey = apikey;
        this.baseURL = baseURL;
        this.context = context;
    }

    public Retrofit retrofit = null;

    public Retrofit initRetrofit (String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
