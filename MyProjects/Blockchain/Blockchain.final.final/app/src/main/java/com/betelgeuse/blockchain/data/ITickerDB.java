package com.betelgeuse.blockchain.data;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.data.dataListener.TickerDTOListListener;
import com.betelgeuse.blockchain.data.dataListener.TickerDTOListener;

public interface ITickerDB {

    void readTickersOfDate (String date, @Nullable TickerDTOListListener listener);

    void readTickersFromDateToNow (String date, @Nullable TickerDTOListListener listener);

    void readTickerOfDate (String toCurrency, String dateValue, @Nullable TickerDTOListener listener);

    void readTickerFromDateToNow (String toCurrency, String fromDate, @Nullable TickerDTOListListener listener);
}
