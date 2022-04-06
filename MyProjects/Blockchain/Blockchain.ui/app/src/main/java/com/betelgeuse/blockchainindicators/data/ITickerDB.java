package com.betelgeuse.blockchainindicators.data;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchainindicators.data.CallBackClasses.TickerListListener;
import com.betelgeuse.blockchainindicators.data.CallBackClasses.TickerListener;

public interface ITickerDB {

    void readTickersOfDate (String date, @Nullable TickerListListener listener);
    void  readTickersFromDateToNow(String date , @Nullable TickerListListener listener);
    void  readTickerOfDate(String toCurrency, String dateValue, @Nullable TickerListener listener);
    void readTickerFromDateToNow (String toCurrency, String fromDate, @Nullable TickerListListener listener);
}
