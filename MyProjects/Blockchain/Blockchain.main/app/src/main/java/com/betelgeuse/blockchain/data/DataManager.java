package com.betelgeuse.blockchain.data;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.view.TintableBackgroundView;

import com.betelgeuse.blockchain.H;
import com.betelgeuse.blockchain.data.CallBackClasses.TickerListListener;
import com.betelgeuse.blockchain.data.CallBackClasses.TickerListener;
import com.betelgeuse.blockchain.dto.TickerDTO;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DataManager implements IDatabase {
    IDatabase database;
    public DataManager (IDatabase database) {
        this.database = database;
    }
    @Override
    public void readTickersOfDate (String date , TickerListListener listener) {
        // cache den al!!...
        if (true) {
            database.readTickersOfDate(date, listener);
        } else {
        }

    }

    @Override
    public void readTickersFromDateToNow (String date , @Nullable TickerListListener listener) {

    }

    @Override
    public void readTickerOfDate ( String toCurrency, String dateValue, @Nullable TickerListener listener) {

    }

    @Override
    public void readTickerFromDateToNow (String toCurrency, String fromDate, @Nullable TickerListListener listener) {

    }


}
