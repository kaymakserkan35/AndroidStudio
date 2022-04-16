package com.betelgeuse.blockchain.data.mySql;

import android.content.Context;

import androidx.annotation.Nullable;

import com.betelgeuse.blockchain.data.IDatabase;
import com.betelgeuse.blockchain.data.dataListener.TickerListListener;
import com.betelgeuse.blockchain.data.dataListener.TickerListener;

public class MySqlDB implements IDatabase
{


    MySqlDB (Context context) {

    }


    @Override
    public void readTickersOfDate (String date, @Nullable TickerListListener listener) {

    }

    @Override
    public void readTickersFromDateToNow (String date, @Nullable TickerListListener listener) {

    }

    @Override
    public void readTickerOfDate (String toCurrency, String dateValue, @Nullable TickerListener listener) {

    }

    @Override
    public void readTickerFromDateToNow (String toCurrency, String fromDate, @Nullable TickerListListener listener) {

    }
}
