package com.betelgeuse.blockchain.service.freecurrencyapiservice.manager;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.service.freecurrencyapiservice.CallBackClasses.CallBackJSONArray;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IFreeCurrencyService {

    void latestCurrenciesStringRequest();
    void latestCurrenciesJsonArrayRequest();
    void latestCurrenciesJsonObjectRequest();
    void getTickersStringRequest(String baseCurrency);
    void getTickersWithHistory(String baseCurrency, String fromDate, String toDate);
}
