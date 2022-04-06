package com.betelgeuse.blockchain.data;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.data.CallBackClasses.TickerListListener;
import com.betelgeuse.blockchain.data.CallBackClasses.TickerListener;

import java.util.Calendar;
import java.util.Date;

public interface ITickerDB {

    void readTickersOfDate (String date, @Nullable TickerListListener listener);
    void  readTickersFromDateToNow(String date , @Nullable TickerListListener listener);
    void  readTickerOfDate(String toCurrency, String dateValue, @Nullable TickerListener listener);
    void readTickerFromDateToNow (String toCurrency, String fromDate, @Nullable TickerListListener listener);
}
